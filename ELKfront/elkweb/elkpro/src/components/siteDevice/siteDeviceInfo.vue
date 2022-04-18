<template>
  <el-container>
    <el-main>
      <el-row>
        <el-col :span="24" align="left">
          <div class="title">
            场地设备监控信息
          </div>
        </el-col>
      </el-row>
      <el-row>
        <!--————近期（一个月）Windows服务器提供服务统计 begin————-->
        <el-col :span="11">
          <el-row>
            <el-col :span="24" class="area1 area_public" align="center">
              <!--————饼状图 begin————-->
              <v-chart class="echarts" :option="option1"/>
              <!--————饼状图 end————-->
            </el-col>
          </el-row>
        </el-col>
        <!--————近期（一个月）Windows服务器提供服务统计 end————-->
        <!--————Windows服务器日志告警信息 begin————-->
        <el-col :span="11">
          <el-row>
            <el-col :span="24" class="area2 area_public" align="center">
              <!--————柱状图 begin————-->
              <v-chart class="echarts" :option="option2"/>
              <!--————柱状图 end————-->
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" class="area2 area_public" align="center">
              <!--————柱状图 begin————-->
              <v-chart class="echarts" :option="option3"/>
              <!--————柱状图 end————-->
            </el-col>
          </el-row>
        </el-col>
        <!--————Windows服务器日志告警信息 end————-->
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  MarkLineComponent,
  MarkPointComponent
} from 'echarts/components';
import {
  PieChart,
  LineChart
} from 'echarts/charts';
import {
  CanvasRenderer
} from 'echarts/renderers';

echarts.use(
    [TitleComponent, ToolboxComponent, TooltipComponent, GridComponent, LegendComponent, LineChart, CanvasRenderer,PieChart, MarkLineComponent, MarkPointComponent]
);
import VChart, {THEME_KEY} from "vue-echarts";

