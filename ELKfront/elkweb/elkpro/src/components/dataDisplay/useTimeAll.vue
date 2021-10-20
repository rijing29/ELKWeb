<template>
    <!--所有软件使用时间/使用率-->
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24">
                    <!--起始日期下拉框-->
                    起始日期：
                    <el-date-picker
                            v-model="startTime"
                            type="date"
                            placeholder="选择起始日期"
                            format="yyyy/MM/dd"
                            value-format="yyyy/MM/dd"
                            style="margin-right: 1%; width: 12%;"
                            @change="selectStartTime">
                    </el-date-picker>

                    <!--终止日期下拉框-->
                    截止日期：
                    <el-date-picker
                            v-model="stopTime"
                            type="date"
                            placeholder="选择截止日期"
                            format="yyyy/MM/dd"
                            value-format="yyyy/MM/dd"
                            style="margin-right: 1%; width: 12%;"
                            @change="selectStopTime">
                    </el-date-picker>
                    <!-- 查询按钮 -->
                    <span class="span_area">
            <el-button
                    icon="el-icon-search"
                    type="primary"
                    @click="getFrequency">查询</el-button>
          </span>
                </el-col>
            </el-row>
            <el-row v-if="haveData">
                <!--————柱状图区域 begin————-->
                <el-col :span="12"  style="margin-top: 60px">
                    <el-row>
                        <el-col :span="24" class="area">
                                <!--————柱状图 begin————-->
                                <v-chart class="echarts" :option="option" />
                                <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————柱状图区域 end————-->
                <!--————表格区域 begin————-->
                <el-col :span="12">
                    <el-row class="text">
                        <el-col class="title title_border" :span="8" >
                                各软件使用率(%)
                        </el-col>
                        <el-col class="title" :span="16" style="text-align: left">
                            <el-button type="success" round icon="el-icon-top-right" @click="export2Excel" size="mini">导出</el-button>
                        </el-col>
                    </el-row>
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div style="margin-top:20px;margin-left:20px;color: #17caf0">
                                时间：{{this.startTime}}——{{this.stopTime}}
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table :data="tableData" style="width: 100%" :header-cell-style="{color: '#17caf0',fontSize:'16px'}" height="500">
                                    <el-table-column prop="SOFTWARENAME" sortable label="软件" align="center">
                                    </el-table-column>
                                    <el-table-column prop="SUMTIME" sortable label="使用时间(小时)" align="center">
                                    </el-table-column>
                                    <el-table-column prop="FREQUENCY" sortable label="使用率(%)" align="center">
                                    </el-table-column>
                                </el-table>
                                <!--————表格 end————-->
                            </div>
                        </el-col>
                    </el-row>
                    <el-row class="border_bottom">
                        <el-col >
                            <br>
                        </el-col>
                    </el-row>
                </el-col>
                <!--————表格区域 end————-->
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
import VChart, {THEME_KEY} from "vue-echarts";
import moment from "moment";

