<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        员工使用软件行为
                    </div>
                </el-col>
            </el-row>
            <!--查询部分-->
            <el-row>
                <el-col :span="24" align="left">
                    <!--员工查询输入框 -->
                    <span class="span_area">
                        <el-input v-model="username" placeholder="请输入员工姓名" style="margin-right: 1%;width:12%;"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getEpyCardInfo">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <!--无数据时的渲染提示-->
            <el-row v-if="haveData3" class="noData_Con">
              <div style="line-height: 500px">
                查询框输入员工姓名即可进行信息展示。
                例如：输入“wuying”
              </div>
            </el-row>
            <el-row>
                <!--————员工近期使用软件信息 begin————-->
                <el-col :span="12" style="margin-top: 8px" v-if="haveData">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">员工近期使用软件信息</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="500"
                                        ref="singleTable"
                                        :data="tableDataInfo"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="time" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="username" label="用户名" align="center"></el-table-column>
                                    <el-table-column prop="softwarename" label="软件系统的名称"align="center"></el-table-column>
                                    <el-table-column prop="modulename" label="软件系统模块名称" align="center"></el-table-column>
                                    <el-table-column prop="usemintue" label="用户使用模块的总时间" align="center"></el-table-column>
                                </el-table>
                                <!--————表格 end————-->
                            </div>
                        </el-col>
                    </el-row>
                    <el-row class="border_bottom">
                        <el-col >
                            <div style="height: 50px"></div>
                        </el-col>
                    </el-row>
                </el-col>
                <!--————员工近期使用软件信息 end————-->
                <!--————员工近期加班使用软件统计 begin————-->
                <el-col :span="11" style="margin-top: 8px;margin-left: 40px" v-if="haveData1">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">员工近期加班使用软件统计</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        ref="singleTable"
                                        :data="tableDataSum"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="SUM(WORKUSEMINTUE)" label="加班时间(分钟)" align="center"></el-table-column>
                                </el-table>
                                <!--————表格 end————-->
                            </div>
                        </el-col>
                    </el-row>
                    <el-row class="border_bottom">
                        <el-col >
                            <div style="height: 50px"></div>
                        </el-col>
                    </el-row>
                </el-col>
                <!--————员工近期加班使用软件统计 end————-->
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
import * as echarts from 'echarts/core';
import {
    TooltipComponent,
    LegendComponent
} from 'echarts/components';
import {
    PieChart
} from 'echarts/charts';
import {
    CanvasRenderer
} from 'echarts/renderers';

