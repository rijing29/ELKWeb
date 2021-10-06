<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        用户软件使用率统计
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--员工查询输入框 -->
                    员工姓名：
                    <el-select v-model="username_value" placeholder="请选择">
                        <el-option v-for="item in username_options" :key="item.username_value" :label="item.label" :value="item.label"></el-option>
                    </el-select>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getEpyCardInfo">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="11" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">近一周软件使用率</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table height="540" ref="singleTable" :data="tableDataWeek" :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="SOFTWARE" label="使用软件" align="center"></el-table-column>
                                    <el-table-column prop="USERATE" label="使用率(%)" align="center"></el-table-column>
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
                <!--————表格区域 begin————-->
                <el-col :span="11" style="margin-top: 8px;margin-left: 80px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">月度软件使用率</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="540"
                                        ref="singleTable"
                                        :data="tableDataMonth"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" label="时间" align="center"></el-table-column>
                                    <el-table-column prop="SOFTWARE" label="使用软件" align="center"></el-table-column>
                                    <el-table-column prop="USERATE" label="使用率(%)" align="center"></el-table-column>
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
import VChart, { THEME_KEY } from "vue-echarts";
export default {
    name: "userUsageRate",
    components: {
        VChart
    },
    data(){
        return{
            haveData: true,
            user_lname: '',//员工姓名
            personname:'',
            username_value:'',//员工姓名下拉框
            username_options:[{username_value:'1',label:'zhaozhong'}],
            tableDataWeek: [
                {TIME:'2021-09-12',SOFTWARE:'GeoEast(解释)',USERATE:'25'},
                {TIME:'2021-09-11',SOFTWARE:'GeoEast(解释)',USERATE:'35'},
                {TIME:'2021-09-10',SOFTWARE:'GeoEast(解释)',USERATE:'5'},
                {TIME:'2021-09-09',SOFTWARE:'GeoEast(解释)',USERATE:'15'},
                {TIME:'2021-09-08',SOFTWARE:'GeoEast(解释)',USERATE:'12'},
                {TIME:'2021-09-07',SOFTWARE:'GeoEast(解释)',USERATE:'11'},
                {TIME:'2021-09-06',SOFTWARE:'GeoEast(解释)',USERATE:'2'},
                {TIME:'2021-09-05',SOFTWARE:'GeoEast(解释)',USERATE:'5'},
            ],//近一周软件使用率
            tableDataMonth: [
                {TIME:'2021-09',SOFTWARE:'GeoEast(解释)',USERATE:'15'},
                {TIME:'2021-08',SOFTWARE:'GeoEast(解释)',USERATE:'25'},
                {TIME:'2021-07',SOFTWARE:'GeoEast(解释)',USERATE:'11'},
                {TIME:'2021-06',SOFTWARE:'GeoEast(解释)',USERATE:'7'},
            ],//月度软件使用率

        }
    },
    methods:{
        getEpyCardInfo(){//获取表格以及柱状图数据并渲染
            if(this.user_lname!==''){
                var url="/getEpyCardInfo"
                var params={
                    'user_lname':this.user_lname,
                }
                this.$http.get(url,{params}).then(res=>{
                    this.tableData=res.data
                    this.name=res.data[0].USER_LNAME
                    this.personname=this.user_lname
                })
                var url2="/getEpyCardData"
                this.$http.get(url2,{params}).then(res=>{
                    console.log(res.data[0].CQ)
                    this.option.series[0].data=[res.data[0].CQ]
                    this.option.series[1].data=[res.data[0].ZS]
                    this.option.series[2].data=[res.data[0].JB]
                    this.option.title.subtext="员工刷卡地点： "+res.data[0].PLACECOUNT+' 个'
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
    width: 90vh;
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

.el-select {
    margin-right: 1%;
    width: 10%;
}
</style>