<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        公共服务器性能数据
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="11" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">华为云存储使用情况</div>
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
                                    <el-table-column prop="USER" label="用户" align="center"></el-table-column>
                                    <el-table-column prop="MEMTOTAL" label="总容量(T)" align="center"></el-table-column>
                                    <el-table-column prop="MEMUSED" label="使用(T)" align="center"></el-table-column>
                                    <el-table-column prop="MEMUSERATE" label="使用率(T)" align="center"></el-table-column>
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
    name: "menUse",
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
            tableData:[
                {USER:'dxzb', MEMTOTAL:'500T',MEMUSED:'229T',MEMUSERATE:'46'},
                {USER:'cydzcl', MEMTOTAL:'230T',MEMUSED:'203T',MEMUSERATE:'88'},
                {USER:'dzcxxff', MEMTOTAL:'500T',MEMUSED:'473T',MEMUSERATE:'95'},
                {USER:'fzgz', MEMTOTAL:'600T',MEMUSED:'578T',MEMUSERATE:'97'},
                {USER:'gfblcl', MEMTOTAL:'500T',MEMUSED:'229T',MEMUSERATE:'46'},
                {USER:'gfblxff', MEMTOTAL:'230T',MEMUSED:'203T',MEMUSERATE:'88'},
                {USER:'kyglxi', MEMTOTAL:'500T',MEMUSED:'473T',MEMUSERATE:'95'},
                {USER:'dzyssj', MEMTOTAL:'500T',MEMUSED:'480',MEMUSERATE:'96'},
                {USER:'ksifs', MEMTOTAL:'600T',MEMUSED:'560',MEMUSERATE:'93'},
                {USER:'all', MEMTOTAL:'5409T',MEMUSED:'4395T',MEMUSERATE:'78'},

            ],//表格数据
            /*————折线图图数据 begin————*/
            option:{
                title: {
                    text: '存储使用情况图',
                    subtext:'用户： dxzb',
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
                        data: [1, 2, 3, 4, 5, 6,7,8,9,10,11,12,13,14,15]
                    }
                ],
                yAxis: [
                    {
                        axisLabel:{color:"#ffffff"},//X轴底部标签颜色
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
                        data: [340, 320, 350, 330, 360, 350, 330,365,336,372,365,300,280,290,229]
                    }
                ]
            },

            /*————折线图数据 end————*/
        }
    },
    created(){//自动渲染数据
        // this.getDate()
        // this.getIPSAnalysis()
    },
    methods:{
        getDate(){//获取当前时间
            var date = this.formatter(new Date(), 'yyyy-MM-dd hh:mm:ss')
            this.date=date.toLocaleString()
        },
        getIPSAnalysis(){//渲染数据
            var url="/getIPSAnalysis"
            var params={
                'time':this.date,
            }
            this.$http.get(url,{params}).then(res=>{
                /*————渲染分析表格数据 begin————*/
                while (this.tableData.length !== 0) {
                    this.tableData.pop()
                }
                var i;
                for (i = 0; i < res.data.time.length; i++) {
                    this.tableData.push(
                            {
                                time: res.data.time[i],
                                dstipaddr: res.data.dstipaddr[i],
                                dangervalue: res.data.dangervalue[i],
                            })
                }
                /*————渲染分析表格数据 end————*/
                /*————渲染饼状图数据 begin————*/
                while(this.option.series[0].data.length!==0) {
                    this.option.series[0].data.pop()
                }
                if(res.data.low!==0){
                    this.option.series[0].data.push({value: res.data.low, name: '低'})
                }
                if(res.data.middle!==0){
                    this.option.series[0].data.push({value: res.data.middle, name: '中'})
                }
                if(res.data.high!==0){
                    this.option.series[0].data.push({value: res.data.high, name: '高'})
                }

                /*————渲染饼状图数据 end————*/
            })
        },
        selectStartTime(val) {//日期选择器
            this.time = val;
            if (this.time==null){
                this.getDate()
                this.getIPSAnalysis()
            }
            else if(this.time!==null){
                this.date=this.time
                this.getIPSAnalysis()
            }//if
        },
        handleCurrentChange(val) {//表格点击事件
            if(val!==null){
                this.currentRow = val;
                var url="/getIPSInfo"
                var params={
                    'time':this.currentRow.time,
                    'dstipaddr':this.currentRow.dstipaddr,
                    'pageNum': this.pages[0].currentPage,
                    'pageSize': this.pages[0].pageSize,
                }
                console.log(this.currentRow.time, this.currentRow.dstipaddr)
                this.$http.get(url,{params}).then(res=> {
                    /*————渲染详细表格数据 begin————*/
                    this.pages[0].total = res.data.total//向分页传递总数据
                    while (this.tableData1.length !== 0) {
                        this.tableData1.pop()
                    }
                    var k;
                    for (k = 0; k < res.data.time.length; k++) {
                        this.tableData1.push(
                                {
                                    time: res.data.time[k],
                                    attackname: res.data.attackname[k],
                                    severity: res.data.dangervalue[k],
                                    srcipaddr:res.data.srcipaddr[k]
                                })
                    }
                    /*————渲染详细表格数据 end————*/
                })
            }
        },
        currentPage: function (row) {//分页控制部分
            this.pages[0].currentPage = row//取当前页码
            this.handleCurrentChange(this.currentRow)//根据当前页码渲染数据
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
    width: 80vh;
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