echarts.use(
        [TooltipComponent, LegendComponent, PieChart, CanvasRenderer]
);
import VChart, { THEME_KEY } from "vue-echarts";
export default {
    name: "epySoftware",
    components: {
        VChart
    },
    data(){
        return{
            haveData: false,
            haveData1:false,
            //一开始无数据时的数据展示提示
            haveData3:true,
            date:'',//日期变量
            username:'',
            paginations:{
                // 默认显示第几页
                currentPage:1,
                // 个数选择器（可修改）
                // pageSizes:[1,2,3],
                // 默认每页显示的条数（可修改）
                PageSize:10,
            },
            time: '',//根据此时间查询分析表
            currentRow: null,//存储当前点击行信息
            //员工近期使用软件信息
            tableDataInfo:[],
            //员工近期加班使用软件统计
            tableDataSum:[],
        }
    },
    created(){//自动渲染数据
        // this.getDate()
        // this.getIPSAnalysis()
        this.getEpySoftWareUsage()
    },
    methods:{
        getDate(){//获取当前时间
            var date = this.formatter(new Date(), 'yyyy-MM-dd hh:mm:ss')
            this.date=date.toLocaleString()
        },
        getIPSAnalysis(){//渲染数据
            var url="/getIPSAnalysis"
            var params={
                'time':this.date,
            }
            this.$http.get(url,{params}).then(res=>{
                /*————渲染分析表格数据 begin————*/
                while (this.tableData.length !== 0) {
                    this.tableData.pop()
                }
                var i;
                for (i = 0; i < res.data.time.length; i++) {
                    this.tableData.push(
                            {
                                time: res.data.time[i],
                                dstipaddr: res.data.dstipaddr[i],
                                dangervalue: res.data.dangervalue[i],
                            })
                }
                /*————渲染分析表格数据 end————*/
                /*————渲染饼状图数据 begin————*/
                while(this.option.series[0].data.length!==0) {
                    this.option.series[0].data.pop()
                }
                if(res.data.low!==0){
                    this.option.series[0].data.push({value: res.data.low, name: '低'})
                }
                if(res.data.middle!==0){
                    this.option.series[0].data.push({value: res.data.middle, name: '中'})
                }
                if(res.data.high!==0){
                    this.option.series[0].data.push({value: res.data.high, name: '高'})
                }

                /*————渲染饼状图数据 end————*/
            })
        },
        selectStartTime(val) {//日期选择器
            this.time = val;
            if (this.time==null){
                this.getDate()
                this.getIPSAnalysis()
            }
            else if(this.time!==null){
                this.date=this.time
                this.getIPSAnalysis()
            }
        },
        handleSizeChange(val) {
            // 改变每页显示的条数
            this.paginations.PageSize=val
            // 注意：在改变每页显示的条数时，要将页码显示到第一页
            this.paginations.currentPage=1
        },
        // 现在显示当前页的第几页
        handleCurrentChange(val) {
            // 改变默认的页数
            this.paginations.currentPage=val
        },
        formatter (thistime, fmt) {//js格式化时间
            let $this = new Date(thistime)
            let o = {
                'M+': $this.getMonth() + 1,
                'd+': $this.getDate(),
                'h+': $this.getHours(),
                'm+': $this.getMinutes(),
                's+': $this.getSeconds(),
                'q+': Math.floor(($this.getMonth() + 3) / 3),
                'S': $this.getMilliseconds()
            }
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, ($this.getFullYear() + '').substr(4 - RegExp.$1.length))
            }
            for (var k in o) {
                if (new RegExp('(' + k + ')').test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
                }
            }
            return fmt
        },
        //员工姓名模糊查询
        getEpyCardInfo(){
            this.haveData=true;
            this.haveData1=true;
            this.haveData3=false;
            //员工近期使用软件信息
            var url="/getEpySoftWareUsage"
            var params={
                'username':this.username,
            }
            this.$http.get(url,{params}).then(res=>{
                console.log(res)
                this.tableDataInfo=res.data
                console.log(this.tableDataInfo)
            })
            //员工近期加班使用软件统计
            var url="/getEpySoftWareUsageCount"
            var params={
                'username':this.username,
            }
            this.$http.get(url,{params}).then(res=>{
                console.log(res)
                this.tableDataSum=res.data
                console.log(this.tableDataSum)
            })
        }
    }
}
</script>

<style scoped>
.el-container {
    display: flex;
    flex-wrap: nowrap;
}
.el-main {
    color: #ffffff;
    text-align: center;
    height: 90vh;
    z-index: 1;
}
.area{
    width: 72vh;
    height: 67vh;
    background: #ffffff;
    background: url("../../assets/bg_data.png");
    background-size: 100% 100%;
    padding: 60px;
    margin-left: 20px;

}
.span_area {
    /*区域*/
    margin-left: 1px;
}
.title{
    width: 243px;
    height: 75px;
    font-size: 18px;
    color: #17caf0;
    background: url("../../assets/border_label.png") no-repeat;
    line-height: 75px;
    font-weight: bold;
    text-align: center;
}
.tableSubTitle{
    height: 50px;
    line-height: 70px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
}
.border_top{
    background:url("../../assets/border_top.png");
    background-size: 100% 100%;
    text-align: left;
}
.border_bottom{
    background:url("../../assets/border_bottom.png");
    background-size: 100% 100%;
}
.border_top2{
    background:url("../../assets/border_top2.png");
    background-size: 100% 100%;
    text-align: left;
}
.border_bottom2{
    background:url("../../assets/border_bottom2.png");
    background-size: 100% 100%;
}
.el-table{
    header-align: center;
    border-radius: 4px;
    margin: 1% auto 0;
    width: 90%;
}
.el-pagination {
    /*分页*/
    margin-right: 50%;
}
/*————表格背景透明 begin————*/
.table-wrapper /deep/  .el-table,
.el-table__expanded-cell {
    background-color: transparent !important;
}
.table-wrapper /deep/ tr, .table-wrapper /deep/ th, .table-wrapper /deep/ td {
    background: none !important;
    color: #ffffff;
    border-color: #18256f;
}
.table-wrapper /deep/ .el-table__row {
    background: none !important;
    color: #46d4ff;
}
/*————表格背景透明 end————*/
.table-wrapper /deep/ .el-table--striped .el-table__body tr.el-table__row--striped.current-row td, .table-wrapper /deep/ .el-table__body tr.current-row>td {
    color: #ffffff;
    background-color: #17b3f0 !important;
    background-size: 100% 100%;
    opacity: 0.7;
}/*高亮选中行*/
.noData_Con{
  border: 1px dashed white ;
  width: 100%;
  height: 500px;
  margin: 0 auto;
  margin-top: 1%;
}

</style>