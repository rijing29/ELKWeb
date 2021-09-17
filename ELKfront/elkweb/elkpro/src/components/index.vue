<template>
    <el-container>
        <el-header>资源管理系统</el-header>
        <el-main>
            <el-row style="z-index: 1">
                <!--————雷达图区域 begin————-->
                <el-col :span="8">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————雷达图 begin————-->
                            <v-chart class="echarts" :option="option3" />
                            <!--————雷达图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————雷达图区域 end————-->
                <el-col :span="8"></el-col>
                <!--————右上角区域 begin————-->
                <el-col :span="8" style="float: right;">
                    <el-row>
                        <el-col :span="24"class="time_area" align="left">
                            <el-row>
                                <el-col :span="6"align="center">
                                        <el-row class="text_big"><el-col>{{this.hours}}:{{this.minutes}}</el-col></el-row>
                                        <el-row class="text_small"><el-col>{{this.week}}</el-col></el-row>
                                </el-col>
                                <el-col :span="1"><div  class="center bg_line"></div></el-col>
                                <el-col :span="9" style="margin-left: 20px;line-height: 7vh"class="text_light_blue text_blod">{{this.date}}</el-col>
                                <el-col :span="8"><el-button type="primary" round icon="el-icon-menu" @click="Home">主 页</el-button></el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" class="num_ares">
                            <el-row>
                                <el-col :span="24" align="left" class="text_blod text_light_blue text_title left">告警/任务信息</el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="8" align="center">
                                    <div class="bg_num">2</div>
                                </el-col>
                                <el-col :span="8" align="center">
                                    <div class="bg_num">4.2</div>
                                </el-col>
                                <el-col :span="8" align="center">
                                    <div class="bg_num">2</div>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="8" align="center" class="text_light_blue text_blod">IPMI告警台数</el-col>
                                <el-col :span="8" align="center" class="text_light_blue text_blod">平均执行任务个数</el-col>
                                <el-col :span="8" align="center" class="text_light_blue text_blod">
                                    Agent故障个数<br><div class="text_small text_white text_lighter text_thin" >(部署Agent 352 个)</div></el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-col>
                <!--————右上角区域 end————-->
            </el-row>
            <el-row style="z-index: 1">
                <!--————柱状图区域 begin————-->
                <el-col :span="8">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————柱状图 begin————-->
                            <v-chart class="echarts" :option="option" />
                            <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————柱状图区域 end————-->
                <!--————走马灯 begin————-->
                <el-col :span="7">
                    <el-row>
                        <el-col :span="24" style="height: 19vh"></el-col>
                    </el-row>
                    <el-row class="word_area">
                        <el-col :span="24">
                            <div class="block">
                                <el-carousel height="20vh">
                                    <el-carousel-item v-for="item in dataWord" :key="item.title">
                                        <el-row>
                                            <el-col class="text_blod text_light_blue text_title">{{item.title}}</el-col>
                                        </el-row>
                                        <el-row>
                                            <el-col v-for="(items,index) in item.data" :key="index" style="line-height: 20px;margin-top: 15px;" class="text_title">{{items}}</el-col>
                                        </el-row>
                                    </el-carousel-item>
                                </el-carousel>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
                <!--————走马灯 end————-->
                <!--————柱状图区域 begin————-->
                <el-col :span="8" style="margin-left: 40px;float: right;">
                    <el-row>
                        <el-col :span="24" class="area" align="center">
                            <!--————柱状图 begin————-->
                            <v-chart class="echarts" :option="option2" />
                            <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————柱状图区域 end————-->
            </el-row>
        </el-main>
        <span class="bg_icon"></span>
        <span class="bg_icon2"></span>
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
    RadarChart
} from 'echarts/charts';
import {
    CanvasRenderer
} from 'echarts/renderers';

