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
                    <!--员工查询输入框 -->
                    <span class="span_area">
                        <el-input v-model="username" placeholder="请输入员工姓名" style="margin-right: 1%;width: 10%;"></el-input>
                    </span>
                    <!-- 查询按钮 -->
                    <span class="span_area">
                        <el-button icon="el-icon-search" type="primary">查询</el-button>
                    </span>
                </el-col>
            </el-row>
            <el-row>
                <!--————表格区域 begin————-->
                <el-col :span="24">
                    <el-row >
                        <el-col :span="24" class="border_top">
                            <div style="height: 50px;line-height: 70px;padding-left: 40px;color: #17caf0">员工 - 张三  (一周内信息)</div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24" style="padding: 0">
                            <div class="table-wrapper">
                                <!-- 外表格 begin-->
                                <el-table v-if="haveData" :data="tableData" style="width: 95%;margin: auto;header-align: center;">
                                    <el-table-column prop="TIME" label="日期" align="center"></el-table-column>
                                    <el-table-column prop="WORKCOUNTSOFWORKTIME" label="工作时间 - 工作网站" align="center"></el-table-column>
                                    <el-table-column prop="OTHERCOUNTSOFWORKTIME" label="工作时间 - 搜索网站" align="center"></el-table-column>
                                    <el-table-column prop="PLAYCOUNTSOFWORKTIME" label="工作时间 - 娱乐网站" align="center"></el-table-column>
                                    <el-table-column prop="WORKCOUNTSOFSPARETIME" label="业余时间 - 工作网站" align="center"></el-table-column>
                                    <el-table-column prop="OTHERCOUNTSOFSPARETIME" label="业余时间 - 搜索网站" align="center"></el-table-column>
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
    name: "epyNet",
    data(){
        return{
            haveData:true,
            Time:'',//日期选择器
            date:'',//日期变量
            expands: [],
            username:'',
            ip:'',
            time:'',
            tableData:[
                {TIME:'2021-08-26',WORKCOUNTSOFWORKTIME:'80',OTHERCOUNTSOFWORKTIME:'63',PLAYCOUNTSOFWORKTIME:'32',WORKCOUNTSOFSPARETIME:'12',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-25',WORKCOUNTSOFWORKTIME:'75',OTHERCOUNTSOFWORKTIME:'21',PLAYCOUNTSOFWORKTIME:'21',WORKCOUNTSOFSPARETIME:'45',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-23',WORKCOUNTSOFWORKTIME:'54',OTHERCOUNTSOFWORKTIME:'14',PLAYCOUNTSOFWORKTIME:'32',WORKCOUNTSOFSPARETIME:'64',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-22',WORKCOUNTSOFWORKTIME:'87',OTHERCOUNTSOFWORKTIME:'32',PLAYCOUNTSOFWORKTIME:'32',WORKCOUNTSOFSPARETIME:'43',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-21',WORKCOUNTSOFWORKTIME:'45',OTHERCOUNTSOFWORKTIME:'53',PLAYCOUNTSOFWORKTIME:'23',WORKCOUNTSOFSPARETIME:'43',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-20',WORKCOUNTSOFWORKTIME:'86',OTHERCOUNTSOFWORKTIME:'12',PLAYCOUNTSOFWORKTIME:'31',WORKCOUNTSOFSPARETIME:'43',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-19',WORKCOUNTSOFWORKTIME:'68',OTHERCOUNTSOFWORKTIME:'34',PLAYCOUNTSOFWORKTIME:'21',WORKCOUNTSOFSPARETIME:'46',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-18',WORKCOUNTSOFWORKTIME:'54',OTHERCOUNTSOFWORKTIME:'12',PLAYCOUNTSOFWORKTIME:'21',WORKCOUNTSOFSPARETIME:'32',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-17',WORKCOUNTSOFWORKTIME:'65',OTHERCOUNTSOFWORKTIME:'43',PLAYCOUNTSOFWORKTIME:'23',WORKCOUNTSOFSPARETIME:'53',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-16',WORKCOUNTSOFWORKTIME:'86',OTHERCOUNTSOFWORKTIME:'35',PLAYCOUNTSOFWORKTIME:'24',WORKCOUNTSOFSPARETIME:'32',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-15',WORKCOUNTSOFWORKTIME:'95',OTHERCOUNTSOFWORKTIME:'41',PLAYCOUNTSOFWORKTIME:'34',WORKCOUNTSOFSPARETIME:'64',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-14',WORKCOUNTSOFWORKTIME:'43',OTHERCOUNTSOFWORKTIME:'31',PLAYCOUNTSOFWORKTIME:'26',WORKCOUNTSOFSPARETIME:'32',OTHERCOUNTSOFSPARETIME:'0'},
                {TIME:'2021-08-13',WORKCOUNTSOFWORKTIME:'32',OTHERCOUNTSOFWORKTIME:'31',PLAYCOUNTSOFWORKTIME:'26',WORKCOUNTSOFSPARETIME:'33',OTHERCOUNTSOFSPARETIME:'0'},
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