<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        用户软件使用时间
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--员工查询输入框 -->
                    <span class="span_area">
                        <el-input v-model="users" placeholder="请输入员工姓名" style="margin-right: 1%;width: 10%;"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getUserUseRate">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <el-row class="area" v-if="haveData">
                <!--————表格区域 begin————-->
                <el-col :span="24" style="margin-top: 8px">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">近一周软件使用时间</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table  ref="singleTable" :data="tableDataWeek" :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" sortable label="时间" align="center"></el-table-column>
                                    <el-table-column prop="SOFTWARE" sortable label="使用软件" align="center"></el-table-column>
                                    <el-table-column prop="USERATE" sortable label="使用时间(小时)" align="center"></el-table-column>
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
    name: "userUsageTime",
    components: {
        VChart
    },
    data(){
        return{
            haveData: false,
            users:'zhaozh',//员工姓名输入框
            tableDataWeek: [],//近一周软件使用率
        }
    },
    created() {//自动渲染数据
      this.getUserUseRate()
    },
    methods:{
        getUserUseRate(){//SoftUserMapper.xml - ELKSoftUserController.java
            this.haveData=true
            var url="/getUserUseRate"
            var params={
                'users':this.users,
            }
            this.$http.get(url,{params}).then(res=>{
                console.log(res.data)
                while (this.tableDataWeek.length!==0){
                    this.tableDataWeek.pop()
                }
                for(var i=0;i<res.data.softname.length;i++){
                    this.tableDataWeek.push({TIME:res.data.time[i],SOFTWARE:res.data.softname[i],USERATE:res.data.useRate[i]})
                }

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
    width: 80%;
    margin-left: 9%;
    margin-top: 2%;
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
    background:url("../../assets/border_top2.png");
    background-size: 100% 100%;
    text-align: left;
}
.border_bottom{
    background:url("../../assets/border_bottom2.png");
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
.el-table {
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