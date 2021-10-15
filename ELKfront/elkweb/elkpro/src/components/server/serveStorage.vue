<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        公共存储日志
                    </div>
                </el-col>
            </el-row>
            <el-row style="margin-top: 3%">
                <!--————表格区域 begin————-->
                <el-col :span="11" style="margin-top: 8px">
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
                                        height="540"
                                        ref="singleTable"
                                        :data="tableData"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}"
                                        highlight-current-row
                                        @current-change="handleCurrentChange">
                                    <el-table-column prop="DIRNAME" sortable label="存储目录" align="center"></el-table-column>
                                    <el-table-column prop="INFO" sortable label="使用量" align="center"></el-table-column>
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
                <!--————饼状图区域 begin————-->
                <el-col :span="13">
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
    TitleComponent,
    ToolboxComponent,
    TooltipComponent,
    GridComponent,
    LegendComponent
} from 'echarts/components';
import {
    LineChart
} from 'echarts/charts';
import {
    CanvasRenderer
} from 'echarts/renderers';

echarts.use(
        [TitleComponent, ToolboxComponent, TooltipComponent, GridComponent, LegendComponent, LineChart, CanvasRenderer]
);
import VChart, { THEME_KEY } from "vue-echarts";
export default {
    name: "serveStorage",
    components: {
        VChart
    },
    data(){
        return{
            haveData: true,
            time: '',//根据此时间查询15天内当前选择目录的存储信息
            currentRow: null,//存储当前点击行信息
            tableData:[],//表格数据
            /*————折线图图数据 begin————*/
            option:{
                title: {
                    text: '存储使用情况图',
                    subtext:'存储目录： /datapool/dqazqm',
                    textStyle:{
                        color:"#17caf0"//标题文字颜色
                    },
                    subtextStyle:{
                        color:"#ffffff"//副标题文字颜色
                    },
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                legend: {
                    textStyle:{
                        color:"#ffffff"//顶部控制区域文字颜色
                    },
                    left: "40%",//距离左边距离
                    data: ['存储使用情况']
                },
                toolbox: {
                    show: false,
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
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        axisLabel:{color:"#ffffff"},//X轴底部标签颜色
                        type: 'category',
                        boundaryGap: false,
                        data: []
                    }
                ],
                yAxis: [
                    {
                        axisLabel:{color:"#ffffff",formatter: '{value} T',},//X轴底部标签颜色
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '存储使用情况',
                        type: 'line',
                        stack: '总量',
                        areaStyle: {},
                        emphasis: {
                            focus: 'series'
                        },
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                    offset: 0,
                                    color: "rgb(246,102,81)" // 0% 处的颜色
                                }, {
                                    offset: 0.6,
                                    color: "rgb(236,53,53)" // 60% 处的颜色
                                }, {
                                    offset: 1,
                                    color: "rgb(245,59,74)" // 100% 处的颜色
                                }], false)
                            },
                        },
                        data: []
                    }
                ]
            },

            /*————折线图数据 end————*/
        }
    },
    created(){//自动渲染数据
        this.getStorage()
    },
    methods:{
        getStorage(){
            var url="/getStorage"
            this.$http.get(url).then(res=> {
                this.tableData=res.data

            })
        },
        handleCurrentChange(val) {//表格点击事件
            if(val!==null){
                this.currentRow = val;
                var url="/getStorageInfo"
                var params={
                    'time':this.time,
                    'DIRNAME':this.currentRow.DIRNAME,
                }
                console.log(this.currentRow.DIRNAME, this.currentRow.INFO)
                this.$http.get(url,{params}).then(res=> {
                    console.log(res.data[0].TIME,"2222222")
                    while (this.option.series[0].data.length!==0){
                        this.option.series[0].data.pop()
                      this.option.xAxis[0].data.pop()
                    }
                    var i;
                    for(i=0;i<res.data.length;i++){
                        this.option.series[0].data.push(res.data[i].USE)
                        this.option.xAxis[0].data.push(res.data[0].TIME)
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
    /*height: 100vh;*/
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
    /*opacity: 0.7;*/
}/*高亮选中行*/
</style>