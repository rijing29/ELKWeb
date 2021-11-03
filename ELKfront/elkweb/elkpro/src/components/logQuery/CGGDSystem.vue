<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col align="left">
          <div class="title">
            成果归档系统日志
          </div>
        </el-col>
      </el-row>
      <!--————表格区域 begin————-->
      <el-col :span="10" style="margin-top: 8px">
        <el-row>
          <el-col class="border_top">
            <div class="tableSubTitle">成果归档日志</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col style="padding: 0;">
            <div class="table-wrapper">
              <!--————表格 begin————-->
              <el-table height="540" :data="tableData" >
                <el-table-column prop="time" sortable label="时间" align="center"></el-table-column>
                <el-table-column prop="username" sortable label="用户名" align="center"></el-table-column>
                <el-table-column prop="xm" sortable label="项目名" align="center"></el-table-column>
                <el-table-column prop="active" sortable label="文档名" align="center"></el-table-column>
              </el-table>
              <!--————表格 end————-->
            </div>
          </el-col>
        </el-row>
      </el-col>
      <!--————表格区域 end————-->
    </el-main>
  </el-container>
</template>

<script>
export default {
    name: "CGGDSystem",
    data(){
      return{
        tableData:''
      }
    },
    created() {
      this.getKKLog();
    },
    methods:{
        getKKLog(){
          var url = "/getKKLog"
          this.$http.get(url).then(res => {
            console.log(res.data)
            this.tableData=res.data
          })
        }
    }
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
.el-col-10 {
  width: 100%;
}
</style>