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
            <el-input v-model="userName" placeholder="请输入员工编号" style="margin-right: 1%;width:20%;"></el-input>
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
                                        <el-table-column prop="actiontime" sortable label="时间" align="center"></el-table-column>
                                        <el-table-column prop="action" sortable label="员工行为" align="center"></el-table-column>
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

            <!--————默认显示区域 begin————-->
            <el-row v-if="defaultData">
                <el-col :span="24">
                    <el-row>
                        <el-col :span="24" class="border_top2">
                            <div class="tableSubTitle">一周内员工行为统计个数</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="600"
                                        ref="singleTable"
                                        :data="defaultTable"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="TIME" :formatter="dateForma" sortable label="日期" align="center"></el-table-column>
                                    <el-table-column prop="COU" sortable label="员工行为统计数（个）" align="center"></el-table-column>
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
    name: "epyBehaviorProfile",
    data() {
        return {
            haveData:false,
            defaultData:true,
            tableData: [],
            defaultTable:[],
            startTime: '',
            stopTime: '',
            userName: ''
        }
    },
    created() {
        this.getDefaultBehavior()
    },
    methods: {
        dateForma:function(row,column){//表格行格式化时间
            var date = row[column.property];
            if(date === undefined){return ''};
            return moment(date).format("YYYY-MM-DD")
        },

        getDefaultBehavior(){//PersonActionLogMapper.xml - ELKEPYController.java
            var url = "/getDefaultBehavior"
            this.$http.get(url).then(res => {
                console.log(res.data)
                this.defaultTable=res.data
            })
        },

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
                if(res.data.length!==0){
                    this.tableData = res.data
                    console.log(res.data)
                    this.haveData=true
                    this.defaultData=false;
                }

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
.tableSubTitle2 {
    height: 50px;
    line-height: 35px;
    padding-left: 20px;
    color: #17caf0;
    font-weight: bold;
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