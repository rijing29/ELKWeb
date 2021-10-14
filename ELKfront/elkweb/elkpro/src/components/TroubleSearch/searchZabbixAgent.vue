<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="24" align="left">
          <div class="title">
            故障Zabbix Agent查询
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-row class="area">
            <el-row>
              <el-col :span="24" class="border_top">
                <div class="tableSubTitle">故障Zabbix Agent查询</div>
              </el-col>
            </el-row>
            <el-row>

              <el-col :span="24">
                <div class="table-wrapper">
                  <el-table :data="tableData"
                            :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                    <el-table-column prop="ERRORS_FROM" sortable label="时间" align="center"></el-table-column>
                    <el-table-column prop="ERROR" sortable label="故障信息" align="center"></el-table-column>
                    <el-table-column prop="HOST" sortable label="主机名" align="center"></el-table-column>
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
  name: 'searchZabbixAgent',
  data() {
    return {
      tableData: [],
    }
  },
  created() {
    this.getZAErrors();
  },
  methods: {
    getZAErrors(){//ZAHostsErrorMapper.xml
      var url="/searchZAError"
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