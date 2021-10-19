<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="24" align="left">
          <div class="title">
            防火墙IPS告警分析
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24" align="left">
          <!--起始日期下拉框-->
          日期：
          <el-date-picker
              default-value
              v-model="time"
              type="date"
              placeholder="请选择日期"
              format="yyyy/MM/dd"
              value-format="yyyy/MM/dd HH:mm:ss"
              style="margin-right: 1%; width: 11%;"
              @change="selectStartTime">
          </el-date-picker>
        </el-col>
      </el-row>
      <el-row>
        <!--————表格区域 begin————-->
        <el-col :span="12">
          <el-row>
            <el-col :span="24" class="border_top">
              <div style="height: 50px;line-height: 70px;padding-left: 40px;color: #17caf0">点击查看详细信息</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" style="padding: 0">
              <div class="table-wrapper">
                <!--————表格 begin————-->
                <el-table
                    height="500"
                    ref="singleTable"
                    :data="tableData"
                    :header-cell-style="{color: '#17caf0',fontSize:'16px'}"
                    highlight-current-row
                    @current-change="handleCurrentChange">
                  <el-table-column prop="time" sortable label="时间" align="center"></el-table-column>
                  <el-table-column prop="dstipaddr" sortable label="受攻击服务器" align="center"></el-table-column>
                  <el-table-column prop="dangervalue" sortable label="受威胁程度" align="center"></el-table-column>
                </el-table>
                <!--————表格 end————-->
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
        <!--————饼状图区域 begin————-->
        <el-col :span="12">
          <el-row>
            <el-col :span="24" class="area" align="center">
              <!--————饼状图 begin————-->
              <v-chart class="echarts" :option="option"/>
              <!--————饼状图 end————-->
            </el-col>
          </el-row>
        </el-col>
        <!--————饼状图区域 end————-->
      </el-row>
      <el-row v-if="haveData">
        <!--————表格区域 begin————-->
        <el-col :span="23">
          <el-row>
            <el-col :span="24" class="border_top2">
              <div style="height: 50px"></div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" style="padding: 0">
              <div class="table-wrapper">
                <!--————扩展表格 begin————-->
                <el-table
                    :data="tableData1"
                    :header-cell-style="{color: '#17caf0',fontSize:'16px'}"
                    @expand-change="explore"
                    :row-key='getRowKeys' :expand-row-keys="expands">
                  <!--————扩展表格 end————-->
                  <el-table-column type="expand">
                    <template slot-scope="props">
                      <el-table :data="TableData" style="width: 100%;margin-bottom: 10px;" border>
                        <el-table-column prop="TIME" sortable label="时间" value-format="yyyy/MM/dd HH:mm:ss" align="center"></el-table-column>
                        <el-table-column prop="ATTACKNAME" sortable label="攻击类型" align="center"></el-table-column>
                        <el-table-column prop="SEVERITY" sortable label="危险程度" align="center"></el-table-column>
                        <el-table-column prop="DSTIPADDR" sortable label="攻击源IP" align="center"></el-table-column>
                      </el-table>
                    </template>
                  </el-table-column>
                  <el-table-column prop="time" sortable label="时间" align="center"></el-table-column>
                  <el-table-column prop="attackname" sortable label="攻击类型" align="center"></el-table-column>
                  <el-table-column prop="severity" sortable label="危险程度" align="center"></el-table-column>
                  <el-table-column prop="srcipaddr" sortable label="受攻击服务器" align="center"></el-table-column>
                </el-table>
              </div>
            </el-col>
          </el-row>
          <el-row class="border_bottom2">
            <el-col>
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
              v-if="haveData"
              v-for="(item,index) in pages"
              background layout="prev, pager, next"
              @current-change="currentPage"
              :page-size="item.pageSize"
              :current-page="item.currentPage"
              :total="item.total"
              :key="index">
          </el-pagination>
          <!-- 分页 end-->
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent

} from 'echarts/components';
import {
  PieChart
} from 'echarts/charts';

import {
  CanvasRenderer
} from 'echarts/renderers';

echarts.use(
    [TitleComponent,
      TooltipComponent,
      LegendComponent,
      PieChart,
      CanvasRenderer,]
);
import VChart, {THEME_KEY} from "vue-echarts";

