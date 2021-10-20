<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        网络交换机告警信息
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--IP下拉框-->
                    请选择IP：
                    <el-select v-model="ip_value" filterable placeholder="请选择">
                        <el-option v-for="item in ip_options" :key="item.ip_value"
                                   :label="item.label" :value="item.label"></el-option>
                    </el-select>
                    <span class="span_area">
                        <el-checkbox v-model="filtration" ><div style="color: #ffffff">过滤掉低等级告警</div></el-checkbox>
                    </span>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="24" v-if="haveData">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px"></div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!-- 表格 begin-->
                                <el-table :data="tableData" style="width: 95%;margin: auto;header-align: center;">
                                    <el-table-column prop="DEVICEIP" sortable label="设备IP" align="center"></el-table-column>
                                    <el-table-column prop="ALARMLEVEL" sortable label="告警级别" align="center"></el-table-column>
                                    <el-table-column prop="ALARMCATEGORYDESC" sortable label="告警类别信息" align="center"></el-table-column>
                                    <el-table-column prop="RECSTATUS" sortable label="告警状态" align="center"></el-table-column>
                                    <el-table-column prop="ALARMDESC" sortable label="告警具体信息" align="center" width="600"></el-table-column>
                                    <el-table-column prop="rectimedesc" sortable label="设备恢复时间" align="center"></el-table-column>
                                    <el-table-column prop="FAULTTIMEDESC" sortable label="设备告警时间" value-format="yyyy/MM/dd HH:mm:ss" align="center" width="180">
                                    </el-table-column>
                                </el-table>
                                <!-- 表格 end-->
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
</template>

<script>
export default {
    name: "IMCAlarm",
    data(){
        return{
            defaultData:false,
            haveData:false,//表格是否有数据
            filtration:false,//是否过滤低级警告
            ip_options:[],
            ip_value:'',
            tableData:[],
            //分页信息
            pages: [
                {
                    pageSize: 20,
                    total: 1000,
                    currentPage: 1,
                },
            ],
        }
    },
    created(){
        this.getIMCIP()
        this.getDefaultIMC()
    },
    watch:{
        ip_value:function (newV, oldV){
            this.ip_value=newV
            this.getIMCInfo(this.ip_value)
            console.log(this.ip_value)
        },
        filtration: function (){
            this.getIMCInfo(this.ip_value)
        }
    },
    methods: {

        /*————分页控制部分 begin————*/
        currentPage: function (row) {
            this.pages[0].currentPage = row//取当前页码
            this.getIPMI()//根据当前页码渲染数据
        },
        /*————分页控制部分 end————*/

        getDefaultIMC(){//IMCAlarmMapper.xml - ELKIMCController.java
            var url = "/getDefaultIMC"
            this.$http.get(url).then(res => {
                this.haveData=true
                this.tableData=res.data
                console.log(res.data)
            })
        },
        getIMCIP(){
            var url = "/getIMCIP"
            this.$http.get(url).then(res => {
                console.log(res.data[0].DEVICEIP)
                while (this.ip_options.length!==0){
                    this.ip_options.pop()
                }
                var i;
                for(i=0;i<res.data.length;i++){
                    this.ip_options.push({ip_value: i,label: res.data[i].DEVICEIP})
                }
                this.haveData=true
            })
        },

        getIMCInfo(){
            this.defaultData=true
            var url = "/getIMCInfo"
            var params = {
                'ip': this.ip_value,
                'filtration':this.filtration,
                'pageNum': this.pages[0].currentPage,
                'pageSize': this.pages[0].pageSize,
            }
            this.$http.get(url, {params}).then(res => {
                this.tableData = res.data.list;
                this.pages[0].total = res.data.total//向分页传递总数据
                console.log(res.data)
            })
        },
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

    z-index: 1;
}
.span_area {
    /*按钮边距区域*/
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
.border_top{
    background:url("../../assets/border_top2.png");
    background-size: 100% 100%;
    text-align: left;
}
.border_bottom{
    background:url("../../assets/border_bottom2.png");
    background-size: 100% 100%;
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