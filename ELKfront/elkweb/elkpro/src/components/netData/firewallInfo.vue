<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24">

                    <!--起始日期下拉框-->
                    起始日期：
                    <el-date-picker
                            v-model="startTime"
                            type="date"
                            placeholder="选择起始日期"
                            format="yyyy/MM/dd"
                            value-format="yyyy/MM/dd HH:mm:ss"
                            style="margin-right: 1%; width: 10%;"
                            @change="selectStartTime">
                    </el-date-picker>
                    <!--终止日期下拉框-->
                    截止日期：
                    <el-date-picker
                            v-model="stopTime"
                            type="date"
                            placeholder="选择截止日期"
                            format="yyyy/MM/dd"
                            value-format="yyyy/MM/dd HH:mm:ss"
                            style="margin-right: 1%; width: 10%;"
                            @change="selectStopTime">
                    </el-date-picker>

                    <!--源域下拉框-->
                    源域：
                    <el-select v-model="sourceDomain_value" placeholder="请选择">
                        <el-option v-for="item in sourceDomain_options" :key="item.sourceDomain_value"
                                   :label="item.label" :value="item.label"></el-option>
                    </el-select>

                    <!--目的域下拉框-->
                    目的域：
                    <el-select v-model="destinationDomain_value" placeholder="请选择">
                        <el-option v-for="item in destinationDomain_options" :key="item.destinationDomain_value"
                                   :label="item.label" :value="item.label"></el-option>
                    </el-select>

                    <!--Protocol下拉框-->
                    Protocol：
                    <el-select v-model="protocol_value" placeholder="请选择">
                        <el-option v-for="item in protocol_option" :key="item.protocol_value" :label="item.label"
                                   :value="item.label"></el-option>
                    </el-select>

                    <!--Event下拉框-->
                    Event：
                    <el-select v-model="event_value" placeholder="请选择">
                        <el-option v-for="(item,index) in event_options" :key="index" :label="item.label"
                                   :value="item.label"></el-option>
                    </el-select>
                </el-col>
                <el-col :span="24">
                    <!-- `checked` 为 true 或 false -->
                    <el-checkbox v-model="sourceIP"><div class="checkColor">源IP</div></el-checkbox>
                    <el-checkbox :disabled="useSourcePort" v-model="sourcePort"><div class="checkColor">源Port</div></el-checkbox>
                    <el-checkbox v-model="destinationIP"><div class="checkColor">目的IP</div></el-checkbox>
                    <el-checkbox :disabled="useDestinationPort" v-model="destinationPort"><div class="checkColor">目的Port</div></el-checkbox>
                    <span class="span_area">
                        <el-input v-model="srcIp" placeholder="请输入源IP" style="margin-right: 1%;width: 10%;"
                                  :disabled="srcIp_disabled"></el-input>
                        <el-input v-model="dstIp" placeholder="请输入目的IP" style="margin-right: 1%;width: 10%;"
                                  :disabled="dstIp_disabled"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary" @click="getSelection">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <!--从这开始-->
            <el-row>
                <el-col v-if="haveData" :span="24" class="border_top"><br><br></el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <div class="table-wrapper">
                        <!-- 外表格 begin-->
                        <el-table v-if="haveData" :data="tableData" style="width: 95%;margin: auto;header-align: center;"
                                  @expand-change="explore"
                                  :row-key='getRowKeys' :expand-row-keys="expands">
                          <!-- 内嵌表格 begin-->
                          <el-table-column type="expand">
                                <template slot-scope="props">
                                    <el-table :data="TableData" style="width: 100%;margin-bottom: 10px;" border>
                                        <el-table-column prop="time" sortable label="时间" value-format="yyyy/MM/dd HH:mm:ss"  align="center"></el-table-column>
                                        <el-table-column prop="srczonename" sortable label="源域" align="center"></el-table-column>
                                        <el-table-column prop="dstzonename" sortable label="目的域" align="center"></el-table-column>
                                        <el-table-column prop="protocol" sortable label="协议" align="center"></el-table-column>
                                        <el-table-column prop="srcipaddr" sortable label="源IP" align="center"></el-table-column>
                                        <el-table-column prop="srcport" sortable label="源port" align="center"></el-table-column>
                                        <el-table-column prop="dstipaddr" sortable label="目的IP" align="center"></el-table-column>
                                        <el-table-column prop="dstport" sortable label="目的port" align="center"></el-table-column>
                                        <el-table-column prop="event" sortable label="Event" align="center"></el-table-column>
                                    </el-table>
                                </template>
                          </el-table-column>
                          <!-- 内嵌表格 end-->
                          <el-table-column v-for="(col,i) in cols" :prop="col.prop" :key="i"  sortable
                                             :label="col.label" align="center">
                          </el-table-column>
                        </el-table>
                        <!-- 外表格 end-->
                    </div>
                </el-col>
            </el-row>
            <el-row class="border_bottom">
                <el-col v-if="haveData"><br><br></el-col>
            </el-row>
            <!--到这结束-->
            <el-row>
                <el-col :span="24">
                    <!-- 分页 begin-->
                    <el-pagination
                            v-if="haveData"
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
    name: 'firewallInfo',
    data() {
        return {
            expands: [],
            //时间选择器
            startTime: '2021/10/14 00:00:00',
            stopTime: '2021/10/14 23:59:59',
            useSourcePort:false,
            useDestinationPort:false,
            //下拉框
            sourceDomain: '',
            destinationDomain: '',
            protocol: '',
            event: '',
            // 输入框
            srcIp: '',
            dstIp: '',
            srcIp_disabled: true,
            dstIp_disabled: true,
            // 复选框
            sourceIP: true,
            destinationIP: true,
            sourcePort: true,
            destinationPort: true,
            haveData: false,
            isSourceSelected: false,
            isDestinationSelected: false,
            // 表列
            cols: [],
            //源域下拉框值
            sourceDomain_options:[
                    {sourceDomain_value:1,label:'Trust'},
                    {sourceDomain_value:2,label:'Untrust'},
                    {sourceDomain_value:3,label:'Local'},
                    {sourceDomain_value:4,label:'Management'},
            ],
            sourceDomain_value: '',
            //目的域下拉框值
            destinationDomain_options: [
                    {destinationDomain_value:1,label:'Trust'},
                    {destinationDomain_value:2,label:'Untrust'},
                    {destinationDomain_value:3,label:'Local'},
                    {destinationDomain_value:4,label:'Management'},
            ],
            destinationDomain_value: '',
            //Protocol下拉框值
            protocol_option: [
                    {protocol_value:1,label:'UDP'},
                    {protocol_value:2,label:'TCP'},
                    {protocol_value:3,label:'IGMP'},
            ],
            protocol_value: '',
            //Event下拉框值
            event_options: [
                    {event_value:1,label:'Deny'},
                    {event_value:2,label:'Permit'},
            ],
            event_value: '',
            //外表数据
            tableData: [],
            //内表数据
            TableData: [],
            SRCIPADDR: '',
            DSTPORT: '',
            SRCPORT: '',
            DSTIPADDR: '',
            //分页信息
            pages: [
                {
                    pageSize: 20,
                    total: 1000,
                    currentPage: 1,
                },
            ],
            //起始日期
            disabledDate(time) {
                return time.getTime() > Date.now();
            },
            //终止日期
            disabledDate2(time) {
                return time.getTime() > Date.now();
            },
        }
    },
    watch: {
        // 下拉框事件
        sourceDomain_value: function (newV, oldV) {
            this.sourceDomain = newV
        },
        destinationDomain_value: function (newV, oldV) {
            this.destinationDomain = newV
        },
        protocol_value: function (newV, oldV) {
            this.protocol = newV
        },
        event_value: function (newV, oldV) {
            this.event = newV
        },

        //复选框事件
        sourceIP: function () {
            if (this.sourceIP === false) {
                this.useSourcePort = true
                this.srcIp_disabled = true
            } else {
                this.useSourcePort = false
                this.srcIp_disabled = false
            }
        },

        destinationIP: function () {
            if (this.destinationIP === false) {
                this.useDestinationPort=true
                this.destinationPort = false
                this.dstIp_disabled = true
            } else {
                this.useDestinationPort=false
                this.dstIp_disabled = false
            }
        }
    },
    created(){
        this.getSelection()
    },
    methods: {
        /*————控制表格智能展开一行 begin————*/
        getRowKeys: function (row) {
            if (row.SRCIPADDR !== null) {
                return row.SRCIPADDR + row.SRCPORT + row.DSTIPADDR + row.DSTPORT
                //  当源IP不为空时，将row.SRCIPADDR, row.SRCPORT, row.DSTIPADDR, row.DSTPORT的拼接作为行的唯一id,解决行id唯一问题
            } else {
                return row.DSTIPADDR + row.DSTPORT
                //  当源IP为空时，将row.DSTIPADDR, row.DSTPORT的拼接作为行的唯一id，解决行id唯一问题
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
                        that.expands.push(row.SRCIPADDR + row.SRCPORT + row.DSTIPADDR + row.DSTPORT)
                    } else {
                        that.expands.push(row.DSTIPADDR + row.DSTPORT)
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
            //绑定后台数据传输地址
            var url = "/getInfo"
            //传递参数
            var params = {
                'startTime': this.startTime,
                'stopTime': this.stopTime,
                'SRCZONENAME': this.sourceDomain,
                'DSTZONENAME': this.destinationDomain,
                'PROTOCOL': this.protocol,
                'EVENT': this.event,
                'DSTIPADDR': this.DSTIPADDR,
                'DSTPORT': this.DSTPORT,
                'SRCIPADDR': this.SRCIPADDR,
                'SRCPORT': this.SRCPORT,
            }
            this.$http.get(url, {params}).then(res => {
                this.TableData = res.data;
                console.log(res.data, "hhh")
            })
        },
        /*————日期选择器 begin————*/
        selectStartTime(val) {
            //开始时间
            this.startTime = val;
        },
        selectStopTime(val) {
            //截止时间
            this.stopTime = val;
        },
        getSelection() {
            //控制多选框并查询外表数据
            /*————多选框控制外表列属性 begin————*/
            while (this.cols.length !== 0) {
                this.cols.pop()
            }
            if (this.sourceIP === true) {
                this.cols.push({prop: 'SRCIPADDR', label: '源IP'})
            }
            if (this.sourcePort === true) {
                this.cols.push({prop: 'SRCPORT', label: '源端口'})
            }
            if (this.destinationIP === true) {
                this.cols.push({prop: 'DSTIPADDR', label: '目的IP'})
            }
            if (this.destinationPort === true) {
                this.cols.push({prop: 'DSTPORT', label: '目的端口'})
            }
            this.cols.push({prop: 'COUNT', label: '包个数'})
            this.haveData = true
            if (this.sourceIP === false && this.sourcePort === false && this.destinationIP === false && this.destinationPort === false) {
                this.cols.pop()
                this.haveData = false
            }
            /*————多选框控制外表列属性 end————*/
            console.log("hhhhhh", this.pages[0].currentPage, "jjjjj", this.pages[0].pageSize)
            //绑定后台数据传输地址
            var url = "/getSelection"
            //传递参数
            var params = {
                'startTime': this.startTime,
                'stopTime': this.stopTime,
                'sourceDomain': this.sourceDomain,
                'destinationDomain': this.destinationDomain,
                'protocol': this.protocol,
                'event': this.event,
                'sourceIP': this.sourceIP,
                'sourcePort': this.sourcePort,
                'destinationIP': this.destinationIP,
                'destinationPort': this.destinationPort,
                'srcIp': this.srcIp,
                'dstIp': this.dstIp,
                'pageNum': this.pages[0].currentPage,
                'pageSize': this.pages[0].pageSize,
            }
            this.$http.get(url, {params}).then(res => {
                this.haveData=true
                this.tableData = res.data.list;//渲染外表
                this.pages[0].total = res.data.total//向分页传递总数据
                console.log("hhhhhh", res.data)
            })
        },
        /*————分页控制部分 begin————*/
        currentPage: function (row) {
            this.pages[0].currentPage = row//取当前页码
            this.getSelection()//根据当前页码渲染数据
        },
        /*————分页控制部分 end————*/
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

.el-header, .el-footer {
    background: none;
    color: #ffffff;
    text-align: center;
    line-height: 60px;
    font-size: large;
    font-weight: bold;
}

.el-select {
    margin-right: 1%;
    width: 10%;
}

.el-row {
    background: none;
}

.el-col {
    background: none;
    padding: 10px;
}
.checkColor{
    color: #ffffff;
}
.demo-table-expand {
    font-size: 0;
}

.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}

.demo-table-expand .el-form-item {
    width: 50%;
}

.span_area {
    /*按钮区域*/
    margin-left: 40px;
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
    color: #fff;
    border-color: #18256f;
}

.table-wrapper /deep/ .el-table__row {
    background: none !important;
    color: #46d4ff;
}

/*————表格背景透明 end————*/
</style>
