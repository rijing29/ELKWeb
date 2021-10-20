<template>
    <div>
        <el-container>
            <el-main>
                <el-row>
                    <el-col :span="24" align="left">
                        <div class="title">
                            节点作业规划
                        </div>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <!--上面的下拉框们-->
                        节点：
                        <el-select v-model="nodeType_value" placeholder="请选择">
                            <el-option v-for="item in nodeType_options" :key="item.nodeType_value" :label="item.label"
                                       :value="item.label"></el-option>
                        </el-select>
                        软件名：
                        <el-select id="group" v-model="softName_value" placeholder="请选择">
                            <el-option v-for="item in softName_options" :key="item.softName_value" :label="item.label"
                                       :value="item.label"></el-option>
                        </el-select>
                        <!--起节点-->
                        起始节点：
                        <el-select v-model="start_value" placeholder="请选择">
                            <el-option v-for="item in start_options" :key="item.start_value" :label="item.label"
                                       :value="item.label"></el-option>
                        </el-select>
                        <!--止节点-->
                        终止节点：
                        <el-select v-model="stop_value" placeholder="请选择">
                            <el-option v-for="item in stop_options" :key="item.stop_value" :label="item.label"
                                       :value="item.label"></el-option>
                        </el-select>
                        工作量：
                        <el-select v-model="work_value" placeholder="请选择">
                            <el-option v-for="item in work_options" :key="item.work_value" :label="item.label"
                                       :value="item.label"></el-option>
                        </el-select>
                        <!--按钮-->
                        <el-button icon="el-icon-d-arrow-right" type="primary" @click="add"></el-button>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-row class="area">
                            <el-col :span="24">
                                <el-row>
                                    <el-col :span="24" class="border_top">
                                        <div style="height: 50px"></div>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="24" align="center">
                                        <div class="table-wrapper">
                                            <!--下面的表格-->
                                            <el-table :data="tableData" :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                                <el-table-column type="selection" width="55" align="center"></el-table-column>
                                                <el-table-column prop="nodeType" sortable label="节点名" align="center"></el-table-column>
                                                <el-table-column prop="nodeId" sortable label="起始和截止节点" align="center"></el-table-column>
                                                <el-table-column prop="softName" sortable label="软件名" align="center"></el-table-column>
                                                <el-table-column prop="workLoad" sortable label="作业规划数（个）" align="center"></el-table-column>
                                            </el-table>
                                        </div>
                                    </el-col>
                                </el-row>
                                <el-row class="border_bottom">
                                    <el-col>
                                        <div style="height: 50px"></div>
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <!-- 分页 begin-->
                        <el-pagination
                                v-if="defaultData"
                                v-for="(item,index) in pages"
                                :key="index"
                                background layout="prev, pager, next"
                                @current-change="currentPage"
                                :page-size="item.pageSize"
                                :current-page="item.currentPage"
                                :total="item.total">
                        </el-pagination>
                        <!-- 分页 end-->
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>

</template>

<script>
export default {
    name: 'nodeDeploy',
    watch: {
        softName_value: function (newV, oldV) {
            // this.softName_present=oldV;
            this.softName_present = newV
        },
        nodeType_value: function (newV, oldV) {
            // this.softName_present=oldV;
            this.nodeType_present = newV
        },
        start_value: function (newV, oldV) {
            // this.softName_present=oldV;
            this.start_present = newV
        },
        stop_value: function (newV, oldV) {
            // this.softName_present=oldV;
            this.stop_present = newV
        },
        work_value: function (newV, oldV) {
            // this.softName_present=oldV;
            this.work_present = newV
        },

    },
    data() {
        return {
            defaultData:true,
            softName_present: '',//softName
            nodeType_present: '',//nodeType
            start_present: '',
            stop_present: '',
            work_present: '',
            tableData: [],
            //分页信息
            pages: [
                {
                    pageSize: 20,
                    total: 1000,
                    currentPage: 1,
                },
            ],
            //软件名节点下拉框值
            softName_options: [{softName_value: '选项1', label: 'Geoeast'},
                {softName_value: '选项2', label: 'Pardiam'},
                {softName_value: '选项3', label: 'GEOEASTDL'},
                {softName_value: '选项4', label: 'PWIN'},
                {softName_value: '选项5', label: 'TOMODEL'},
                {softName_value: '选项6', label: 'PARADIGM'},
                {softName_value: '选项7', label: 'PARADIGMDL'},
                {softName_value: '选项8', label: 'ZHIKONG'},
                {softName_value: '选项9', label: 'PSTM'}],
            softName_value: '',
            //节点下拉框值
            nodeType_options: [{nodeType_value: '选项1', label: 'hwnode'},
                {nodeType_value: '选项2', label: 'hwgnode'}],
            nodeType_value: '',
            //起节点下拉框值
            start_options: [{start_value: '选项1', label: '1'},
                {start_value: '选项2', label: '2'},
                {start_value: '选项3', label: '3'}],
            start_value: '',
            //止节点下拉框值
            stop_options: [{stop_value: '选项1', label: '4'},
                {stop_value: '选项2', label: '5'},
                {stop_value: '选项3', label: '6'}],
            stop_value: '',
            //工作量
            work_options: [{work_value: '选项1', label: '1'},
                {work_value: '选项2', label: '2'},
                {work_value: '选项3', label: '3'}],
            work_value: '',
        }
    },
    created() {
        this.getTableData()
    },
    methods: {
        /*————分页控制部分 begin————*/
        currentPage: function (row) {
            this.pages[0].currentPage = row//取当前页码
            this.getTableData()
        },
        /*————分页控制部分 end————*/

        //获取tabledata数据
        getTableData() {
            var url = "/queryTable"
            var params = {
                'pageNum': this.pages[0].currentPage,
                'pageSize': this.pages[0].pageSize,
            }
            this.$http.get(url,{params}).then(res => {
                console.log("table返回来的值：")
                this.tableData = res.data.list;
                this.pages[0].total = res.data.total//向分页传递总数据
            })
        },
        //新增
        add() {
            console.log(this.nodeType_present)
            console.log(this.softName_present)
            console.log(this.start_present)
            console.log(this.stop_present)
            console.log(this.work_present)
            let that = this;//改变this指向
            var url = "add"
            var params = {
                'nodeType': this.nodeType_present,
                'startValue': this.start_present,
                'stopValue': this.stop_present,
                'softName': this.softName_present,
                'workLoad': this.work_present,
            }
            this.$http.get(url, {params}).then(res => {
                console.log(res + "新增成功")
                window.location.reload();
            })
        },
    }
}
</script>

<style scoped>
.el-main {
    color: #ffffff;
    text-align: center;
    z-index: 1;
    height: 90vh;
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