export default {
  name: "IPS",
  components: {
    VChart
  },
  data() {
    return {
      expands: [],
      haveData: false,
      date: '',//日期变量
      pages: [//分页信息
        {
          pageSize: 20,
          total: 1000,
          currentPage: 1,
        },
      ],
      time: '',//根据此时间查询分析表
      tableData: [],//分析表格数据
      currentRow: null,//存储当前点击行信息
      tableData1: [],//详情表格数据
      TableData:[],
      /*————饼状图数据 begin————*/
      option: {
        title: {
          text: '受威胁统计',
          textStyle: {
            color: "#17caf0"//标题文字颜色
          },
          subtextStyle: {
            color: "#17caf0"//副标题文字颜色
          },
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          textStyle: {
            color: "#ffffff"//顶部控制区域文字颜色
          },
        },
        series: [
          {
            data: [],
            name: '访问来源',
            type: 'pie',
            radius: ['30%', '70%'],
            itemStyle: {
              borderRadius: 10,
              borderColor: '#ffffff',
              borderWidth: 2
            },
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            label: {
              color: '#ffffff',
              textBorderWidth: 0,
              alignTo: 'edge',
              formatter: '{name|{b}}\n{time|{c} 个}',
              minMargin: 5,
              edgeDistance: 10,
              lineHeight: 15,
              rich: {
                time: {
                  fontSize: 10,
                  color: '#ffffff'
                }
              },
              labelLine: {
                length: 35,
                length2: 0,
                maxSurfaceAngle: 80
              },
              fontSize: 18,
            }

          }
        ]
      },
      /*————饼状图数据 end————*/
    }
  },
  created() {//自动渲染数据
    this.getDate()
    this.getIPSAnalysis()
  },
  methods: {
    getDate() {//获取当前时间
      var date = this.formatter(new Date(), 'yyyy-MM-dd hh:mm:ss')
      this.date = date.toLocaleString()
    },
    getIPSAnalysis() {//渲染数据
      var url = "/getIPSAnalysis"
      var params = {
        'time': this.date,
      }
      this.$http.get(url, {params}).then(res => {
        console.log(res.data)
        /*————渲染分析表格数据 begin————*/
        while (this.tableData.length !== 0) {
          this.tableData.pop()
        }
        var i;
        for (i = 0; i < res.data.time.length; i++) {
          this.tableData.push(
              {
                time: res.data.time[i],
                dstipaddr: res.data.dstipaddr[i],
                dangervalue: res.data.dangervalue[i],
              })
        }
        /*————渲染分析表格数据 end————*/
        /*————渲染饼状图数据 begin————*/
        while (this.option.series[0].data.length !== 0) {
          this.option.series[0].data.pop()
        }
        this.option.series[0].data.push({value: res.data.low, name: '低'})
        this.option.series[0].data.push({value: res.data.middle, name: '中'})
        this.option.series[0].data.push({value: res.data.high, name: '高'})
        /*————渲染饼状图数据 end————*/
      })
    },
    selectStartTime(val) {//日期选择器
      this.time = val;
      if (this.time == null) {
        this.getDate()
        this.getIPSAnalysis()
      } else if (true) {
        this.date = this.time
        this.getIPSAnalysis()
      }//if
    },
    handleCurrentChange(val) {//表格点击事件
      if (val !== null) {
        this.currentRow = val;
        var url = "/getIPSInfo"
        var params = {
          'time': this.currentRow.time,
          'dstipaddr': this.currentRow.dstipaddr,
          'pageNum': this.pages[0].currentPage,
          'pageSize': this.pages[0].pageSize,
        }
        console.log(this.currentRow.time, this.currentRow.dstipaddr)
        this.$http.get(url, {params}).then(res => {
          this.haveData = true
          /*————渲染详细表格数据 begin————*/
          this.pages[0].total = res.data.total//向分页传递总数据
          while (this.tableData1.length !== 0) {
            this.tableData1.pop()
          }
          var k;
          for (k = 0; k < res.data.time.length; k++) {
            this.tableData1.push(
                {
                  time: res.data.time[k],
                  attackname: res.data.attackname[k],
                  severity: res.data.dangervalue[k],
                  srcipaddr: res.data.srcipaddr[k]
                })
          }
          /*————渲染详细表格数据 end————*/
        })
      }
    },
    currentPage: function (row) {//分页控制部分
      this.pages[0].currentPage = row//取当前页码
      this.handleCurrentChange(this.currentRow)//根据当前页码渲染数据
    },
    formatter(thistime, fmt) {//js格式化时间
      let $this = new Date(thistime)
      let o = {
        'M+': $this.getMonth() + 1,
        'd+': $this.getDate(),
        'h+': $this.getHours(),
        'm+': $this.getMinutes(),
        's+': $this.getSeconds(),
        'q+': Math.floor(($this.getMonth() + 3) / 3),
        'S': $this.getMilliseconds()
      }
      if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, ($this.getFullYear() + '').substr(4 - RegExp.$1.length))
      }
      for (var k in o) {
        if (new RegExp('(' + k + ')').test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
        }
      }
      return fmt
    },
    //希望是对的
    /*————控制表格只能展开一行 begin————*/
    getRowKeys: function (row) {
      if (row.SRCIPADDR !== null) {
        return row.time + row.attackname + row.severity + row.srcipaddr
        //  当源IP不为空时，将row.SRCIPADDR, row.SRCPORT, row.DSTIPADDR, row.DSTPORT的拼接作为行的唯一id,解决行id唯一问题
      }
    },
    /*————控制表格只能展开一行 end————*/
    explore(row, expandedRows) {
      /*————控制表格只能展开一行 begin————*/
      var that = this
      if (expandedRows.length) {
        that.expands = []
        if (row) {
          if (row.SRCIPADDR !== null) {
            that.expands.push(row.time + row.attackname + row.severity + row.srcipaddr)
          }
        }
      } else {
        that.expands = []
      }
      /*————控制表格只能展开一行 end————*/

      //根据筛选条件选择所有数据
      this.DSTIPADDR = row.DSTIPADDR
      this.DSTPORT = row.DSTPORT
      this.SRCIPADDR = row.SRCIPADDR
      this.SRCPORT = row.SRCPORT

      console.log("要传入过去的参数：")
      console.log(this.currentRow.time)
      console.log(row.srcipaddr)

      //绑定后台数据传输地址
      var url = "/getAttackedIP"
      var params = {
        'startTime': this.currentRow.time,
        'srcipaddr':row.srcipaddr ,
      }
      console.log(this.currentRow.time, this.currentRow.dstipaddr)
      this.$http.get(url, {params}).then(res => {
        this.TableData = res.data;
        console.log(res.data, "hhh")
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
}

.area {
  width: 90%;
  height: 64vh;
  background: #ffffff;
  background: url("../../assets/bg_data.png");
  background-size: 100% 100%;
  padding: 8%;
  margin-left: 5%;
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
  background: url("../../assets/border_top.png");
  background-size: 100% 100%;
  text-align: left;
}

.border_bottom {
  background: url("../../assets/border_bottom.png");
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
}

/*高亮选中行*/
</style>