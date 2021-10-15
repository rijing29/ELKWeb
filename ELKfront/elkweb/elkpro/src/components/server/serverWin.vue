<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        Windows服务器日志
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <!--————近期（一个月）Windows服务器提供服务统计 begin————-->
                <el-col :span="13" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">Windows服务器提供服务统计</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="500"
                                        ref="singleTable"
                                        :data="tableDataService"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="IP" sortable label="IP" align="center"></el-table-column>
                                    <el-table-column prop="TYPE2" sortable label="交互类任务（个）" align="center"></el-table-column>
                                    <el-table-column prop="TYPE3" sortable label="共享类任务（个）" align="center"></el-table-column>
                                    <el-table-column prop="TYPE5" sortable label="服务类任务（个）" align="center"></el-table-column>
                                    <el-table-column prop="TYPE10" sortable label="远程交互任务（个）" align="center"></el-table-column>
                                    <el-table-column prop="TIME" :formatter="dateForma" sortable label="服务日期" align="center"></el-table-column>
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
                <!--————近期（一个月）Windows服务器提供服务统计 end————-->
                <!--————Windows服务器日志告警信息 begin————-->
                <el-col :span="10" style="margin-top: 8px;margin-left: 40px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">Windows服务器日志告警信息</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="500"
                                        ref="singleTable"
                                        :data="tableDataLog"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" :formatter="dateForma" sortable label="时间" align="center"></el-table-column>
                                    <el-table-column prop="IP" sortable label="IP" align="center"></el-table-column>
                                    <el-table-column prop="WARNINFO" sortable label="提示信息" align="center"></el-table-column>
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
                <!--————Windows服务器日志告警信息 end————-->
            </el-row>
            <el-row>
                <!--————近期（一个月）Windows服务器任务增长分析 begin————-->
                <el-col :span="13" style="margin-top: 2%">
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
                                        height="500"
                                        ref="singleTable"
                                        :data="tableDataTask"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16xp'}">
                                    <el-table-column prop="TIME" sortable label="时间" align="center"></el-table-column>
                                    <el-table-column prop="IP" sortable label="IP" align="center"></el-table-column>
                                    <el-table-column prop="INC_VALUE" sortable label="任务增长量（个）" align="center"></el-table-column>
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
                <!--————近期（一个月）Windows服务器任务增长分析 end————-->
                <!--————饼状图区域 begin————-->
                <el-col :span="10">
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
import moment from 'moment'
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
import VChart, { THEME_KEY } from "vue-echarts";
export default {
    name: "serverWin",
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
            time: '',//根据此时间查询分析表
            currentRow: null,//存储当前点击行信息
            tableDataService:[],//表格数据 负载数据统计
            tableDataLog:[],
            tableDataTask:[],
            /*————饼状图数据 begin————*/
            option : {
                title: {
                    text: '负载数据统计',
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
                    orient: "horizontal",
                    top: 'bottom',
                    textStyle: {
                        color: "#ffffff"//顶部控制区域文字颜色
                    },
                },
                series: [
                    {
                        name: '访问来源',
                        data: [],
                        type: 'pie',
                        radius: ['30%','70%'],
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
                            color:'#17caf0',
                            textBorderWidth: 0,
                            alignTo: 'edge',
                            formatter: '{name|{b}}\n{time|{c} 个}',
                            minMargin: 5,
                            edgeDistance: "5%",
                            lineHeight: 15,
                            rich: {
                                time: {
                                    fontSize: 10,
                                    color: '#ffffff'
                                }
                            },
                            labelLine: {
                                length: 35,
                                length2: 0,
                                maxSurfaceAngle: 80
                            },
                            fontSize: 18,
                        }

                    }
                ]
            },
            /*————饼状图数据 end————*/
        }
    },
    created(){//自动渲染数据
        this.getWinWaring()
    },
    methods:{
        dateForma:function(row,column){//表格行格式化时间
            var date = row[column.property];
            if(date === undefined){return ''};
            return moment(date).format("YYYY-MM-DD")
        },
        getDate(){//获取当前时间
            var date = this.formatter(new Date(), 'yyyy-MM-dd hh:mm:ss')
            this.date=date.toLocaleString()
        },
        getWinWaring(){//渲染数据
            var url="/getWinWaring"
            this.$http.get(url).then(res=>{//渲染Windows服务器告警表格数据
                this.tableDataLog=res.data
                console.log(res.data)
            })
            var url1='/getWinServiceInfo'
            this.$http.get(url1).then(res=>{//渲染Windows服务器日志告警数据
                this.tableDataService=res.data
            })

            var url2='/getWinServiceJobInc'
            this.$http.get(url2).then(res=>{//渲染Windows服务器任务增长分析
                this.tableDataTask=res.data
                console.log(res.data)
            })

            var url3='/getWinLoad'
            this.$http.get(url3).then(res=>{//渲染Windows服务器负载统计饼状图
                console.log(res.data[0].ALLCOUNT-res.data[0].HIGHCOUNT,"22222222")
                while( this.option.series[0].data.length!=0){
                    this.option.series[0].data.pop()
                }
                this.option.series[0].data.push({value:res.data[0].HIGHCOUNT,name:'负载高'})
                this.option.series[0].data.push({value:res.data[0].ALLCOUNT-res.data[0].HIGHCOUNT,name:'负载低'})
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
    width: 100%;
    height: 67vh;
    background: #ffffff;
    background: url("../../assets/bg_data.png");
    background-size: 100% 100%;
    padding: 8%;
    margin-left: 5%;
    margin-top: 3%;
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