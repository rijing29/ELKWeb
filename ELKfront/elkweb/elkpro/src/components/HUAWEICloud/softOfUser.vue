<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        软件用户数据
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--软件下拉框-->
                    请选择软件：
                    <el-select v-model="soft_value" placeholder="请选择">
                        <el-option v-for="item in soft_options" :key="item.soft_value"
                                   :label="item.label" :value="item.label"></el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-row v-if="haveData">
                <el-col :span="24" class="area">
                    <!--数据展示图-->
                    <v-chart class="echarts" :option="option"/>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
//  echarts引入
import * as echarts from 'echarts/core';
import {
    TitleComponent,
    TooltipComponent,
    GridComponent,
    LegendComponent
} from 'echarts/components';
import {
    BarChart
} from 'echarts/charts';
import {
    CanvasRenderer
} from 'echarts/renderers';

echarts.use(
        [TitleComponent, TooltipComponent, GridComponent, LegendComponent, BarChart, CanvasRenderer]
);
import VChart, {THEME_KEY} from "vue-echarts";
export default {
    name: "softOfUser",
    components: {
        VChart,
        THEME_KEY
    },
    data() {
        return {
            haveData:false,
            soft_options:[
                {soft_value:1,label:'GEOEASTDL'},
                {soft_value:2,label:'PWIN'},
            ],
            soft_value:'GEOEAST',
            option: {
                title: {
                    text: '最近一周软件用户数据',
                    textStyle:{
                        color:"#17caf0"//标题文字颜色
                    },
                    subtextStyle:{
                        color:"#ffffff"//副标题文字颜色
                    },
                },
                tooltip: {trigger: 'axis', axisPointer: {type: 'shadow'}},
                legend: {
                    data: ['软件效率', '节点效率'],
                    textStyle:{
                        color:"#ffffff"//顶部控制区域文字颜色
                    },
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
                grid: {left: '3%', right: '10%', bottom: '3%', containLabel: true},
                xAxis: {
                    name:'使用时间/小时',
                    nameTextStyle: {color: "#ffffff",},
                    axisLabel: {
                        color: "#ffffff",//坐标轴标签文字颜色
                        formatter: '{value} 小时',
                    },
                    type: 'value',
                    boundaryGap: [0, 0.01],
                },
                yAxis: {
                    name:'用户',
                    nameTextStyle: {color: "#ffffff"},
                    axisLabel: {
                        color: "#ffffff",//坐标轴标签文字颜色
                    },
                    type: 'category',
                    data: ['1', '2', '3', '4', '5', '6']
                },
                series: [
                    {
                        name: '效率展示',
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                //柱状图颜色(渐变)
                                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                                    offset: 0,
                                    color: "#26b2ea" // 0% 处的颜色
                                }, {
                                    offset: 0.6,
                                    color: "#35c7ec" // 60% 处的颜色
                                }, {
                                    offset: 1,
                                    color: "#46fcfc" // 100% 处的颜色
                                }], false)
                            },
                            color: null,
                            opacity: 1
                        },
                        data: [1, 2, 3, 4, 5, 6],

                    },
                ]
            }
        }
    },
    created(){
        this.getSoftName()
        this.getSoft_userInfo()
    },
    watch: {
        soft_value: function (newV, oldV) {
            this.soft_value = newV
            console.log(this.soft_value)
            this.getSoft_userInfo()
        },
    },
    methods: {
        getSoftName(){//SoftUserMapper.xml - ElkSoftUserController.java
            var url='/getSoftName'
            this.$http.get(url).then(res =>{
                console.log(res.data)
                while(this.soft_options.length!==0){
                    this.soft_options.pop()
                }
                for(var i=0;i<res.data.length;i++){
                    this.soft_options.push({soft_value: i+1, label: res.data[i].SOFT_NAME})
                }
            })
        },
        getSoft_userInfo(){//SoftUserMapper.xml - ElkSoftUserController.java
            var url = '/getSoft_userInfo'
            var params = {
                'softName':this.soft_value
            }
            this.$http.get(url,{params}).then(res =>{
                this.haveData=true
                console.log(res.data,'软件用户信息')
                while (this.option.yAxis.data.length!==0){
                    this.option.yAxis.data.pop()
                }
                while (this.option.series[0].data.length!==0){
                    this.option.series[0].data.pop()
                }
                for(var i=0;i<res.data.length;i++){
                    this.option.series[0].data.push(res.data[i].USETIME)
                    this.option.yAxis.data.push(res.data[i].USERS)
                }
            })
        }
    },
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

    z-index: 1;
}
.area {
    width: 120vh;
    height: 70vh;
    background: #ffffff;
    background: url("../../assets/bg_border-big.png");
    background-size: 100% 100%;
    margin-left: 9%;
    margin-top: 50px;
    padding: 30px;
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
.el-select{
    width: 12%;
}
</style>