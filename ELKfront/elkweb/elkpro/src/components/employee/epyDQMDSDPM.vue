<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="24" align="left">
                    <div class="title">
                        员工外网浏览信息
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" align="left">
                    <!--起始日期下拉框-->
                    日期：
                    <el-date-picker
                            default-value
                            v-model="Time"
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
                                <el-table v-if="haveData" :data="tableData" style="width: 95%;margin: auto;header-align: center;">
                                    <el-table-column prop="NAME" label="单位名称" align="center"></el-table-column>
                                    <el-table-column prop="AVGDAYS" label="工作日使用率" align="center"></el-table-column>
                                    <el-table-column prop="COUNTONEDAY" label="平均每天使用次数" align="center"></el-table-column>
                                    <el-table-column prop="JB" label="加班时间使用率" align="center"></el-table-column>
                                    <el-table-column prop="ONETYPE" label="日常功能使用率" align="center"></el-table-column>
                                    <el-table-column prop="TWOTYPE" label="管理功能使用率" align="center"></el-table-column>
                                    <el-table-column prop="THRTYPE" label="专业功能使用率" align="center"></el-table-column>
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
    name: "epyDQMDSDPM",
    data(){
        return{
            haveData:true,
            Time:'',//日期选择器
            date:'',//日期变量
            expands: [],
            ip:'',
            time:'',
            tableData:[
                {NAME:'综合办公室',AVGDAYS:'98.3',COUNTONEDAY:'95',JB:'28.3',ONETYPE:'76.2',TWOTYPE:'67.2',THRTYPE:'83.3'},
                {NAME:'科技管理部',AVGDAYS:'95.6',COUNTONEDAY:'86',JB:'22.2',ONETYPE:'76.8',TWOTYPE:'52.2',THRTYPE:'82.4'},
                {NAME:'人事部（党委组织部）',AVGDAYS:'94.4',COUNTONEDAY:'83',JB:'23.7',ONETYPE:'76.9',TWOTYPE:'76.0',THRTYPE:'81.4'},
                {NAME:'财务资产部',AVGDAYS:'91.6',COUNTONEDAY:'80',JB:'25.7',ONETYPE:'76.8',TWOTYPE:'56.4',THRTYPE:'85.3'},
                {NAME:'经营管理部',AVGDAYS:'90.2',COUNTONEDAY:'83',JB:'21.3',ONETYPE:'76.2',TWOTYPE:'53.4',THRTYPE:'86.6'},
                {NAME:'群团工作部（工会、团委）',AVGDAYS:'93.5',COUNTONEDAY:'85',JB:'20.2',ONETYPE:'76.4',TWOTYPE:'45.5',THRTYPE:'89.4'},
                {NAME:'企业级技术专家',AVGDAYS:'95.8',COUNTONEDAY:'86',JB:'19.0',ONETYPE:'76.3',TWOTYPE:'65.5',THRTYPE:'83.6'},
                {NAME:'勘探规划研究室',AVGDAYS:'97.0',COUNTONEDAY:'85',JB:'23.3',ONETYPE:'76.4',TWOTYPE:'86.4',THRTYPE:'86.4'},
                {NAME:'松辽勘探研究室',AVGDAYS:'98.8',COUNTONEDAY:'82',JB:'22.5',ONETYPE:'76.0',TWOTYPE:'85.8',THRTYPE:'86.2'},
                {NAME:'非常规勘探研究室',AVGDAYS:'92.7',COUNTONEDAY:'87',JB:'24.0',ONETYPE:'76.0',TWOTYPE:'64.8',THRTYPE:'86.3'},
                {NAME:'天然气研究室',AVGDAYS:'98.3',COUNTONEDAY:'88',JB:'25.3',ONETYPE:'76.0',TWOTYPE:'52.9',THRTYPE:'86.2'},
                {NAME:'地质试验研究室',AVGDAYS:'92.5',COUNTONEDAY:'83',JB:'21.5',ONETYPE:'76.5',TWOTYPE:'73.7',THRTYPE:'86.3'},
                {NAME:'有机地球化学研究室',AVGDAYS:'95.5',COUNTONEDAY:'84',JB:'22.5',ONETYPE:'76.7',TWOTYPE:'61.8',THRTYPE:'86.7'},
            ],
        }
    },
    // created(){//自动渲染数据
    //     this.getDate()
    //     this.getIPMIAlarm()
    //     this.Time=this.time
    // },
    methods: {
        getRowKeys: function (row) {//控制表格只能展开一行
            return row.time + row.ip + row.info
            //  将row.time、row.ip、row.info的拼接作为行的唯一id,解决行id唯一问题
        },

        selectStartTime(val) {//日期选择器
            //开始时间
            this.Time = val;
            this.date=this.Time;
            // this.getIPMIAlarm();
            console.log(this.time)
        },
        formatter (thistime, fmt) {//js格式化时间
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
        getDate(){//获取当前时间
            var date = this.formatter(new Date(), 'yyyy/MM/dd hh:mm:ss')
            this.date=date.toLocaleString()
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