echarts.use(
        [TitleComponent, ToolboxComponent, TooltipComponent,RadarChart, GridComponent, LegendComponent, MarkLineComponent, MarkPointComponent, BarChart, CanvasRenderer]
);
import VChart, { THEME_KEY } from "vue-echarts";
export default {
    name: "home",
    components: {
        VChart
    },
    data(){
        return{
            data:'',
            hours:'',
            minutes:'',
            week:'',
            dataWord:[
                {
                    title:'日志采集量统计',
                    data: [
                        '服务器：130台，系统日志13万项，状态采集7万项',
                        'DQMDS日志：员工1031人，访问日志1.7万项',
                        '门禁刷卡：员工953人，刷卡0.31万次',
                        '软件许可：15个软件，127个用户，4178次使用'
                    ],
                },
                {
                    title: '网络',
                    data: [
                        '网络交换机132台，防火墙1个',
                        '网络包：今日326万，涉及417个IP',
                        'IPS告警：213万项，涉及31个服务器',
                        '交换机告警：7万项，涉及2个交换机'
                    ],
                }
            ],
            haveData: true,
            date:'',//日期变量
            user_lname: '',//员工姓名
            personname:'',
            name:'',//表格标题区域
            tableData: [],//分析表格数据
            /*————柱状图数据 begin————*/
            option: {
                title: {
                    text: '全院刷卡平均信息',
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
                            rotate: 90,//坐标轴标签文字选装角度
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
                        data: [95],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                    offset: 0,
                                    color: "#6155fa" // 0% 处的颜色
                                }, {
                                    offset: 0.6,
                                    color: "#5685fc" // 60% 处的颜色
                                }, {
                                    offset: 1,
                                    color: "#6294f9" // 100% 处的颜色
                                }], false)
                            },
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    }, {
                        name: '准时率',
                        type: 'bar',
                        data: [96],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                    offset: 0,
                                    color: "#2baf85" // 0% 处的颜色
                                }, {
                                    offset: 0.6,
                                    color: "#79d8b3" // 60% 处的颜色
                                }, {
                                    offset: 1,
                                    color: "#8ce5b8" // 100% 处的颜色
                                }], false)
                            },
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    }, {
                        name: '加班率',
                        type: 'bar',
                        data: [32],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                    offset: 0,
                                    color: "#e55c60" // 0% 处的颜色
                                }, {
                                    offset: 0.6,
                                    color: "#dd689f" // 60% 处的颜色
                                }, {
                                    offset: 1,
                                    color: "#eb7dbb" // 100% 处的颜色
                                }], false)
                            },
                            color: null,
                            opacity: 1
                        },
                        barGap: "60%"//不同系列的柱间距离
                    },
                ]
            },
            option2: {
                title: {
                    text: '服务器平均负载',
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
                    data: ['网络负载', '内存负载','CPU负载']
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
                        // max:100,//最大刻度值

                    }
                ],
                yAxis: [
                    {
                        inverse: true,//翻转坐标轴
                        axisLabel:{
                            color:"#ffffff",//坐标轴标签文字颜色
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
                        name: '网络负载',
                        type: 'bar',
                        data: [11.5],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
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
                        name: '内存负载',
                        type: 'bar',
                        data: [8.2],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
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
                        name: 'CPU负载',
                        type: 'bar',
                        data: [12.0],
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
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
            /*————柱状图数据 end————*/
            /*————雷达图数据 begin————*/
            option3:{
                title: {
                    text: '刷卡信息图',
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
                    left: "30%",//距离左边距离
                    data: ['开发研究三室', '地震处理研究室', '计算机网络研究室', '院领导']
                },
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
                radar: [
                    {
                        indicator: [
                            {text: '出勤率', max: 100},
                            {text: '准时率', max: 100},
                            {text: '加班率', max: 100}
                        ],
                        center: ['50%', '70%'],
                        radius: 160
                    }
                ],
                series: [
                    {
                        label: {
                            show: true,
                            formatter: function (params) {
                                return params.value;
                            }
                        },
                        type: 'radar',
                        tooltip: {
                            trigger: 'item'
                        },
                        areaStyle: {},
                        data: [
                            {value: [73, 54, 52], name: '开发研究三室'},
                            {value: [76, 88, 34], name: '地震处理研究室'},
                            {value: [80, 54, 42], name: '计算机网络研究室'},
                        ]
                    }
                ]
            },
            /*————雷达图数据 end————*/
        }
    },
    created(){
        this.getDate()
    },
    mounted() {
        let that= this;
        this.timer = setInterval(function() {
            that.hours = new Date().getHours();
            that.minutes=new Date().getMinutes();
        });
    },
    beforeDestroy: function() {
        if (this.time) {
            clearInterval(this.time);
        }
    },
    methods:{
        Home(){
            this.$router.push({name:'home',params:{isVisible:'true'}})//向home页面传递isVisible,控制背景icon显示
        },
        getDate(){
            var vWeek = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]
            var DATE = this.formatter(new Date(), 'yyyy年MM月dd日')
            var WEEK = new Date()
            this.date=DATE.toLocaleString()
            this.week=vWeek[WEEK.getDay()]
            console.log(this.week)
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
    },

}
</script>

