<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        软件 / 节点效率
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left" style="margin-left: 6%">
                    <!--选择软件名/节点下拉框-->
                    选择查询方式：
                    <el-select v-model="selectMethod_value" placeholder="请选择" @change="SelectChange">
                        <el-option v-for="item in selectMethod_options" :key="item.selectMethod_value"
                                   :label="item.label" :value="item.label" style="margin-right: 2%"></el-option>
                    </el-select>
                    <!--软件名下拉框-->
                    <span v-if="isSoftNameSelect">软件名：</span>
                    <el-select v-model="software_value" placeholder="请选择" v-if="isSoftNameSelect">
                        <el-option v-for="item in software_options" :key="item.software_value" :label="item.label"
                                   :value="item.label"></el-option>
                    </el-select>
                    <!--节点名下拉框-->
                    <span v-if="isNodeTypeSelect">节点名：</span>
                    <el-select v-model="node_value" placeholder="请选择" v-if="isNodeTypeSelect">
                        <el-option v-for="item in node_options" :key="item.node_value" :label="item.label"
                                   :value="item.label"></el-option>
                    </el-select>
                    <!--节点ID下拉框-->
                    <span v-if="isNodeTypeSelect">节点ID：</span>
                    <el-select v-model="nodeID_value" placeholder="请选择" v-if="isNodeTypeSelect">
                        <el-option v-for="item in nodeID_options" :key="item.nodeID_value" :label="item.label"
                                   :value="item.label"></el-option>
                    </el-select>
                    <!--         年         -->
                    <span class="demonstration">年</span>
                    <el-date-picker v-model="year_value" type="year" placeholder="选择年"
                                    style="width: 15%;margin-right: 2%"
                                    :disabled="year_disabled" value-format="yyyy"></el-date-picker>
                    <!--         月         -->
                    <span class="demonstration">月</span>
                    <el-date-picker v-model="month_value" type="month" placeholder="选择月" style="width: 15%"
                                    :disabled="month_disabled" value-format="yyyy-MM"></el-date-picker>
                    <!--                    起始日期：-->
                    <!--                    <el-date-picker-->
                    <!--                            v-model="begin"-->
                    <!--                            type="date"-->
                    <!--                            placeholder="选择起始日期"-->
                    <!--                            format="yyyy/MM/dd"-->
                    <!--                            value-format="yyyy/MM/dd HH:mm:ss"-->
                    <!--                            style="margin-right: 1%; width: 10%;"-->
                    <!--                            @change="selectStartTime">-->

                    <!--                    </el-date-picker>-->
                    <!--                    终止日期：-->
                    <!--                    <el-date-picker-->
                    <!--                            v-model="end"-->
                    <!--                            type="date"-->
                    <!--                            placeholder="选择终止日期"-->
                    <!--                            format="yyyy/MM/dd"-->
                    <!--                            value-format="yyyy/MM/dd HH:mm:ss"-->
                    <!--                            style="margin-right: 1%; width: 10%;"-->
                    <!--                            @change="selectStopTime">-->

                    <!--                    </el-date-picker>-->
                    <!--按钮-->
                    <el-button v-if="isSoftNameSelect" icon="el-icon-search" type="primary"
                               @click="getSoftNameEfficiency" v-loading.fullscreen.lock="fullscreenLoading">软件效率
                    </el-button>
                    <el-button v-if="isNodeTypeSelect" icon="el-icon-search" type="primary"
                               @click="getNodeTypeEfficiency" v-loading.fullscreen.lock="fullscreenLoading">节点效率
                    </el-button>
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
//开始渲染
export default {
    name: 'datainfo',
    components: {
        VChart,
        THEME_KEY
    },
    watch: {
        software_value: function (newV, oldV) {
            this.softName = newV
        },
        node_value: function (newV, oldV) {
            this.nodeType = newV
        },
        nodeID_value: function (newV, oldV) {
            this.nodeId = newV
        },
        year_value: function (newV, oldV) {
            this.year_value = newV
            if (this.year_value != null) {
                this.month_disabled = true;
                this.month_value = null
            } else {
                this.month_disabled = false;
            }
        },
        month_value: function (newV, oldV) {
            this.month_value = newV
            if (this.month_value != null) {
                this.year_disabled = true;
                this.year_value = null
            } else {
                this.year_disabled = false;
            }
        }
    },
    data() {
        return {
            haveData:false,
            fullscreenLoading: false,
            softName: '',
            nodeType: '',
            nodeId: '',
            month_value: '',
            year_value: '',
            isSoftNameSelect: false,
            isNodeTypeSelect: false,
            tableData: [],
            Time: '',
            year_disabled: false,
            month_disabled: false,
            selectMethod_options: [
                {selectMethod_value: '选项1', label: '按软件名查询'},
                {selectMethod_value: '选项2', label: '按节点查询'}
            ],
            selectMethod_value: '',
            //软件名下拉框值
            software_options: [
                {software_value: '选项1', label: 'GEOEAST'},
                {software_value: '选项2', label: 'GEOEASTDL'},
                {software_value: '选项3', label: 'Pardiam'},
                {software_value: '选项4', label: 'WCC'},
                {software_value: '选项5', label: 'PSTM'},
                {software_value: '选项6', label: 'PARADIGMDL'},
                {software_value: '选项7', label: 'ZHIKONG'},
                {software_value: '选项8', label: 'TOMODEL'},
                {software_value: '选项9', label: 'PARADIGM'},
                {software_value: '选项10', label: 'PSTM'},
                {software_value: '选项11', label: 'GEOEAST'},
                {software_value: '选项12', label: 'GEOEASTDL'},
                {software_value: '选项13', label: 'Geoeast'}
            ],
            software_value: '',
            //节点名下拉框值
            node_options: [
                {node_value: '选项1', label: 'hwnode'},
                {node_value: '选项2', label: 'hwgnode'}
            ],
            node_value: '',
            //节点ID下拉框值
            nodeID_options: [
                {nodeID_value: '选项1', label: '1'},
                {nodeID_value: '选项2', label: '2'},
                {nodeID_value: '选项3', label: '3'}
            ],
            nodeID_value: '',
            begin: '',
            end: '',
            option: {
                title: {
                    text: '节点利用率',
                    subtext: '数据来自数据库统计',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#ffffff"//副标题文字颜色
                    },
                },
                tooltip: {trigger: 'axis', axisPointer: {type: 'shadow'}},
                legend: {
                    data: ['软件效率', '节点效率'],
                    textStyle: {
                        color: "#ffffff"//顶部控制区域文字颜色
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
                    iconStyle: {
                        //工具栏图标样式
                        borderColor: "#ffffff",//icon边框颜色
                        borderWidth: 1,//icon边框大小
                    },
                    left: "90%",//工具栏距离左边距离
                },
                grid: {left: '3%', right: '4%', bottom: '3%', containLabel: true},
                yAxis: {
                    axisLabel: {
                        color: "#ffffff",//坐标轴标签文字颜色
                    },
                    type: 'value',
                    boundaryGap: [0, 0.01],
                },
                xAxis: {
                    axisLabel: {
                        color: "#ffffff",//坐标轴标签文字颜色
                    },
                    type: 'category',
                    data: ['1', '2', '3', '4', '5', '6']
                },
                series: [
                    {
                        name: '效率展示',
                        type: 'line',
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
                        data: [1, 2, 3, 4, 5, 6],
                    },
                ]
            }
        }
    },
    created() {
    },
    methods: {
        SelectChange(val) {
            console.log(val)
            if (val == '按软件名查询') {
                this.isSoftNameSelect = true;
                this.isNodeTypeSelect = false;
            } else {
                this.isNodeTypeSelect = true;
                this.isSoftNameSelect = false;
            }
        },
        selectStartTime(val) {
            this.startTime = val;
        },
        selectStopTime(val) {
            this.stopTime = val;
        },
        //  根据软件名查效率
        getSoftNameEfficiency() {
            this.fullscreenLoading = true;
            setTimeout(() => {
                this.fullscreenLoading = false;
            }, 2000);
            //传入哪个时间
            console.log("年：",this.year_value,"月：",this.month_value)
            if (this.year_value!==null) {
                this.Time = this.year_value;
                if(this.Time.length!==0){
                    var url = "/calSoftNameYear"
                    var params = {
                        'softName': this.softName,
                        'Time': this.Time,
                    }
                    this.$http.get(url, {params}).then(res => {
                        this.haveData=true
                        //设置横轴
                        this.option.series[0].name = "软件效率"
                        this.option.series[0].data = res.data.value
                        //设置纵轴
                        this.option.xAxis.data = res.data.key
                    })
                }

            } else if (this.month_value !== null) {
                this.Time = this.month_value;
                if(this.Time.length!==0){
                    var url = "/calSoftName"
                    var params = {
                        'softName': this.softName,
                        'Time': this.Time,
                    }
                    this.$http.get(url, {params}).then(res => {
                        this.haveData=true
                        console.log(res)
                        //设置横轴
                        this.option.series[0].name = "软件效率"
                        this.option.series[0].data = res.data.value
                        //设置纵轴
                        this.option.xAxis.data = res.data.key
                        // this.option.title.subtext='平均效率：'+res.data.ave
                    })
                }

            }
        },
        //  根据节点名查效率
        getNodeTypeEfficiency() {
            this.fullscreenLoading = true;
            setTimeout(() => {
                this.fullscreenLoading = false;
            }, 2000);
            //传入哪个时间
            if (this.year_value !== null) {
                console.log(this.year_value, "nian")
                this.Time = this.year_value;
                if(this.Time.length!==0){
                    var url = "/calNodeTypeYear"
                    var params = {
                        'nodeType': this.nodeType,
                        'nodeId': this.nodeId,
                        'Time': this.Time,
                    }
                    this.$http.get(url, {params}).then(res => {
                        this.haveData=true
                        console.log(res)
                        //设置横轴
                        this.option.series[0].name = "软件效率"
                        this.option.series[0].data = res.data.value
                        //设置纵轴
                        this.option.xAxis.data = res.data.key
                        //
                    })
                }
            } else if (this.month_value !== null) {
                console.log(this.Time)
                this.Time = this.month_value;
                if(this.Time.length!==0){
                    var url = "/calNodeType"
                    var params = {
                        'nodeType': this.nodeType,
                        'nodeId': this.nodeId,
                        'Time': this.Time,
                    }
                    this.$http.get(url, {params}).then(res => {
                        console.log(res)
                        //设置横轴
                        this.option.series[0].name = "节点效率"
                        this.option.series[0].data = res.data.value
                        //设置纵轴
                        this.option.xAxis.data = res.data.key
                        // this.option.title.subtext='平均效率：'+res.data.ave
                    })
                }
            }
        }
    },
}
</script>

<style scoped>
.el-main {
    color: #ffffff;
    text-align: center;
    z-index: 1;
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

.el-select {
    margin-right: 1%;
    width: 10%;
    line-height: 10px;
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


</style>