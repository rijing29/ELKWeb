<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        DQMDS行为分析
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--员工查询输入框 -->
                    <span class="span_area">
                        <el-input v-model="username" placeholder="请输入员工姓名" style="margin-right: 1%;width: 10%;"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getEpyDQMSInfo">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <el-row v-if="haveData">
                <!--————表格区域 begin————-->
                <el-col :span="10" style="margin-top: 1vh">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">员工 - {{this.name}}</div>
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
                                        :header-cell-style="{color: '#17caf0',fontSize:'large'}">
                                    <el-table-column prop="OPERATIONTIME" sortable label="时间" align="center"></el-table-column>
                                    <el-table-column prop="DESCRIPTION" sortable label="使用功能" align="center" width="380"></el-table-column>
                                </el-table>
                                <!--————表格 end————-->
                            </div>
                        </el-col>
                    </el-row>
                    <el-row class="border_bottom">
                        <el-col >
                            <div  class="tableSubTitle2"></div>
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
            <!--————默认显示区域 begin————-->
            <el-row v-if="defaultData">
                <el-col :span="24">
                    <el-row>
                        <el-col :span="24" class="border_top2">
                            <div class="tableSubTitle">全院最近一天平均使用功能次数</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="600"
                                        ref="singleTable"
                                        :data="tableData2"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="DESCRIPTION" sortable label="功能描述" align="center"></el-table-column>
                                    <el-table-column prop="COU" sortable label="全院平均使用次数（次）" align="center"></el-table-column>
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
            character:'',
            haveData: false,
            defaultData:true,
            tableData2:[],
            date:'',//日期变量
            pages: [//分页信息
                {
                    pageSize: 20,
                    total: 1000,
                    currentPage: 1,
                },
            ],
            username: '',//员工姓名
            tableData: [],//表格数据
            currentRow: null,//存储当前点击行信息
            /*————饼状图数据 begin————*/
            option: {
                title: {
                    text: 'DQMDS行为统计',
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
                        color:"#ffffff",//顶部控制区域文字颜色
                        fontSize: 9
                    },
                    // icon: "circle",
                    left: "25%",//距离左边距离
                    data: ['工作日使用率', '日常功能使用率','加班时间使用率','管理功能使用率','专业功能使用率']
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
                yAxis: [
                    {
                        position: "top",//X轴标签位置(顶部或底部)
                        axisLabel:{color:"#ffffff",formatter: '{value} 次'},//X轴底部标签颜色
                        type: 'value',
                        boundaryGap: [0, 0.01],
                        max:100,//最大刻度值
                    }
                ],
                xAxis: [
                    {
                        inverse: true,//翻转坐标轴
                        axisLabel:{
                            color:"#ffffff",//坐标轴标签文字颜色
                            rotate: 0,//坐标轴标签文字旋转角度
                            margin: 14,//坐标轴标签文字与轴线距离
                            fontWeight: "lighter",//坐标轴标签文字粗细
                        },
                        type: 'category',
                        data: ['员工近期DQMDS行为统计']
                    }
                ],
                series: [
                    {
                        name: '工作日使用率',
                        type: 'bar',
                        data: []
                    },
                    {
                        name: '日常功能使用率',
                        type: 'bar',
                        data: []
                    },
                    {
                        name: '加班时间使用率',
                        type: 'bar',
                        data: []
                    },
                    {
                        name: '管理功能使用率',
                        type: 'bar',
                        data: []
                    },
                    {
                        name: '专业功能使用率',
                        type: 'bar',
                        data: []
                    },
                ]
            },
            /*————饼状图数据 end————*/
        }
    },
    created(){
      this.getEpyDQMSInfo();
      this.getDefaultDQMDS()
    },
    methods:{
        getDefaultDQMDS(){
            var url = "/getDefaultDQMDS"
            this.$http.get(url).then(res => {
                console.log(res.data)
                this.tableData2=res.data
            })
        },
        getEpyDQMSInfo(){//获取表格以及柱状图数据并渲染
            this.name=this.username
            if(this.username!==''){
                var url="/getEpyDQMSInfo"
                var params={
                    'username':this.username,
                }
                this.$http.get(url,{params}).then(res=>{
                    console.log(res.data,"88888")
                    this.tableData=res.data
                    // this.name=res.data[0].USERNAME
                    this.haveData=true
                    this.defaultData=false
                })
                var url2="/getEpyDQMDSData"
                this.$http.get(url2,{params}).then(res=>{
                    console.log(res.data,"7777777")
                    this.option.series[0].data=[res.data[0].AVGDAYS]
                    this.option.series[1].data=[res.data[0].ONETYPE]
                    this.option.series[2].data=[res.data[0].JB]
                    this.option.series[3].data=[res.data[0].TWOTYPE]
                    this.option.series[4].data=[res.data[0].THRTYPE]
                    this.option.title.subtext="平均每天使用次数： "+res.data[0].COUNTONEDAY+' 次'
                    switch (res.data[0].CLASSNO){
                        case 1:
                            this.option.xAxis[0].data[0]='岗位特性： 普通型'
                            break
                        case 3:
                            this.option.xAxis[0].data[0]='岗位特性： 普通型'
                            break
                        case 6:
                            this.option.xAxis[0].data[0]='岗位特性： 普通型'
                            break
                        case 9:
                            this.option.xAxis[0].data[0]='岗位特性： 普通型'
                            break
                        case 2:
                            this.option.xAxis[0].data[0]='岗位特性： 专业型'
                            break
                        case 8:
                            this.option.xAxis[0].data[0]='岗位特性： 专业型'
                            break
                        case 4:
                            this.option.xAxis[0].data[0]='岗位特性： 管理型'
                            break
                        case 5:
                            this.option.xAxis[0].data[0]='岗位特性： 管理型'
                            break
                        case 7:
                            this.option.xAxis[0].data[0]='岗位特性： 管理型'
                            break
                    }
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
    width: 95%;
    height: 70vh;
    background: #ffffff;
    background: url("../../assets/bg_data.png");
    background-size: 100% 100%;
    padding: 8%;
    margin-left: 5%;
}
.span_area {
    /*区域*/
    margin-left: 1%;
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
.tableSubTitle{
    height: 50px;
    line-height: 70px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
}
.tableSubTitle2{
    height: 50px;
    line-height: 35px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
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