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
                                    <el-table :data="tableData"
                                              :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                        <el-table-column prop="userName" label="用户" align="center"></el-table-column>
                                        <el-table-column prop="licenceServer" label="软件名" align="center"></el-table-column>
                                        <el-table-column prop="userAction" label="许可动作" align="center"></el-table-column>
                                        <el-table-column prop="moduleName" label="软件模块名" align="center"></el-table-column>
                                        <el-table-column prop="inputTime" label="时间" align="center"></el-table-column>
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
        </el-main>
    </el-container>
</template>

<script>
export default {
    name: 'softwareLicence',
    watch: {
        softName_value: function (newV, oldV) {
            this.softName = newV
        },
        yearvalue: function (newV, oldV) {
            this.year = newV
        },
        monthvalue: function (newV, oldV) {
            this.month = newV
        }
    },
    data() {
        return {
            fullscreenLoading: false,
            year: '',
            month: '',
            softName: '',
            tableData: [],
        }
    },
    created() {
        this.getLience();
    },
    methods: {
        getNodeTypeEffi() {
            this.fullscreenLoading = true;
            setTimeout(() => {
                this.fullscreenLoading = false;
            }, 500);
            var url = "/searchNodeTypeEfficiency"
            var params = {
                'softName': this.softName,
                'year': this.year,
                'month': this.month,
            }
            this.$http.get(url, {params}).then(res => {
                console.log(res)
                this.tableData = res.data
                console.log(this.tableData)
            })
        },

        //导出excel的信息集合
        export2Excel() {
            require.ensure([], () => {
                const {export_json_to_excel} = require("../../tools/Export2Excel.js");
                // 设置自己的excel表头
                const tHeader = [
                    "节点名",
                    "节点编号",
                    "时间",
                    "效率",
                ]; // 上面设置Excel的表格第一行的标题
                const filterVal = [
                    "nodeType",
                    "nodeId",
                    "time",
                    "efficiency",
                ]; // client_id client_name client_phone 为tableData的属性
                const list = this.tableData; //把data里的tableData存到list
                const data = this.formatJson(filterVal, list);
                export_json_to_excel(tHeader, data, "节点效率excel");
            });
        },
        formatJson(filterVal, jsonData) {
            return jsonData.map(v => filterVal.map(j => v[j]));
        },
        getLience(){
          var url="/getLience"
          this.$http.get(url).then(res=>{
            console.log(res)
            this.tableData=res.data
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

.tableSubTitle{
    height: 50px;
    line-height: 70px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
}
</style>