export default {
    components: {
        VChart
    },
    name: "useTimeAll",
    created(){
      this.getFrequency();
      this.getYesterday();
    },
    data() {
        return {
            startTime: '2021-08-11',
            stopTime: '2021-08-11',
            haveData:false,
            tableData: [],
            option: {
                title: {
                    text: '各软件使用率(%)',
                    subtext: '时间：',
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
                        color:"#ffffff"//顶部控制区域文字颜色
                    },
                    left: "50%",//距离左边距离
                    data: ['软件使用频率', '软件使用率(%)']
                },
                grid: {left: '2%', right: '4%', bottom: '3%', containLabel: true},
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {show: false, readOnly: false},
                        magicType: {show: false, type: ['line', 'bar']},
                        restore: {show: false},
                        saveAsImage: {//下载图标
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
                        axisLabel:{color:"#ffffff",formatter: '{value} %',},//X轴底部标签颜色
                        type: 'value', boundaryGap: [0, 0.01],
                    }
                ],
                yAxis: [
                    {
                        inverse: true,//翻转坐标轴
                        axisLabel:{color:"#ffffff"},//Y轴底部标签颜色
                        type: 'category',
                        data: []
                    }
                ],
                series: [
                    {
                        name: '样例',
                        type: 'bar',
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
                                    color: "#f18a60" // 100% 处的颜色
                                }], false)
                            }
                        },
                        data: []
                    },
                ]
            },
        }
    },
    methods: {
        /*————日期选择器 begin————*/
        selectStartTime(val) {
            //开始时间
            this.startTime = val;
        },
        selectStopTime(val) {
            //截止时间
            this.stopTime = val;
        },
        /*————日期选择器 end————*/
        getFrequency() {
            var url = "/getSumTime"
            var params = {
                'startTime': this.startTime,
                'stopTime': this.stopTime,
            }
            console.log(this.startTime, this.stopTime, "gggg")
            this.$http.get(url, {params}).then(res => {
                this.haveData=true
                /*————渲染表格数据 begin————*/
                var i;
                while (this.tableData.length!=0){
                    this.tableData.pop()
                }
                for (i = 0; i < res.data.softwarename.length; i++) {
                    console.log(res.data.softwarename[i], "kkkkk")
                    this.tableData.push(
                            {
                                SOFTWARENAME: res.data.softwarename[i],
                                SUMTIME: res.data.sumtime[i],
                                FREQUENCY: res.data.frequency[i]
                            })
                }
                /*————渲染表格数据 end————*/

                /*————渲染柱状图数据 begin————*/
                this.option.title.subtext="时间："+this.startTime+"-"+this.stopTime
                this.option.yAxis[0].data=res.data.softwarename.slice(0,10)
                this.option.series[0].data=res.data.frequency.slice(0,10)
                this.option.series[0].name="软件使用率(%)"
                /*————渲染柱状图数据 end————*/
            })
        },
        getYesterday() {
          let obj = {
            starttime: '',
            endtime: ''
          }
          obj.starttime = moment(moment().add(-1, 'days').startOf("day").valueOf()).format("YYYY/MM/DD");
          obj.endtime = moment(moment().add(0, 'days').endOf('day').valueOf()).format('YYYY/MM/DD');
          console.log(obj.endtime,obj.starttime)
          this.startTime=obj.starttime;
          this.stopTime=obj.endtime;
          // return obj
        },
        //导出excel的信息集合
        export2Excel() {
            require.ensure([], () => {
                const {export_json_to_excel} = require("../../tools/Export2Excel.js");
                // 设置自己的excel表头
                const tHeader = [
                    "软件",
                    "使时间",
                    "使用率(%)",
                ]; // 上面设置Excel的表格第一行的标题
                const filterVal = [
                    "SOFTWARENAME",
                    "SUMTIME",
                    "FREQUENCY",
                ]; // client_id client_name client_phone 为tableData的属性
                const list = this.tableData; //把data里的tableData存到list
                const data = this.formatJson(filterVal, list);
                export_json_to_excel(tHeader, data, "全部软件使用时间和使用率excel");
            });
        },
        formatJson(filterVal, jsonData) {
            return jsonData.map(v => filterVal.map(j => v[j]));
        }
    }
}
</script>

<style scoped>
.el-container{
    display: flex;
    flex-wrap: nowrap;
}
.el-main {
    color: #ffffff;
    text-align: center;
    z-index: 1;
}
.el-row {
    background: none;
}
.el-col {
    background: none;
    padding: 10px;
    margin-bottom: 10px;
}
.span_area {
    /*按钮区域*/
    margin-left: 40px;
}
.title{
    color: #17caf0;
    text-align: center;
    font-weight: bold;
    font-size: medium;
    line-height: 40px;
}
.area{
    width: 95%;
    height: 70vh;
    background: #ffffff;
    background: url("../../assets/bg_data.png");
    background-size: 100% 100%;
    padding: 8%;
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
.title_border{
    background: url("../../assets/border_label.png");
    background-size: 100% 100%;
}

.el-table{
    header-align: center;
    border-radius: 4px;
    margin: 1% auto 0;
    width: 90%;
}

/*————表格背景透明 begin————*/
.table-wrapper /deep/  .el-table,
.el-table__expanded-cell {
    background-color: transparent !important;
}
.table-wrapper /deep/ tr, .table-wrapper /deep/ th, .table-wrapper /deep/ td {
    background: none !important;
    color:#fff;
    border-color: #18256f;
}
.table-wrapper /deep/ .el-table__row {
    background: none !important;
    color: #46d4ff;
}
/*————表格背景透明 end————*/

</style>