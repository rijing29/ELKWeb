<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        公共服务器性能数据
                    </div>
                </el-col>
            </el-row>
            <el-row style="margin-top: 3%">
                <!--————表格区域 begin————-->
                <el-col :span="12" style="margin-top: 8px">
                    <el-row>
                        <el-col :span="24" class="border_top">
                            <div class="tableSubTitle">各服务器数据</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0;">
                            <div class="table-wrapper">
                                <!--————表格 begin————-->
                                <el-table
                                        height="540"
                                        ref="singleTable"
                                        :data="tableData"
                                        :header-cell-style="{color: '#17caf0',fontSize:'16px'}">
                                    <el-table-column prop="ip" sortable label="IP" align="center"></el-table-column>
                                    <el-table-column prop="cpurateforworktime" sortable label="CPU负载（%）"
                                                     align="center"></el-table-column>
                                    <el-table-column prop="memrateforworktime" sortable label="内存负载（%）"
                                                     align="center"></el-table-column>
                                    <el-table-column prop="netfloatforworktime" sortable label="网络负载（%）"
                                                     align="center"></el-table-column>
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
                            <!--————柱状图 begin————-->
                            <v-chart class="echarts" :option="option" @click="handleTooltipClick"/>
                            <!--————柱状图 end————-->
                        </el-col>
                    </el-row>
                </el-col>
                <!--————饼状图区域 end————-->
            </el-row>
            <el-row v-if="echartsClick">
                <el-col>
                    <el-row>
                        <el-col :span="24" class="dividerLine"></el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" align="left">
                            <div class="tag-group" style="padding: 1%">
                                <span class="tag-group__title" style="margin-right: 2%">{{ tagTitle }}</span>
                                <el-tag :key="tag"
                                        effect="plain"
                                        v-for="tag in ipTags"
                                        :disable-transitions="false">
                                    {{ tag }}
                                </el-tag>
                            </div>
                        </el-col>
                    </el-row>
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
    name: "serverPublic",
    components: {
        VChart
    },
    data() {
        return {
            haveData: true,
            val: '',
            date: '',//日期变量
            pages: [//分页信息
                {
                    pageSize: 20,
                    total: 1000,
                    currentPage: 1,
                },
            ],
            //饼图的分类
            cpu: 0,
            memra: 0,
            highMemra: 0,
            lowMemra: 0,
            time: '',//根据此时间查询分析表
            currentRow: null,//存储当前点击行信息
            tableData: [],//表格数据
            echartsClick: false,//判定饼状图是否点击
            tagTitle: '',//标签名称
            ipTags: [],//IP标签
            tagsData: {
                cpuData: [],
                memraData: [],
                highMemraData: [],
                lowMemraData: [],
                testData: [1, 2, 3, 4, 5, 6],
            },
            /*————饼状图数据 begin————*/
            option: {
                title: {
                    text: '负载数据统计（点击查看相应IP）',
                    left: 'center',
                    textStyle: {
                        color: "#17caf0"//标题文字颜色
                    },
                    subtextStyle: {
                        color: "#17caf0"//副标题文字颜色
                    },
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (params) {
                        // console.log(params,"iiiii")
                    }
                },
                legend: {
                    orient: "horizontal",
                    top: 'bottom',
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
                            color: '#17caf0',
                            textBorderWidth: 0,
                            alignTo: 'edge',
                            formatter: '{name|{b}}\n{time|{c} 个}',
                            minMargin: 5,
                            edgeDistance: "10%",
                            lineHeight: 15,
                            rich: {
                                time: {
                                    fontSize: 10,
                                    color: '#ffffff'
                                }
                            },
                            labelLine: {
                                length: 80,
                                length2: "50",
                                // maxSurfaceAngle: 80
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
        // this.getDate()
        // this.getIPSAnalysis()
        this.getPublicServer()

    },

    methods: {
        getPublicServer() {//serverPublicMapper.xml - ELKServerController.java


            var url = "/getPublicServer"
            //获取到表格的数据
            var length = 0;
            this.$http.get(url).then(res => {
                res.data.forEach(item => {
                    if (item.netfloatforworktime > 100000)
                        item.netfloatforworktime = 100
                    else
                        item.netfloatforworktime = item.netfloatforworktime / 10000
                    length++;
                })
                /**
                 * 对饼图进行划分
                 * 0 cpu负载高
                 * 1 内存负载高
                 * 2 高负载均衡
                 * 3 低负载均衡
                 */
                this.cpu = 0;
                this.memra = 0;
                this.highMemra = 0;
                this.lowMemra = 0;
                res.data.forEach(item => {
                    if (item.cpurateforworktime >= 20) {
                        this.cpu++;
                        this.tagsData.cpuData.push(item.ip)
                    } else if (item.cpurateforworktime < 20 && item.memrateforworktime >= 15) {
                        this.memra++;
                        this.tagsData.memraData.push(item.ip)
                    } else if (item.cpurateforworktime < 20 && item.memrateforworktime < 15 && item.totalpjvalue >= 8) {
                        this.highMemra++;
                        this.tagsData.highMemraData.push(item.ip)
                    } else {
                        this.lowMemra++
                        this.tagsData.lowMemraData.push(item.ip)
                    }
                })
                this.tableData = res.data
                while (this.option.series[0].data.length !== 0) {
                    this.option.series[0].data.pop()
                }

                this.option.series[0].data.push({value: this.cpu, name: 'CPU负载高'})
                this.option.series[0].data.push({value: this.memra, name: '内存负载高'})
                this.option.series[0].data.push({value: this.lowMemra, name: '低负载均衡'})
                this.option.series[0].data.push({value: this.highMemra, name: '高负载均衡'})

            })
        },
        handleTooltipClick(val) {// 饼状图点击事件
            if (val.data.name === "CPU负载高") {
                this.ipTags = this.tagsData.cpuData
                this.tagTitle = '高CPU负载 IP：'
                this.echartsClick = true
            }
            if (val.data.name === "内存负载高") {
                this.ipTags = this.tagsData.memraData
                this.tagTitle = '高内存负载 IP：'
                this.echartsClick = true
            }
            if (val.data.name === "高负载均衡") {
                this.ipTags = this.tagsData.highMemraData
                this.tagTitle = '高负载均衡 IP：'
                this.echartsClick = true
            }
            if (val.data.name === "低负载均衡") {
                this.ipTags = this.tagsData.lowMemraData
                this.tagTitle = '低负载均衡 IP：'
                this.echartsClick = true
            }
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
    /*height: 90vh;*/
    z-index: 1;
}

.area {
    width: 95%;
    height: 70vh;
    background: #ffffff;
    background: url("../../assets/bg_data.png");
    background-size: 100% 100%;
    padding: 8%;
    margin-left: 5%;

}

.span_area {
    /*区域*/
    margin-left: 40px;
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

.dividerLine {
    height: 20px;
    background-color: #d4f5eb;
    opacity: 0.3;
    margin-bottom: 10px;
    border-radius: 3px;
}

.el-tag + .el-tag {
    margin-left: 1%;
    margin-top: 1%;
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
    opacity: 0.7;
}

/*高亮选中行*/
</style>