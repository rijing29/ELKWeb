<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        公共服务器性能数据
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <!--————近期（一个月）Windows服务器提供服务统计 begin————-->
                <el-col :span="13" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">Windows服务器提供服务统计</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        ref="singleTable"
                                        :data="tableDataService"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="IP" label="IP" align="center"></el-table-column>
                                    <el-table-column prop="TYPE2" label="交互类任务" align="center"></el-table-column>
                                    <el-table-column prop="TYPE3" label="共享类任务" align="center"></el-table-column>
                                    <el-table-column prop="TYPE5" label="服务类任务" align="center"></el-table-column>
                                    <el-table-column prop="TYPE10" label="远程交互任务" align="center"></el-table-column>
                                    <el-table-column prop="TIME" label="服务天数" align="center"></el-table-column>
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
                <!--————近期（一个月）Windows服务器提供服务统计 end————-->
                <!--————Windows服务器日志告警信息 begin————-->
                <el-col :span="10" style="margin-top: 8px;margin-left: 40px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">Windows服务器日志告警信息</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        ref="singleTable"
                                        :data="tableDataLog"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="IP" label="IP" align="center"></el-table-column>
                                    <el-table-column prop="WARNINFO" label="提示信息" align="center"></el-table-column>
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
                <!--————Windows服务器日志告警信息 end————-->
            </el-row>
            <el-row>
                <!--————近期（一个月）Windows服务器任务增长分析 begin————-->
                <el-col :span="13" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">各服务器数据</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        ref="singleTable"
                                        :data="tableDataTask"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="IP" label="IP" align="center"></el-table-column>
                                    <el-table-column prop="INC_VALUE" label="任务增长量" align="center"></el-table-column>
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
                <!--————近期（一个月）Windows服务器任务增长分析 end————-->
                <!--————饼状图区域 begin————-->
                <el-col :span="10">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————柱状图 begin————-->
                            <v-chart class="echarts" :option="option" />
                            <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————饼状图区域 end————-->
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
    name: "serverWin",
    components: {
        VChart
    },
    data(){
        return{
            haveData: true,
            date:'',//日期变量
            pages: [//分页信息
                {
                    pageSize: 20,
                    total: 1000,
                    currentPage: 1,
                },
            ],
            time: '',//根据此时间查询分析表
            currentRow: null,//存储当前点击行信息
            tableDataService:[],//表格数据
            tableDataLog:[
                {TIME:'2021-08-27 14:31:25',IP:'192.168.10.30',WARNINFO:'Server XXX失败'},,
                {TIME:'2021-08-27 11:31:25',IP:'192.168.10.30',WARNINFO:'发生大量登录失败'},
                {TIME:'2021-08-26 18:11:26',IP:'192.168.10.30',WARNINFO:'系统日志被关闭'},
                {TIME:'2021-08-26 14:31:25',IP:'192.168.10.30',WARNINFO:'发生提升用户权限'},
                {TIME:'2021-08-25 11:31:25',IP:'192.168.10.30',WARNINFO:'Server XXX失败'},
                {TIME:'2021-08-24 11:31:25',IP:'192.168.10.30',WARNINFO:'Server XXX失败'},
                {TIME:'2021-08-23 11:31:25',IP:'192.168.10.30',WARNINFO:'Server XXX失败'},
                {TIME:'2021-08-22 14:34:23',IP:'192.168.10.30',WARNINFO:'系统日志被关闭'},
            ],
            tableDataTask:[
                {TIME:'2021-08-27',IP:'192.168.10.30',INC_VALUE:'282%'},,
                {TIME:'2021-08-27',IP:'192.168.10.30',INC_VALUE:'142%'},
                {TIME:'2021-08-26',IP:'192.168.10.30',INC_VALUE:'90%'},
                {TIME:'2021-08-26',IP:'192.168.10.30',INC_VALUE:'72%'},
                {TIME:'2021-08-25',IP:'192.168.10.30',INC_VALUE:'62%'},
                {TIME:'2021-08-24',IP:'192.168.10.30',INC_VALUE:'61%'},
                {TIME:'2021-08-23',IP:'192.168.10.30',INC_VALUE:'54%'},
                {TIME:'2021-08-22',IP:'192.168.10.30',INC_VALUE:'75%'},
            ],
            /*————饼状图数据 begin————*/
            option : {
                title: {
                    text: '负载数据统计',
                    textStyle:{
                        color:"#17caf0"//标题文字颜色
                    },
                    subtextStyle:{
                        color:"#17caf0"//副标题文字颜色
                    },
                },
                legend: {
                    top: 'bottom',
                    textStyle:{
                        color:"#ffffff"//顶部控制区域文字颜色
                    },
                },
                tooltip: {
                    trigger: 'item'
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: false},
                        dataView: {show: false, readOnly: false},
                        restore: {show: false},
                        saveAsImage: {
                            //下载图标
                            show: true,
                            emphasis: {
                                iconStyle: {
                                    textFill: "#2791f3"//鼠标放上工具栏提示文字颜色
                                }
                            }
                        },
                    },
                    iconStyle:{
                        //工具栏图标样式
                        borderColor:"#ffffff",//icon边框颜色
                        borderWidth: 1,//icon边框大小
                    },
                    left: "90%",//工具栏距离左边距离
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['50%', '80%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '40',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {show: true},
                        cursor: "pointer",
                        data: [
                            {value: 112, name: '负载高'},
                            {value: 17, name: '负载低'},
                        ],
                    }
                ]
            },
            /*————饼状图数据 end————*/
        }
    },
    created(){//自动渲染数据
        // this.getDate()
        // this.getIPSAnalysis()
    //    获取windows服务器提供服务统计
        this.getWindowsServer()
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
            }//if
        },
        handleCurrentChange(val) {//表格点击事件
            if(val!==null){
                this.currentRow = val;
                var url="/getIPSInfo"
                var params={
                    'time':this.currentRow.time,
                    'dstipaddr':this.currentRow.dstipaddr,
                    'pageNum': this.pages[0].currentPage,
                    'pageSize': this.pages[0].pageSize,
                }
                console.log(this.currentRow.time, this.currentRow.dstipaddr)
                this.$http.get(url,{params}).then(res=> {
                    /*————渲染详细表格数据 begin————*/
                    this.pages[0].total = res.data.total//向分页传递总数据
                    while (this.tableData1.length !== 0) {
                        this.tableData1.pop()
                    }
                    var k;
                    for (k = 0; k < res.data.time.length; k++) {
                        this.tableData1.push(
                                {
                                    time: res.data.time[k],
                                    attackname: res.data.attackname[k],
                                    severity: res.data.dangervalue[k],
                                    srcipaddr:res.data.srcipaddr[k]
                                })
                    }
                    /*————渲染详细表格数据 end————*/
                })
            }
        },
        currentPage: function (row) {//分页控制部分
            this.pages[0].currentPage = row//取当前页码
            this.handleCurrentChange(this.currentRow)//根据当前页码渲染数据
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
        getWindowsServer(){
            var url="/getWindowsServer"
            this.$http.get(url).then(res=>{
                console.log(res)
                this.tableData=res.data
                console.log(this.tableData)
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
    margin-left: 40px;
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
    height: 500px;
    header-align: center;
    border-radius: 4px;
    margin: 1% auto 0;
    width: 90%;
}
.el-pagination {
    /*分页*/
    margin-left: 50%;
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
</style>