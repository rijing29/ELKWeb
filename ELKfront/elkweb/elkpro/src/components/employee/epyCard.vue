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
                        <el-input v-model="user_lname" placeholder="请输入员工姓名" style="margin-right: 1%;width: 10%;"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getEpyCardInfo">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="10" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">员工 - {{this.name}}</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="540"
                                        ref="singleTable"
                                        :data="tableData"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="JL_SJ" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="DEP_NAME" label="刷卡地点" align="center"></el-table-column>
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
    name: "epyCard",
    components: {
        VChart
    },
    data(){
        return{
            haveData: true,
            date:'',//日期变量
            user_lname: '',//员工姓名
            personname:'',
            name:'',//表格标题区域
            tableData: [],//分析表格数据
            /*————柱状图数据 begin————*/
            option: {
                title: {
                    text: '信息数据统计',
                    subtext:'',
                    // subtext: '时间：',
                    textStyle:{
                        color:"#17caf0"//标题文字颜色
                    },
                    subtextStyle:{
                        color:"#ffffff"//副标题文字颜色
                    },
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    textStyle:{
                        color:"#ffffff"//顶部控制区域文字颜色
                    },
                    left: "35%",//距离左边距离
                    data: ['出勤率', '准时率','加班率']
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
                        max:100,//最大刻度值

                    }
                ],
                yAxis: [
                    {
                        inverse: true,//翻转坐标轴
                        axisLabel:{
                            color:"#ffffff",//坐标轴标签文字颜色
                            rotate: 90,//坐标轴标签文字旋转角度
                            margin: 14,//坐标轴标签文字与轴线距离
                            fontWeight: "lighter",//坐标轴标签文字粗细
                        },
                        type: 'category',
                        data: ['员工刷卡信息数据'],
                        axisPointer: {
                            type: "line"//坐标轴指示器(line\shadow\none)
                        }
                    },

                ],
                series: [
                    {
                        name: '出勤率',
                        type: 'bar',
                        data: [],
                        itemStyle: {
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    }, {
                        name: '准时率',
                        type: 'bar',
                        data: [],
                        itemStyle: {
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    }, {
                        name: '加班率',
                        type: 'bar',
                        data: [],
                        itemStyle: {
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    },
                ]
            },
            /*————柱状图数据 end————*/
        }
    },
    methods:{
        getEpyCardInfo(){//获取表格以及柱状图数据并渲染
            if(this.user_lname!==''){
                var url="/getEpyCardInfo"
                var params={
                    'user_lname':this.user_lname,
                }
                this.$http.get(url,{params}).then(res=>{
                    this.tableData=res.data
                    this.name=res.data[0].USER_LNAME
                    this.personname=this.user_lname
                })
                var url2="/getEpyCardData"
                this.$http.get(url2,{params}).then(res=>{
                    console.log(res.data[0].CQ)
                    this.option.series[0].data=[res.data[0].CQ]
                    this.option.series[1].data=[res.data[0].ZS]
                    this.option.series[2].data=[res.data[0].JB]
                    this.option.title.subtext="员工刷卡地点： "+res.data[0].PLACECOUNT+' 个'
                })
            }
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