<style scoped>
.el-container, .el-main{
    background: #000a40;
}
.el-header {
    background: url("../assets/bg_header.png");
    background-size:100% 100%;
    font-size: 20px;
    font-weight: bold;
    color: #caf6f6;
    text-align: center;
    letter-spacing: 10px;
    line-height: 60px;
    margin-top: 20px;
}
.el-main {
    color: #ffffff;
    text-align: center;
    padding: 0;
}
.bg_icon{
    position: absolute;
    width: 91vh;
    height: 56vh;
    background: url("../assets/bg_star.png");
    background-size: 100% 100%;
    margin: auto;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    opacity: 0.9;
    z-index: 0;
}
.bg_icon2{
    position: absolute;
    width: 50vh;
    height: 50vh;
    background: url("../assets/bg_earth3.png");
    background-size: 100% 100%;
    margin: auto;
    right: 0;
    left: 0;
    top: 160px;
    opacity: 1;
    z-index: 0;
}
.center{
    position: absolute;
    margin: auto;
    top:0;
    bottom: 0;
}
.area{
    width: 62vh;
    height: 45vh;
    background: #ffffff;
    background: url("../assets/bg_border.png");
    background-size: 100% 100%;
    padding: 40px;
    margin-left: 20px;
}
.left{
    padding: 10px 20px;
}
.time_area{
    background:url("../assets/bg_border-narrow.png");
    background-size: 100% 100%;
    height: 10vh;
    width: 62vh;
    margin-top: 20px;
    margin-left: 20px;
    color: #ffffff;
    padding: 16px;

}
.bg_num{
    background:url("../assets/bg_num.png");
    background-size: 100% 100%;
    width: 20vh;
    height: 20vh;
    line-height: 20vh;
    text-align: center;
    color: #fa916c;
    font-size: 48px;
    font-weight: bold;
}
.num_ares{
    margin-top: 20px;
    margin-left: 20px;
    background:url("../assets/bg_border-width.png");
    background-size: 100% 100%;
    height: 30vh;
    width: 62vh;
}
.word_area{
    background: url("../assets/bg_word.png");
    background-size: 100% 100%;
    width: 66vh;
    height: 25vh;
}
.text_big{
    font-size: 36px;
    font-weight: bold;
}
.text_title{
    font-size: 18px;
}
.text_small{
    font-size: 12px;
}
.text_light_blue{
    color: #17b3f0;
}
.text_white{
    color: #ffffff;
}
.text_thin{
    font-weight: lighter;
}
.text_blod{
    font-weight: bold;
}
.bg_line{
    background:url("../assets/line.png");
    background-size: 100% 100%;
    width: 3px;
    height: 6vh;
}
.el-button{
    box-shadow: 0px 0px 20px #17caf0;
    background: #17b3f0;
    width: 100px;
    height: 40px;
    position: absolute;
    margin: auto;
    right: 0;
    bottom: 0;
    top: 0

}
.el-button:hover{
    background: #2ac0fa;
}

.title{
    width: 243px;
    height: 75px;
    font-size: 18px;
    color: #17caf0;
    background: url("../assets/border_label.png") no-repeat;
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
/*————走马灯 begin————*/
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    margin: 0;
    line-height: 150px;
}
.el-carousel{
    width: 60vh;
    height: 20vh;
    position: absolute;
    margin: auto;
    left: 0;
    right: 0;
    bottom: 0;
    top: 0;
}
.el-carousel__item:nth-child(2n) {
    background-color: #000a40;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #000a40;
}
/*————走马灯 end————*/
</style>