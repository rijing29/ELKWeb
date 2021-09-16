<template>
    <div>{{date}}</div>
</template>

<script>
export default {
    name: 'test',
    data: function() {
        return {
            date: new Date(),
        };
    },
    created() {
    },
    mounted() {
        let that= this;
        this.timer = setInterval(function() {
            that.date = new Date().toLocaleString();
        });
    },
    beforeDestroy: function() {
        if (this.timer) {
            clearInterval(this.timer);
        }
    },
    methods: {

    }
};
</script>
<style scoped>
.el-container {
    display: flex;
    flex-wrap: nowrap;
}

.el-pagination {
    margin-left: 50%;
}/*分页*/

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