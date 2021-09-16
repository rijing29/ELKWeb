<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        员工刷卡行为分析
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--员工查询输入框 -->
                    <span class="span_area">
                        <el-input v-model="personname" placeholder="请输入员工姓名" style="margin-right: 1%;width: 10%;"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getSelection">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="10" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px"></div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="540"
                                        ref="singleTable"
                                        :data="tableData"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="operationtime" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="function" label="刷卡地点" align="center"></el-table-column>
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
                <!--————表格区域 end————-->
                <!--————柱状图区域 begin————-->
                <el-col :span="14">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————柱状图 begin————-->
                            <v-chart class="echarts" :option="option" />
                            <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————柱状图区域 end————-->
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
import * as echarts from 'echarts/core';
import {
    TitleComponent,
    ToolboxComponent,
    TooltipComponent,
    GridComponent,
    LegendComponent,
    MarkLineComponent,
    MarkPointComponent
} from 'echarts/components';
import {
    BarChart
} from 'echarts/charts';
import {
    CanvasRenderer
} from 'echarts/renderers';

echarts.use(
        [TitleComponent, ToolboxComponent, TooltipComponent, GridComponent, LegendComponent, MarkLineComponent, MarkPointComponent, BarChart, CanvasRenderer]
);
import VChart, { THEME_KEY } from "vue-echarts";
export default {
    name: "epyDQMDS",
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
            personname: '',//员工姓名
            tableData: [
                {operationtime:"2021-08-20 14:31:25",function:"研究院公告"},
                {operationtime:"2021-08-20 13:41:15",function:"项目注册"},
                {operationtime:"2021-08-20 10:41:51",function:"常见问题"},
                {operationtime:"2021-08-19 13:41:15",function:"方案编写"},
                {operationtime:"2021-08-19 10:41:51",function:"待办任务"},
                {operationtime:"2021-08-18 13:41:15",function:"项目注册"},
                {operationtime:"2021-08-17 10:41:51",function:"待办任务"},
                {operationtime:"2021-08-17 10:41:51",function:"项目注册"},
                {operationtime:"2021-08-16 13:41:15",function:"常见问题"},
                {operationtime:"2021-08-16 10:41:51",function:"待办任务"}
            ],//分析表格数据
            currentRow: null,//存储当前点击行信息
            /*————饼状图数据 begin————*/
            option: {
                title: {
                    text: '信息数据统计',
                    // subtext: '时间：',
                    textStyle:{
                        color:"#17caf0"//标题文字颜色
                    },
                    subtextStyle:{
                        color:"#17caf0"//副标题文字颜色
                    },
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    textStyle:{
                        color:"#ffffff",//顶部控制区域文字颜色
                        fontSize: 9
                    },
                    // icon: "circle",
                    left: "20%",//距离左边距离
                    data: ['工作日使用率', '日常功能使用率','加班时间使用率','管理功能使用率','平均每天使用次数','专业功能使用率']
                },
                grid: {left: '2%',right: '4%',bottom: '3%',containLabel: true},
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: false, readOnly: false},
                        magicType: {show: false, type: ['line', 'bar']},
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
                calculable: true,
                xAxis: [
                    {
                        position: "top",//X轴标签位置(顶部或底部)
                        axisLabel:{color:"#ffffff"},//X轴底部标签颜色
                        type: 'value',
                        boundaryGap: [0, 0.01],

                    }
                ],
                yAxis: [
                    {
                        inverse: true,//翻转坐标轴
                        axisLabel:{color:"#ffffff"},//Y轴底部标签颜色
                        type: 'category',
                        data: ['13-08-2021','13-08-2021','13-08-2021','13-08-2021']
                    }
                ],
                series: [
                    {
                        name: '工作日使用率',
                        type: 'bar',
                        data: [87.50, 37.50, 29.17,25.00]
                    },
                    {
                        name: '日常功能使用率',
                        type: 'bar',
                        data: [8.38,2.89,9.71,1.50]
                    },
                    {
                        name: '加班时间使用率',
                        type: 'bar',
                        data: [17.61,42.31,50.00,0.00]
                    },
                    {
                        name: '管理功能使用率',
                        type: 'bar',
                        data: [1.70 ,100.00, 91.18,100.00]
                    },
                    {
                        name: '平均每天使用次数',
                        type: 'bar',
                        data: [96.02, 0.00, 0.00,0.00]
                    },
                    {
                        name: '专业功能使用率',
                        type: 'bar',
                        data: [2.27, 0.00, 8.82,0.00]
                    },
                ]
            },
            /*————饼状图数据 end————*/
        }
    },
    methods:{

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
    width: 90vh;
    height: 70vh;
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