<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        员工外网浏览信息
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--员工查询输入框 -->
                    <span class="span_area">
                        <el-input v-model="username" placeholder="请输入员工姓名" style="margin-right: 1%;width: 10%;"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getEpyNetInfo">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <el-row v-if="haveData">
                <!--————表格区域 begin————-->
                <el-col :span="24">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px;line-height: 70px;padding-left: 40px;color: #17caf0">员工 - {{this.username}}  (一周内外网浏览信息)</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!-- 外表格 begin-->
                                <el-table v-if="haveData" :data="tableData" style="width: 95%;margin: auto;header-align: center;" :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" :formatter="dateForma" sortable label="日期" align="center"></el-table-column>
                                    <el-table-column prop="WORKCOUNTSOFWORKTIME" sortable label="工作时间 - 工作网站（次）" align="center"></el-table-column>
                                    <el-table-column prop="OTHERCOUNTSOFWORKTIME" sortable label="工作时间 - 搜索网站（次）" align="center"></el-table-column>
                                    <el-table-column prop="PLAYCOUNTSOFWORKTIME" sortable label="工作时间 - 娱乐网站（次）" align="center"></el-table-column>
                                    <el-table-column prop="WORKCOUNTSOFSPARETIME" sortable label="业余时间 - 工作网站（次）" align="center"></el-table-column>
                                    <el-table-column prop="OTHERCOUNTSOFSPARETIME" sortable label="业余时间 - 搜索网站（次）" align="center"></el-table-column>
                                </el-table>
                                <!-- 外表格 end-->
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
            <!--————默认显示区域 begin————-->
            <el-row v-if="defaultData">
                <el-col :span="24">
                    <el-row>
                        <el-col :span="24" class="border_top2">
                            <div class="tableSubTitle">全院最近一天浏览各网站平均次数</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="600"
                                        ref="singleTable"
                                        :data="tableData2"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" :formatter="dateForma" sortable label="日期" align="center"></el-table-column>
                                    <el-table-column prop="WORKCOUNTSOFWORKTIME" sortable label="工作时间浏览工作网站平均次数（次）" align="center"></el-table-column>
                                    <el-table-column prop="ASOTHERCOUNTSOFWORKTIME" sortable label="工作时间浏览门户网站平均次数（次）" align="center"></el-table-column>
                                    <el-table-column prop="PLAYCOUNTSOFWORKTIME" sortable label="工作时间浏览娱乐网站平均次数（次）" align="center"></el-table-column>
                                </el-table>
                                <!--————表格 end————-->
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col class="border_bottom2">
                            <div class="tableSubTitle2"></div>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
            <!--————默认显示区域 end————-->
        </el-main>
    </el-container>
</template>

<script>
import moment from "moment";

export default {
    name: "epyNet",
    data(){
        return{
            haveData:false,
            defaultData:true,
            tableData2:[],
            username:'',
            tableData:[],
        }
    },
    created(){
      this.getDefaultNetInfo()
    },
    methods: {
        dateForma:function(row,column){//表格行格式化时间
            var date = row[column.property];
            if(date === undefined){return ''};
            return moment(date).format("YYYY-MM-DD")
        },
        getDefaultNetInfo(){
            var url = "/getDefaultNetInfo"
            this.$http.get(url).then(res => {
                console.log(res.data)
                this.tableData2=res.data
            })
        },

        getEpyNetInfo(){//DoorInfoMappr.xml - ELKEPYController.java
            this.haveData=true
            this.defaultData=false
            var url = '/getEpyNetInfo'
            var params={'username':this.username}
            this.$http.get(url,{params}).then(res =>{
                console.log(res.data)
                if(res.data.length!==0){
                    this.tableData=res.data
                    this.haveData=true
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
    height: 90vh;
    z-index: 1;
}
.area{
    width: 600px;
    height: 500px;
    background: #ffffff;
    background: url("../../assets/bg_data.png");
    background-size: 100% 100%;
    padding: 40px;
    margin-left: 60px;

}
.span_area {
    /*区域*/
    margin-left: 1%;
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
.border_top{
    background:url("../../assets/border_top2.png");
    background-size: 100% 100%;
    text-align: left;
}
.border_bottom{
    background:url("../../assets/border_bottom2.png");
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
.tableSubTitle {
    height: 50px;
    line-height: 70px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
}
.tableSubTitle2 {
    height: 50px;
    line-height: 35px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
}
.el-table{
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
.table-wrapper /deep/ .el-table--striped .el-table__body tr.el-table__row--striped.current-row td, .table-wrapper /deep/ .el-table__body tr.current-row>td {
    color: #ffffff;
    background-color: #17b3f0 !important;
    background-size: 100% 100%;
    opacity: 0.7;
}/*高亮选中行*/
</style>