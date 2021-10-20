<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        单节点利用效率
                    </div>
                </el-col>
            </el-row>
          <el-row>
            <el-col :span="24">
              请选择一个年份：
              <el-select v-model="yearvalue" placeholder="请选择">
                <el-option v-for="item in year_options" :key="item.yearvalue" :label="item.label"
                           :value="item.label"></el-option>
              </el-select>
              请选择一个月份：
              <el-select v-model="monthvalue" placeholder="请选择">
                <el-option v-for="item in month_options" :key="item.monthvalue" :label="item.label"
                           :value="item.label"></el-option>
              </el-select>
              <el-button icon="el-icon-search" type="primary" @click="searchSoftNameEfficiency"
                         v-loading.fullscreen.lock="fullscreenLoading">查询
              </el-button>
              <el-button icon="el-icon-search" type="primary" @click="export2Excel">导出excel</el-button>
            </el-col>
          </el-row>
          <el-row v-if="haveData">
            <el-col :span="24">
              <el-row class="area">
                <el-row>
                  <el-col :span="24" class="border_top">
                    <div style="height: 50px"></div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div class="table-wrapper">
                      <el-table :data="tableData" :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                        <el-table-column prop="softName" sortable label="软件名" width="360" align="center"></el-table-column>
                        <el-table-column prop="time" sortable label="时间" width="360" align="center"></el-table-column>
                        <el-table-column prop="efficiency" sortable label="效率（%）" align="center"></el-table-column>
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
  watch: {
    yearvalue: function (newV, oldV) {
      this.year = newV
    },
    monthvalue: function (newV, oldV) {
      this.month = newV
    }
  },
  data() {
    return {
      haveData:true,
      fullscreenLoading: false,
      year: '2021',
      month: '10',
      tableData: [],
      month_options: [{monthvalue: '选项1', label: '1'},
        {monthvalue: '选项2', label: '2'},
        {monthvalue: '选项3', label: '3'},
        {monthvalue: '选项4', label: '4'},
        {monthvalue: '选项5', label: '5'},
        {monthvalue: '选项6', label: '6'},
        {monthvalue: '选项7', label: '7'},
        {monthvalue: '选项8', label: '8'},
        {monthvalue: '选项9', label: '9'},
        {monthvalue: '选项10', label: '10'},
        {monthvalue: '选项11', label: '11'},
        {monthvalue: '选项12', label: '12'}],
      monthvalue: '',
      year_options: [{yearvalue: '选项1', label: '2017'},
        {yearvalue: '选项2', label: '2018'},
        {yearvalue: '选项3', label: '2019'},
        {yearvalue: '选项4', label: '2020'},
        {yearvalue: '选项5', label: '2021'},],
      yearvalue: ''
    }
  },
  created() {
    this.searchSoftNameEfficiency();
  },
  methods: {
    getSoftName() {
      var url = "/searchSoftName"
      this.$http.get(url).then(res => {
        this.tableData = res.data
        console.log(res)
      })
    },
    searchSoftNameEfficiency() {
      console.log(this.year)
      console.log(this.month)
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 0);
      var url = "/searchSoftNameEfficiency"
      var params = {
        'year': this.year,
        'month': this.month,
      }
      this.$http.get(url, {params}).then(res => {
        this.haveData=true
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
          "软件名",
          "时间",
          "月平均效率",
        ]; // 上面设置Excel的表格第一行的标题
        const filterVal = [
          "softName",
          "time",
          "efficiency",
        ]; // client_id client_name client_phone 为tableData的属性
        const list = this.tableData; //把data里的tableData存到list
        const data = this.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, "软件名效率excel");
      });
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]));
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
.el-button {
  margin-left: 1%;
}

.area {
  width: 80%;
  margin-left: 9%;
  margin-top: 2%;
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
</style>