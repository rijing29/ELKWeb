<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        员工行为画像分析
                    </div>
                </el-col>
            </el-row>
            <!--查询部分-->
            <el-row>
                <el-col :span="24" align="left">
                    <!--员工查询输入框 -->
                    员工姓名：
                    <span class="span_area">
            <el-input v-model="userName" placeholder="请输入员工姓名" style="margin-right: 1%;width:20%;"></el-input>
          </span>
                    <!--   日期选择     -->
                    开始日期：
                    <el-date-picker
                            default-value
                            v-model="startTime"
                            type="date"
                            placeholder="请选择日期"
                            format="yyyy/MM/dd"
                            value-format="yyyy/MM/dd HH:mm:ss"
                            style="margin-right: 1%; width: 20%;">
                    </el-date-picker>
                    结束日期：
                    <el-date-picker
                            default-value
                            v-model="stopTime"
                            type="date"
                            placeholder="请选择日期"
                            format="yyyy/MM/dd"
                            value-format="yyyy/MM/dd HH:mm:ss"
                            style="margin-right: 1%; width: 20%;">
                    </el-date-picker>
                    <!-- 查询按钮 -->
                    <span class="span_area">
            <el-button icon="el-icon-search" type="primary" @click="EpyBehavProfile">查询</el-button>
          </span>
                </el-col>
            </el-row>
            <el-row v-if="haveData">
                <el-col :span="24">
                    <el-row class="area">
                        <el-row>
                            <el-col :span="24" class="border_top">
                                <div class="tableSubTitle">员工行为画像分析</div>
                            </el-col>
                        </el-row>

                        <el-row>
                            <el-col :span="24">
                                <div class="table-wrapper">
                                    <el-table :data="tableData"
                                              :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                        <el-table-column prop="username" label="姓名" align="center"></el-table-column>
                                        <el-table-column prop="actiontime" label="时间" align="center"></el-table-column>
                                        <el-table-column prop="action" label="员工行为" align="center"></el-table-column>
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
    name: "epyBehaviorProfile",
    data() {
        return {
            haveData:false,
            tableData: [],
            startTime: '2021-09-07',
            stopTime: '2021-09-24',
            userName: '荣海亮'
        }
    },
    created() {
        this.EpyBehavProfile();
    },
    methods: {
        EpyBehavProfile() {
            console.log(this.startTime)
            console.log(this.stopTime)
            console.log(this.userName)
            var url = "/searchBehaveProfile"
            var params = {
                'startTime': this.startTime,
                'stopTime': this.stopTime,
                'userName': this.userName
            }
            this.$http.get(url, {params}).then(res => {
                this.tableData = res.data
                console.log(res.data)
                this.haveData=true
            })
        },
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