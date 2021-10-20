<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        单位员工刷卡统计
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="24">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px;line-height: 70px;padding-left: 40px;color: #17caf0">最近一周内信息</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!-- 外表格 begin-->
                                <el-table v-if="haveData" :data="tableData" style="width: 95%;margin: auto;header-align: center;":header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="name" sortable label="单位名称" align="center"></el-table-column>
                                    <el-table-column prop="counts" sortable label="单位统计涉及人数（人）" align="center"></el-table-column>
                                    <el-table-column prop="placecount" sortable label="人均刷卡地点（个）" align="center"></el-table-column>
                                    <el-table-column prop="cq" sortable label="人均出勤率(%)" align="center"></el-table-column>
                                    <el-table-column prop="jb" sortable label="人均加班率(%)" align="center"></el-table-column>
                                    <el-table-column prop="zs" sortable label="人均准时率(%)" align="center"></el-table-column>
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
        </el-main>
    </el-container>
</template>

<script>
export default {
    name: "epyCardDPM",
    data(){
        return{
            haveData:true,
            Time:'',//日期选择器
            date:'',//日期变量
            expands: [],
            ip:'',
            time:'',
            tableData:[],
        }
    },
    created(){//自动渲染数据
        this.getCardDEP()
    },
    methods: {
        //单位刷卡信息统计
        getCardDEP(){
          var url="/getCardDEP"
          this.$http.get(url).then(res=>{
            console.log(res)
            this.tableData=res.data
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