export default {
  name: "siteDeviceInfo",
  components: {
    VChart,
    THEME_KEY
  },
  data() {

    return {
      haveData: true,
      date: '',
      time: '',
      /*————图表数据 begin————*/
      option1: {
        title: {
          text: 'UPS监控信息',
          subtext: '',
          // subtext: '时间：',
          textStyle: {
            color: "#17caf0"//标题文字颜色
          },
          subtextStyle: {
            color: "#ffffff"//副标题文字颜色
          },
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          textStyle: {
            color: "#ffffff"//顶部控制区域文字颜色
          },
          left: "35%",//距离左边距离
          data: ['ups1', 'ups2', 'ups3']
        },
        grid: {left: '2%', right: '4%', bottom: '3%', containLabel: true},
        toolbox: {
          show: true,
          feature: {
            dataView: {show: false, readOnly: false},
            magicType: {show: false, type: ['line', 'bar']},
            restore: {show: false},
            saveAsImage: {
              //下载图标
              show: true,
              emphasis: {
                iconStyle: {
                  textFill: "#2791f3"//鼠标放上工具栏提示文字颜色
                }
              }
            },

          },
          iconStyle: {
            //工具栏图标样式
            borderColor: "#ffffff",//icon边框颜色
            borderWidth: 1,//icon边框大小
          },
          left: "90%",//工具栏距离左边距离
        },
        calculable: true,
        xAxis: [
          {
            axisLabel: {
              color: "#ffffff",//坐标轴标签文字颜色
              rotate: 0,//坐标轴标签文字选装角度
              margin: 14,//坐标轴标签文字与轴线距离
              fontWeight: "lighter",//坐标轴标签文字粗细
            },
            type: 'category',
            data: ['输入电压','输出电压','负载'],
            axisPointer: {
              type: "shadow"//坐标轴指示器(line\shadow\none)
            }
          },
        ],
        yAxis: [
          {
            position: "top",//X轴标签位置(顶部或底部)
            axisLabel: {color: "#ffffff"},//X轴底部标签颜色
            type: 'value',
            boundaryGap: [0, 0.01],
            // max:100,//最大刻度值

          }


        ],
        series: [
          {
            name: 'ups1',
            type: 'bar',
            data: [0,0,0],
            itemStyle: {
              normal: {
                //柱状图颜色(渐变)
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                  {//0,0,1,0分别表示左、上、右、下,控制渐变方向
                    offset: 0,
                    color: "#26b2ea" // 0% 处的颜色
                  }, {
                    offset: 0.6,
                    color: "#35b8ec" // 60% 处的颜色
                  }, {
                    offset: 1,
                    color: "#46c8fc" // 100% 处的颜色
                  }], false)
              },
              color: null,
              opacity: 1
            },
            // barGap: "60%"//不同系列的柱间距离
          }, {
            name: 'ups2',
            type: 'bar',
            data: [0,0,0],
            itemStyle: {
              normal: {
                //柱状图颜色(渐变)

                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                  offset: 0,
                  color: "rgb(84,246,200)" // 0% 处的颜色
                }, {
                  offset: 0.6,
                  color: "rgb(82,250,191)" // 60% 处的颜色
                }, {
                  offset: 1,
                  color: "rgb(83,232,202)" // 100% 处的颜色
                }], false)

              },
              color: null,
              opacity: 1
            },
            // barGap: "60%"//不同系列的柱间距离
          }, {
            name: 'ups3',
            type: 'bar',
            data: [0,0,0],
            itemStyle: {
              normal: {
                //柱状图颜色(渐变)
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                  offset: 0,
                  color: "rgb(147,125,243)" // 0% 处的颜色
                }, {
                  offset: 0.6,
                  color: "rgb(177,105,243)" // 60% 处的颜色
                }, {
                  offset: 1,
                  color: "rgb(211,163,246)" // 100% 处的颜色
                }], false)
              },
              color: null,
              opacity: 1
            },
            // barGap: "60%"//不同系列的柱间距离
          },
        ]
      },
      option2:{
        title: {
          text: '场地温度',
          // subtext:'用户： dxzb',
          textStyle:{
            color:"#17caf0"//标题文字颜色
          },
          subtextStyle:{
            color:"#ffffff"//副标题文字颜色
          },
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          textStyle:{
            color:"#ffffff"//顶部控制区域文字颜色
          },
          left: "40%",//距离左边距离
          data: ['2楼','3楼']
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: false},
            dataView: {show: false, readOnly: false},
            restore: {show: false},
            saveAsImage: {
              //下载图标
              show: true,
              emphasis: {
                iconStyle: {
                  textFill: "#2791f3"//鼠标放上工具栏提示文字颜色
                }
              }
            },
          },
          iconStyle:{
            //工具栏图标样式
            borderColor:"#ffffff",//icon边框颜色
            borderWidth: 1,//icon边框大小
          },
          left: "90%",//工具栏距离左边距离
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            axisLabel:{color:"#ffffff"},//X轴底部标签颜色
            type: 'category',
            // boundaryGap: false,
            data: []
          }
        ],
        yAxis: [
          {
            axisLabel:{
              color:"#ffffff",//X轴底部标签颜色
              formatter: '{value} °C'
            },
            type: 'value',
            max:30,
            min:20
          }
        ],
        series: [
          {
            itemStyle: {
              normal: {
                //折线图图颜色(渐变)
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                  offset: 0,
                  color: "rgb(81,232,246)" // 0% 处的颜色
                }, {
                  offset: 0.6,
                  color: "rgb(53,236,236)" // 60% 处的颜色
                }, {
                  offset: 1,
                  color: "rgb(59,202,245)" // 100% 处的颜色
                }], false)
              },
            },
            name: '2楼',
            type: 'line',
            data: [],
            // markPoint: {
            //     data: [
            //         {type: 'max', name: '最大值'},
            //         {type: 'min', name: '最小值'}
            //     ]
            // },
            // markLine: {
            //     data: [
            //         {type: 'average', name: '平均值'}
            //     ]
            // }
          },
          {
            itemStyle: {
              normal: {
                //柱状图颜色(渐变)
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                  offset: 0,
                  color: "rgb(211,163,246)" // 0% 处的颜色
                }, {
                  offset: 0.6,
                  color: "rgb(177,105,243)" // 60% 处的颜色
                }, {
                  offset: 1,
                  color: "rgb(147,125,243)" // 100% 处的颜色
                }], false)
              },
            },
            name: '3楼',
            type: 'line',
            data: [],
          }
        ]
      },
      option3:{
        title: {
          text: '场地湿度',
          // subtext:'用户： dxzb',
          textStyle:{
            color:"#17caf0"//标题文字颜色
          },
          subtextStyle:{
            color:"#ffffff"//副标题文字颜色
          },
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          textStyle:{
            color:"#ffffff"//顶部控制区域文字颜色
          },
          left: "40%",//距离左边距离
          data: ['2楼','3楼']
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: false},
            dataView: {show: false, readOnly: false},
            restore: {show: false},
            saveAsImage: {
              //下载图标
              show: true,
              emphasis: {
                iconStyle: {
                  textFill: "#2791f3"//鼠标放上工具栏提示文字颜色
                }
              }
            },
          },
          iconStyle:{
            //工具栏图标样式
            borderColor:"#ffffff",//icon边框颜色
            borderWidth: 1,//icon边框大小
          },
          left: "90%",//工具栏距离左边距离
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            axisLabel:{color:"#ffffff"},//X轴底部标签颜色
            type: 'category',
            // boundaryGap: false,
            data: []
          }
        ],
        yAxis: [
          {
            axisLabel:{
              color:"#ffffff",
              formatter: '{value} %'},//X轴底部标签颜色
            type: 'value',
            max:34,
            min:26
          }
        ],
        series: [
          {
            itemStyle: {
              normal: {
                //柱状图颜色(渐变)
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                  offset: 0,
                  color: "rgb(84,246,200)" // 0% 处的颜色
                }, {
                  offset: 0.6,
                  color: "rgb(82,250,191)" // 60% 处的颜色
                }, {
                  offset: 1,
                  color: "rgb(83,232,202)" // 100% 处的颜色
                }], false)
              },
            },
            name: '2楼',
            type: 'line',
            data: [],
            // markPoint: {
            //     data: [
            //         {type: 'max', name: '最大值'},
            //         {type: 'min', name: '最小值'}
            //     ]
            // },
            // markLine: {
            //     data: [
            //         {type: 'average', name: '平均值'}
            //     ]
            // }
          },
          {
            itemStyle: {
              normal: {
                //柱状图颜色(渐变)
                color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{//0,0,1,0分别表示左、上、右、下,控制渐变方向
                  offset: 0,
                  color: "rgb(241,100,79)" // 0% 处的颜色
                }, {
                  offset: 0.6,
                  color: "rgb(241,88,88)" // 60% 处的颜色
                }, {
                  offset: 1,
                  color: "rgb(246,96,108)" // 100% 处的颜色
                }], false)
              },
            },
            name: '3楼',
            type: 'line',
            data: [],
            // markPoint: {
            //     data: [
            //         {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
            //     ]
            // },
            // markLine: {
            //     data: [
            //         {type: 'average', name: '平均值'},
            //         [{
            //             symbol: 'none',
            //             x: '90%',
            //             yAxis: 'max'
            //         }, {
            //             symbol: 'circle',
            //             label: {
            //                 position: 'start',
            //                 formatter: '最大值'
            //             },
            //             type: 'max',
            //             name: '最高点'
            //         }]
            //     ]
            // }
          }
        ]
      },
    }
  },
  created() {//自动渲染数据
    this.getUPSInfo()
    this.getEnvironmentInfo()
  },
  methods: {
    getUPSInfo() {//渲染数据
      var url = "/getUPSInfo"
      this.$http.get(url).then(res => {//渲染ups柱状图数据(如果数据未更新,看data数组是否清空,清空才能更新成功)
        console.log(res.data)
        /*————清空data数组，以更新柱状图数据 begin————*/
        while (this.option1.series[0].data.length!==0){
          this.option1.series[0].data.pop()
        }
        while (this.option1.series[1].data.length!==0){
          this.option1.series[1].data.pop()
        }
        while (this.option1.series[2].data.length!==0){
          this.option1.series[2].data.pop()
        }
        console.log(this.option1.series[0].data[0],"+++++",this.option1.series[0].data[0],"------")
        /*————清空data数组，以更新柱状图数据 end————*/
        for(var i=0;i<res.data.length;i++){//
          if(res.data[i].UPSNAME==='ups1'){
            if(res.data[i].ITEMNAME==='in'){
              this.option1.series[0].data[0]=res.data[i].VALUE
            }
            if(res.data[i].ITEMNAME==='out'){
              this.option1.series[0].data[1]=res.data[i].VALUE
            }
            if(res.data[i].ITEMNAME==='load'){
              this.option1.series[0].data[2]=res.data[i].VALUE
            }
          }
          if(res.data[i].UPSNAME==='ups2'){
            if(res.data[i].ITEMNAME==='in'){
              this.option1.series[1].data[0]=res.data[i].VALUE
            }
            if(res.data[i].ITEMNAME==='out'){
              this.option1.series[1].data[1]=res.data[i].VALUE
            }
            if(res.data[i].ITEMNAME==='load'){
              this.option1.series[1].data[2]=res.data[i].VALUE
            }
          }
          if(res.data[i].UPSNAME==='ups3'){
            if(res.data[i].ITEMNAME==='in'){
              this.option1.series[2].data[0]=res.data[i].VALUE
            }
            if(res.data[i].ITEMNAME==='out'){
              this.option1.series[2].data[1]=res.data[i].VALUE
            }
            if(res.data[i].ITEMNAME==='load'){
              this.option1.series[2].data[2]=res.data[i].VALUE
            }
          }

        }
        console.log(this.option1.series,'0000000')
      })
    },
    getEnvironmentInfo(){
      var url = "/getEnvironmentInfo"
      this.$http.get(url).then(res => {//渲染Windows服务器告警表格数据
        console.log(res.data)
        while (this.option2.series[0].data.length!==0){
          this.option2.series[0].data.pop()
          this.option2.series[1].data.pop()
          this.option2.xAxis[0].data.pop()
          this.option3.series[0].data.pop()
          this.option3.series[1].data.pop()
          this.option3.xAxis[0].data.pop()

        }

        var i
        for(i=res.data.length-1;i>=0;i--){
          if(res.data[i].PLACENAME==='2楼'){
            this.option2.series[0].data.push(res.data[i].WD)
            this.option3.series[0].data.push(res.data[i].SD)
            this.option2.xAxis[0].data.push(res.data[i].TIME)
            console.log(res.data[i].TIME)

          }
          else if(res.data[i].PLACENAME==='3楼'){
            this.option2.series[1].data.push(res.data[i].WD)
            this.option3.series[1].data.push(res.data[i].SD)
            this.option3.xAxis[0].data.push(res.data[i].TIME)
          }
        }
      })
    },
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
  z-index: 1;
}

.area1 {
  width: 90%;
  height: 80vh;
  background: url("../../assets/bg-border-high.png");
  background-size: 100% 100%;
}
.area2 {
  width: 90%;
  height: 40vh;
  background: url("../../assets/bg_border-low.png");
  background-size: 100% 100%;
}
.area_public{
  padding: 40px;
  margin-left: 10%;
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

.tableSubTitle {
  height: 50px;
  line-height: 70px;
  padding-left: 20px;
  color: #17caf0;
  font-weight: bold;
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
</style>