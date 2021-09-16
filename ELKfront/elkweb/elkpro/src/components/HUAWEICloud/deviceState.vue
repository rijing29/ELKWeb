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
                <!--————近期（一个月）Windows服务器提供服务统计 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————雷达图 begin————-->
                            <v-chart class="echarts" :option="option1"/>
                            <!--————雷达图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————近期（一个月）Windows服务器提供服务统计 end————-->
                <!--————Windows服务器日志告警信息 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————雷达图 begin————-->
                            <v-chart class="echarts" :option="option2"/>
                            <!--————雷达图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————Windows服务器日志告警信息 end————-->
            </el-row>
            <el-row>
                <!--————近期（一个月）Windows服务器提供服务统计 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————雷达图 begin————-->
                            <v-chart class="echarts" :option="option3"/>
                            <!--————雷达图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————近期（一个月）Windows服务器提供服务统计 end————-->
                <!--————Windows服务器日志告警信息 begin————-->
                <el-col :span="11">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————柱状图 begin————-->
                            <v-chart class="echarts" :option="option4"/>
                            <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————Windows服务器日志告警信息 end————-->
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
            /*————饼状图数据 begin————*/
            option1: {
                title: {
                    text: 'Node节点状态',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#17caf0"//副标题文字颜色
                    },
                },
                legend: {
                    top: 'bottom',
                    textStyle: {
                        color: "#ffffff"//顶部控制区域文字颜色
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
                    iconStyle: {
                        //工具栏图标样式
                        borderColor: "#ffffff",//icon边框颜色
                        borderWidth: 1,//icon边框大小
                    },
                    left: "90%",//工具栏距离左边距离
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['40%', '80%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#ffffff',
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
                        labelLine: {show: true,},
                        cursor: "pointer",
                        data: [
                            {value: 92, name: '正常'},
                            {value: 8, name: '异常'},
                        ],
                    }
                ],
                labelLine: {
                    show: true
                },
                label: {
                    alignTo: "labelLine"
                }
            },
            option2: {
                title: {
                    text: 'GPU节点状态',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#17caf0"//副标题文字颜色
                    },
                },
                legend: {
                    top: 'bottom',
                    textStyle: {
                        color: "#ffffff"//顶部控制区域文字颜色
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
                    iconStyle: {
                        //工具栏图标样式
                        borderColor: "#ffffff",//icon边框颜色
                        borderWidth: 1,//icon边框大小
                    },
                    left: "90%",//工具栏距离左边距离
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['40%', '80%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#ffffff',
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
                        labelLine: {show: true,},
                        cursor: "pointer",
                        data: [
                            {value: 95, name: '正常'},
                            {value: 5, name: '异常'},
                        ],
                    }
                ],
                labelLine: {
                    show: true
                },
                label: {
                    alignTo: "labelLine"
                }
            },
            option3: {
                title: {
                    text: '存储系统使用',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#17caf0"//副标题文字颜色
                    },
                },
                legend: {
                    top: 'bottom',
                    textStyle: {
                        color: "#ffffff"//顶部控制区域文字颜色
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
                    iconStyle: {
                        //工具栏图标样式
                        borderColor: "#ffffff",//icon边框颜色
                        borderWidth: 1,//icon边框大小
                    },
                    left: "90%",//工具栏距离左边距离
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['40%', '80%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#ffffff',
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
                        labelLine: {show: true,},
                        cursor: "pointer",
                        data: [
                            {value: 92, name: '使用'},
                            {value: 8, name: '空闲'},
                        ],
                    }
                ],
                labelLine: {
                    show: true
                },
                label: {
                    alignTo: "labelLine"
                }
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
                    left: "35%",//距离左边距离
                    data: ['PSBN', 'Paradlag', 'GeoEast处理']
                },
                grid: {left: '2%', right: '4%', bottom: '3%', containLabel: true},
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
                        position: "top",//X轴标签位置(顶部或底部)
                        axisLabel: {color: "#ffffff"},//X轴底部标签颜色
                        type: 'value',
                        boundaryGap: [0, 0.01],
                        // max:100,//最大刻度值

                    }
                ],
                yAxis: [
                    {
                        inverse: true,//翻转坐标轴
                        axisLabel: {
                            color: "#ffffff",//坐标轴标签文字颜色
                            rotate: 90,//坐标轴标签文字选装角度
                            margin: 14,//坐标轴标签文字与轴线距离
                            fontWeight: "lighter",//坐标轴标签文字粗细
                        },
                        type: 'category',
                        data: ['服务器负载数据'],
                        axisPointer: {
                            type: "line"//坐标轴指示器(line\shadow\none)
                        }
                    },

                ],
                series: [
                    {
                        name: 'PSBN',
                        type: 'bar',
                        data: [45.6],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                                    {//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                        offset: 0,
                                        color: "#26b2ea" // 0% 处的颜色
                                    }, {
                                        offset: 0.6,
                                        color: "#35b8ec" // 60% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: "#46c8fc" // 100% 处的颜色
                                    }], false)
                            },
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    }, {
                        name: 'Paradlag',
                        type: 'bar',
                        data: [35.8],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                                    {//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                        offset: 0,
                                        color: "#ea6026" // 0% 处的颜色
                                    }, {
                                        offset: 0.6,
                                        color: "#ec6b35" // 60% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: "#fc7b46" // 100% 处的颜色
                                    }], false)
                            },
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    }, {
                        name: 'GeoEast处理',
                        type: 'bar',
                        data: [85.2],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                                    {//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                        offset: 0,
                                        color: "#6b26ea" // 0% 处的颜色
                                    }, {
                                        offset: 0.6,
                                        color: "#8135ec" // 60% 处的颜色
                                    }, {
                                        offset: 1,
                                        color: "#9246fc" // 100% 处的颜色
                                    }], false)
                            },
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    },
                ]
            },
            /*————饼状图数据 end————*/
        }
    },
    created() {//自动渲染数据
        this.getWinWaring()
    },
    methods: {
        dateForma: function (row, column) {//表格行格式化时间
            var date = row[column.property];
            if (date === undefined) {
                return ''
            }
            ;
            return moment(date).format("YYYY-MM-DD")
        },
        getDate() {//获取当前时间
            var date = this.formatter(new Date(), 'yyyy-MM-dd hh:mm:ss')
            this.date = date.toLocaleString()
        },
        getWinWaring() {//渲染数据
            var url = "/getWinWaring"
            this.$http.get(url).then(res => {//渲染Windows服务器告警表格数据
                this.tableDataLog = res.data
                console.log(res.data)
            })
            var url1 = '/getWinServiceInfo'
            this.$http.get(url1).then(res => {//渲染Windows服务器日志告警数据
                this.tableDataService = res.data
            })
            var url2 = '/getWinServiceJobInc'
            this.$http.get(url2).then(res => {//渲染Windows服务器任务增长分析
                this.tableDataTask = res.data
                console.log(res.data)
            })
            var url3 = '/getWinLoad'
            this.$http.get(url3).then(res => {//渲染Windows服务器负载统计饼状图
                console.log(res.data[0].ALLCOUNT - res.data[0].HIGHCOUNT, "22222222")
                this.option.series[0].data[0].value = res.data[0].HIGHCOUNT
                this.option.series[0].data[1].value = res.data[0].ALLCOUNT - res.data[0].HIGHCOUNT
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

.area {
    width: 70vh;
    height: 45vh;
    background: #ffffff;
    background: url("../../assets/bg_border.png");
    background-size: 100% 100%;
    padding: 40px;
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
</style>