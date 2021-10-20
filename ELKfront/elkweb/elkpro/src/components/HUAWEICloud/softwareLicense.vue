<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        软件许可信息
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <el-row class="area">
                        <el-row>
                            <el-col :span="24" class="border_top">
                                <div class="tableSubTitle">华为云软件许可日志</div>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="24">
                                <div class="table-wrapper">
                                    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                                              :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                        <el-table-column prop="userName" sortable label="用户"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="licenceServer" sortable label="软件名"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="userAction" sortable label="许可动作"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="moduleName" sortable label="软件模块名"
                                                         align="center"></el-table-column>
                                        <el-table-column prop="inputTime" sortable label="时间"
                                                         align="center"></el-table-column>
                                    </el-table>
                                </div>
                            </el-col>
                        </el-row>
                        <el-row class="border_bottom">
                            <el-col>
                                <div style="height: 50px"></div>
                            </el-col>
                        </el-row>
                    </el-row>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <!-- 分页 begin-->
                    <el-pagination
                            :key="index"
                            background layout="prev, pager, next"
                            @current-change="currentPage1"
                            :page-size="pageSize"
                            :current-page="currentPage"
                            :total="total">
                    </el-pagination>
                    <!-- 分页 end-->
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script>
export default {
    name: 'softwareLicence',
    data() {
        return {
            fullscreenLoading: false,
            year: '',
            month: '',
            softName: '',
            tableData: [],
            //分页信息 begin
            index:'',
            pageSize: 20,
            total: 1000,
            currentPage: 1,
            //分页信息 end
        }
    },
    created() {
        this.getLience();
    },

    methods: {

        /*————分页控制部分 begin————*/
        currentPage1: function (row) {
            this.currentPage = row//取当前页码
            console.log(row, "44444444444444")
        },
        /*————分页控制部分 end————*/

        getLience() {
            var url = "/getLience"
            this.$http.get(url).then(res => {
                console.log(res)
                this.total = res.data.length
                this.tableData = res.data
            })
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

.el-select {
    margin-right: 1%;
    width: 10%;
    line-height: 40px;
}

.border_top {
    background: url("../../assets/border_top2.png");
    background-size: 100% 100%;
    text-align: left;
}

.border_bottom {
    background: url("../../assets/border_bottom2.png");
    background-size: 100% 100%;
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

.area {
    width: 80%;
    margin-left: 9%;
    margin-top: 2%;
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
.el-pagination {
    /*分页*/
    margin-left: 50%;
}
.el-table {
    header-align: center;
    border-radius: 4px;
    margin: 1% auto 0;
    width: 90%;
}

/*————表格背景透明 begin————*/
.table-wrapper /deep/ .el-table,
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

.tableSubTitle {
    height: 50px;
    line-height: 70px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
}
</style>