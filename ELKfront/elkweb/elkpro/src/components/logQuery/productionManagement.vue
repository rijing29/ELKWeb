<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        生产管理软件日志
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="24" class="area">
                    <el-row>
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px;line-height: 70px;padding-left: 40px;color: #17caf0"></div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!-- 表格 begin-->
                                <el-table :data="tableData"
                                          height="650"
                                          style="width: 95%;margin: auto;header-align: center;">
                                    <el-table-column prop="OCCURTIME" sortable label="时间"
                                                     value-format="yyyy/MM/dd HH:mm:ss"
                                                     align="center"></el-table-column>
                                    <el-table-column prop="USERNAME" sortable label="用户名" align="center"></el-table-column>
                                    <el-table-column prop="USERIP" sortable label="用户ID" align="center"></el-table-column>
                                    <el-table-column prop="USEREMAIL" sortable label="用户邮箱" align="center" ></el-table-column>
                                    <el-table-column prop="MSGTYPE" sortable label="信息类型" align="center"></el-table-column>
                                    <el-table-column prop="FUNNAME" sortable label="操作" align="center"></el-table-column>
                                    <el-table-column prop="SYSTEMNAME" sortable label="使用系统名" align="center" ></el-table-column>
                                </el-table>
                                <!-- 表格 end-->
                            </div>
                        </el-col>
                    </el-row>
                    <el-row class="border_bottom">
                        <el-col>
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
export default {
    name: "productionManagement",
    data() {
        return {
            haveData: false,
            tableData: [],
        }
    },
    created() {//自动渲染数据
        this.getSysSrmLog()
    },
    methods: {

        getSysSrmLog() {//zabbixShenji.xml - ELKLogQueryController.java
            var url = "/getSysSrmLog"
            this.$http.get(url).then(res => {
                console.log(res.data)
                this.tableData = res.data
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

.area {
    width: 95%;
    margin-left: 2%;
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

.border_top {
    background: url("../../assets/border_top2.png");
    background-size: 100% 100%;
    text-align: left;
}

.border_bottom {
    background: url("../../assets/border_bottom2.png");
    background-size: 100% 100%;
}

.el-table {
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
.table-wrapper /deep/ .el-table--striped .el-table__body tr.el-table__row--striped.current-row td, .table-wrapper /deep/ .el-table__body tr.current-row > td {
    color: #ffffff;
    background-color: #17b3f0 !important;
    background-size: 100% 100%;
    /*opacity: 0.7;*/
}  /*高亮选中行*/
</style>