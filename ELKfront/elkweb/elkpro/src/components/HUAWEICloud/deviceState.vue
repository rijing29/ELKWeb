<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        设备状态统计
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <!--————华为CPU节点负载变化 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————折线图 begin————-->
                            <v-chart class="echarts" :option="option1"/>
                            <!--————折线图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————华为CPU节点负载变化 end————-->
                <!--————华为GPU节点负载变化 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————折线图 begin————-->
                            <v-chart class="echarts" :option="option2"/>
                            <!--————折线图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————华为GPU节点负载变化 end————-->
            </el-row>
            <el-row>
                <!--————存储系统使用 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————饼状图 begin————-->
                            <v-chart class="echarts" :option="option3"/>
                            <!--————饼状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————存储系统使用 end————-->
                <!--————软件使用率 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————柱状图 begin————-->
                            <v-chart class="echarts" :option="option4"/>
                            <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————软件使用率 end————-->
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
import VChart, {THEME_KEY} from "vue-echarts";

export default {
    name: "deviceState",
    components: {
        VChart
    },
    data() {
        return {
            haveData: true,
            date: '',//日期变量
            pages: [//分页信息
                {
                    pageSize: 20,
                    total: 1000,
                    currentPage: 1,
                },
            ],
            time: '',//根据此时间查询分析表
            currentRow: null,//存储当前点击行信息
            tableDataService: [],//表格数据
            tableDataLog: [],
            tableDataTask: [],
            /*————华为CPU节点负载变化 begin————*/
            option1: {
                title: {
                    text: '华为CPU节点负载变化',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#17caf0"//副标题文字颜色
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
                    // left: "40%",//距离左边距离
                    data: ['CPU']
                },
                xAxis: {
                    axisLabel:{color:"#ffffff"},//X轴底部标签颜色
                    type: 'category',
                    data: []
                },
                yAxis: {
                    axisLabel: {
                        color: "#ffffff",//Y轴底部标签颜色
                        formatter: '{value} %'
                    },
                    type: 'value'
                },
                series: [{
                    itemStyle: {
                        normal: {
                            //柱状图颜色(渐变)
                            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                offset: 0,
                                color: "rgb(211,163,246)" // 0% 处的颜色
                            }, {
                                offset: 0.6,
                                color: "rgb(177,105,243)" // 60% 处的颜色
                            }, {
                                offset: 1,
                                color: "rgb(147,125,243)" // 100% 处的颜色
                            }], false)
                        },
                    },
                    data: [16,28,20,18,29,15,25],
                    type: 'line',
                    name:'CPU'
                }],
                grid: {
                    left: '0%',//设置图表距离左边界的距离
                    right: '0%',//设置图表距离右边界的距离
                    top: '20%',//设置图表距离上边界的距离
                    bottom: '0%',//设置图表距离下边界的距离
                    containLabel: true
                }
            },
            option2: {
                title: {
                    text: '华为GPU节点负载变化',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#17caf0"//副标题文字颜色
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
                    // left: "40%",//距离左边距离
                    data: ['GPU']
                },
                xAxis: {
                    axisLabel:{color:"#ffffff"},//X轴底部标签颜色
                    type: 'category',
                    data: []
                },
                yAxis: {
                    axisLabel:{
                        color:"#ffffff",//y轴底部标签颜色
                        formatter: '{value} %'
                    },
                    type: 'value'
                },
                series: [{
                    itemStyle: {
                        normal: {
                            //柱状图颜色(渐变)
                            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                offset: 0,
                                color: "rgb(81,232,246)" // 0% 处的颜色
                            }, {
                                offset: 0.6,
                                color: "rgb(53,236,236)" // 60% 处的颜色
                            }, {
                                offset: 1,
                                color: "rgb(59,202,245)" // 100% 处的颜色
                            }], false)
                        },
                    },
                    name:'GPU',
                    data: [85,123,100,130,96,122,75],
                    type: 'line'
                }],
                grid: {
                    left: '0%',//设置图表距离左边界的距离
                    right: '0%',//设置图表距离右边界的距离
                    top: '20%',//设置图表距离上边界的距离
                    bottom: '0%',//设置图表距离下边界的距离
                    containLabel: true
                }
            },
            option3: {
                title: {
                    text: '存储系统使用',
                    left:'center',
                    textStyle:{
                        color:"#17caf0"//标题文字颜色
                    },
                    subtextStyle:{
                        color:"#17caf0"//副标题文字颜色
                    },
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    textStyle: {
                        color: "#ffffff"//顶部控制区域文字颜色
                    },
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '70%',
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#ffffff',
                            borderWidth: 2
                        },
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        },
                        label: {
                            fontSize: 18
                        },
                        data: [
                            {value: 0, name: '使用'},
                            {value: 0, name: '空闲'},
                        ],
                    }
                ],

            },
            option4: {
                title: {
                    text: '软件使用率',
                    subtext: '',
                    // subtext: '时间：',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#ffffff"//副标题文字颜色
                    },
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    textStyle: {
                        color: "#ffffff"//顶部控制区域文字颜色
                    },
                    left: "20%",//距离左边距离
                    data: ['QPSTM', 'GEOEAST', 'ES360']
                },
                grid: {left: '2%', right: '4%', bottom: '3%', top:'20%',containLabel: true},
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: false, readOnly: false},
                        magicType: {show: false, type: ['line', 'bar']},
                        restore: {show: false},
                        saveAsImage: {
                            //下载图标
                            show: false,
                            emphasis: {
                                iconStyle: {
                                    textFill: "#2791f3"//鼠标放上工具栏提示文字颜色
                                }
                            }
                        },

                    },
                    iconStyle: {
                        //工具栏图标样式
                        borderColor: "#ffffff",//icon边框颜色
                        borderWidth: 1,//icon边框大小
                    },
                    left: "90%",//工具栏距离左边距离
                },
                calculable: true,
                xAxis: [
                    {
                        inverse: true,//翻转坐标轴
                        axisLabel: {
                            color: "#ffffff",//坐标轴标签文字颜色
                            rotate: 0,//坐标轴标签文字选装角度
                            margin: 14,//坐标轴标签文字与轴线距离
                            fontWeight: "lighter",//坐标轴标签文字粗细
                        },
                        type: 'category',
                        data: ['软件使用率'],
                        axisPointer: {
                            type: "line"//坐标轴指示器(line\shadow\none)
                        }
                    },
                ],
                yAxis: [
                    {
                        position: "top",//y轴标签位置(顶部或底部)
                        axisLabel: {
                            color: "#ffffff",//y轴底部标签颜色
                            formatter: '{value} %'
                        },
                        type: 'value',
                        boundaryGap: [0, 0.01],
                        // max:100,//最大刻度值

                    }
                ],
                series: []
            },
            /*————饼状图数据 end————*/
        }
    },
    created() {//自动渲染数据
        this.getEquipState()
    },
    methods: {
        getEquipState() {
            //获取cpu
            var url = "/getCPU"
            this.$http.get(url).then(res => {
                this.option1.series[0].data = res.data.name
                this.option1.xAxis.data = res.data.time
            })
            //获取gpu
            var url = "/getGPU"
            this.$http.get(url).then(res => {
                this.option2.series[0].data = res.data.name
                this.option2.xAxis.data = res.data.time
            })
            //获取软件使用效率
            var url = "/getSoftWareEfficiency" //SoftWareEfficiencyMapper.xml - ELKHWController.java
            this.$http.get(url).then(res => {
                console.log(res.data,"666666")
                while (this.option4.legend.data.length!==0){
                    this.option4.legend.data.pop()
                    this.option4.series.pop()
                }
                for(var i=0;i<res.data.length;i++){
                    this.option4.legend.data.push(res.data[i].softname);
                    this.option4.series.push(
                            {
                                name: res.data[i].softname,
                                type: 'bar',
                                data: [res.data[i].value],
                            }
                    )
                }

            })
            //获取存储系统使用
            var url = "/getCCUsage"//CCUsageMapper.xml - ELKHWController.java
            this.$http.get(url).then(res => {
                console.log(res.data,"jjjjjj")
                this.option3.series[0].data[0].value = res.data[0].USE;
                this.option3.series[0].data[1].value = res.data[0].FREE;
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

.area {
    width: 95%;
    height: 45vh;
    background: #ffffff;
    background: url("../../assets/bg_border.png");
    background-size: 100% 100%;
    padding: 5%;
    margin-left: 10%;
}

.title {
    width: 243px;
    height: 75px;
    font-size: 18px;
    color: #17caf0;
    background: url("../../assets/border_label.png") no-repeat;
    line-height: 75px;
    font-weight: bold;
    text-align: center;
}

.tableSubTitle {
    height: 50px;
    line-height: 70px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
}

.border_top {
    background: url("../../assets/border_top.png");
    background-size: 100% 100%;
    text-align: left;
}

.border_bottom {
    background: url("../../assets/border_bottom.png");
    background-size: 100% 100%;
}

.border_top2 {
    background: url("../../assets/border_top2.png");
    background-size: 100% 100%;
    text-align: left;
}

.border_bottom2 {
    background: url("../../assets/border_bottom2.png");
    background-size: 100% 100%;
}

.el-table {
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
.table-wrapper /deep/ .el-table,
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
</style>