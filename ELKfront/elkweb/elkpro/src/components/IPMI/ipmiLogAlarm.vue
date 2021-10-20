<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        IPMI日志告警信息
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="24" class="area">
                    <el-row>
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px;line-height: 70px;padding-left: 40px;color: #17caf0">一周内信息 (点击查看详细信息)</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!-- 表格 begin-->
                                <el-table :data="tableData"
                                          height="400"
                                          style="width: 95%;margin: auto;header-align: center;"
                                          highlight-current-row
                                          @current-change="handleCurrentChange">
                                    <el-table-column prop="time" sortable label="时间"
                                                     value-format="yyyy/MM/dd HH:mm:ss"
                                                     align="center"></el-table-column>
                                    <el-table-column prop="ip" sortable label="IP" align="center"></el-table-column>
                                    <el-table-column prop="info" sortable label="告警信息" align="center"></el-table-column>
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
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="24" v-if="haveData" >
                    <el-row>
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px;line-height: 70px;padding-left: 40px;color: #17caf0">IPMI详细信息</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <el-table :data="TableData" style="width: 100%;margin-bottom: 10px;">
                                    <el-table-column prop="time" sortable label="时间"
                                                     value-format="yyyy/MM/dd HH:mm:ss"
                                                     align="center"></el-table-column>
                                    <el-table-column prop="ip" label="IP" align="center"></el-table-column>
                                    <el-table-column prop="info" sortable label="告警信息" align="center"></el-table-column>
                                </el-table>
                                <!-- 外表格 end-->
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
    name: "ipmiLogAlarm",
    data() {
        return {
            haveData: false,
            Time: '',//日期选择器
            date: '',//日期变量
            expands: [],
            ip: '',
            time: '',
            tableData: [],
            TableData: [],
        }
    },
    created() {//自动渲染数据
        this.getIPMIAlarm()
    },
    methods: {
        handleCurrentChange(val) {
            this.haveData=true
            //根据筛选条件选择所有数据
            this.ip = val.ip
            //绑定后台数据传输地址
            var url = "/getIPMIInfo"
            //传递参数
            var params = {
                'ip': this.ip,
            }
            this.$http.get(url, {params}).then(res => {
                this.TableData = res.data;
            })
        },
        getIPMIAlarm() {//IPMILogWarnMapper.xml - ELKIPMIController.java
            var url = "/getIPMIAlarm"
            this.$http.get(url).then(res => {
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