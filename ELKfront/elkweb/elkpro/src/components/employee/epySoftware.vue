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
                                        height="600"
                                        ref="singleTable"
                                        :data="tableDataInfo"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="time" :formatter="dateForma" sortable label="时间" align="center"></el-table-column>
                                    <el-table-column prop="username" sortable label="用户名" align="center"></el-table-column>
                                    <el-table-column prop="softwarename" sortable label="软件系统的名称"align="center"></el-table-column>
                                    <el-table-column prop="modulename" sortable label="软件系统模块名称" align="center"></el-table-column>
                                    <el-table-column prop="workusemintue" sortable label="使用模块总时间（分钟）" align="center"></el-table-column>
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
                                    <el-table-column prop="TIME" :formatter="dateForma" sortable label="时间" align="center"></el-table-column>
                                    <el-table-column prop="SUM(WORKUSEMINTUE)" sortable label="时间(分钟)" align="center"></el-table-column>
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
            <!--————默认显示区域 begin————-->
            <el-row v-if="defaultData">
                <el-col :span="24">
                    <el-row>
                        <el-col :span="24" class="border_top2">
                            <div class="tableSubTitle">一周内软件使用人数</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="600"
                                        ref="singleTable"
                                        :data="defaultTable"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" :formatter="dateForma" sortable label="日期" align="center"></el-table-column>
                                    <el-table-column prop="COU" sortable label="一周内使用软件人数（人）" align="center"></el-table-column>
                                </el-table>
                                <!--————表格 end————-->
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col class="border_bottom2">
                            <div class="tableSubTitle2"></div>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
            <!--————默认显示区域 end————-->
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
import moment from "moment";
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
            defaultData:true,
            defaultTable:[],
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
        this.getDefaultSoftInfo()
    },
    methods:{
        dateForma:function(row,column){//表格行格式化时间
            var date = row[column.property];
            if(date === undefined){return ''};
            return moment(date).format("YYYY-MM-DD")
        },

        getDefaultSoftInfo(){//DoorInfoMappr.xml - ELKEPYController.java
            var url = "/getDefaultSoftInfo"
            this.$http.get(url).then(res => {
                console.log(res.data)
                this.defaultTable=res.data
            })
        },
        //员工姓名模糊查询
        getEpyCardInfo(){//EpySoftWareUsageMapper.xml - ELKEPYController.java
            this.haveData=true;
            this.haveData1=true;
            this.defaultData=false;
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
.tableSubTitle2 {
    height: 50px;
    line-height: 35px;
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