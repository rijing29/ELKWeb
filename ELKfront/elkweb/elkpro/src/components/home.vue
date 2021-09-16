<template>
    <el-container>
        <el-header>资源管理系统</el-header>
        <el-container>
            <el-aside width="300px">
                <h5 class="title">导航栏</h5>
                <el-menu class="el-menu-vertical-demo"
                         @open="handleOpen"
                         @close="handleClose"
                         style="text-align: left;border-right: none"
                         active-text-color="#ea6026"
                         background-color="#000a40"
                         text-color="#2791f3"
                         default-active="/"
                         :unique-opened="true"
                         router>
                    <el-menu-item index="/">
                        <i class="el-icon-s-home icon_color"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-user-solid icon_color"></i>
                            <span>员工行为分析</span>
                        </template>
                        <el-menu-item index="/epyCard">员工刷卡行为分析</el-menu-item>
                        <el-menu-item index="/epyNet">员工外网浏览信息</el-menu-item>
                        <el-menu-item index="/epyDQMDS">员工DQMDS行为</el-menu-item>
                        <el-menu-item index="/epySoftware">员工使用软件行为</el-menu-item>
                        <el-menu-item index="/epyCardDPM">单位刷卡统计</el-menu-item>
                        <el-menu-item index="/epyDQMDSDPM">单位DQMDS行为统计</el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-s-marketing icon_color"></i>
                            <span slot="title">软件许可分析</span>
                        </template>
                        <el-submenu index="2-2">
                            <template slot="title">全部软件</template>
                            <el-menu-item index="/useFrequencyAll">使用频率</el-menu-item>
                            <el-menu-item index="/useTimeAll">使用时间</el-menu-item>
                        </el-submenu>
                        <el-submenu index="2-3" style="background: none">
                            <template slot="title">单一软件</template>
                            <el-menu-item index="/useFrequencyOne">使用频率</el-menu-item>
                            <el-menu-item index="/useTimeOne">使用时间</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-s-data icon_color"></i>
                            <span slot="title">网络数据</span>
                        </template>
                        <el-menu-item index="/firewallInfo">防火墙数据包</el-menu-item>
                        <el-menu-item index="/IPS">IPS告警分析</el-menu-item>
                        <el-menu-item index="/IMCAlarm">交换机告警信息</el-menu-item>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-s-order icon_color"></i>
                            <span slot="title">IPMI日志</span>
                        </template>
                        <el-menu-item index="/ipmiLogAlarm">IPMI日志告警信息</el-menu-item>
                        <el-menu-item index="/ipmiLogInfo">IPMI日志查询</el-menu-item>
                    </el-submenu>
                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-s-platform icon_color"></i>
                            <span slot="title">服务器信息</span>
                        </template>
                        <el-menu-item index="/serverPublic">公共服务器性能数据</el-menu-item>
                        <el-menu-item index="/serverWin">Windows服务器日志</el-menu-item>
                        <el-menu-item index="/serveStorage">存储使用情况</el-menu-item>
                    </el-submenu>
                    <el-menu-item index="/home">
                        <i class="el-icon-s-home icon_color"></i>
                        <span slot="title">主页</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
        <span class="bg_icon"></span>
    </el-container>
</template>

<script>
export default {
    name: "home",
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    }
};
</script>

<style scoped>
.el-container, .el-main{
    background: #000a40;
}
.el-header {
    background: url("../assets/bg_header.png");
    background-size:100% 100%;
    font-size: 20px;
    font-weight: bold;
    color: #caf6f6;
    text-align: center;
    letter-spacing: 10px;
    line-height: 60px;
    margin-top: 20px;
}
.el-aside {
    background: url("../assets/bg_aside.png");
    background-size:100% 100%;
    color: #ffffff;
    text-align: center;
    padding: 40px 40px 150px;
}
.el-main {
    color: #ffffff;
    text-align: center;
    padding: 0;
    height: 90vh;
}
.title {
    color: #ffffff;
    font-size: 16px;
}

body > .el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}
.icon_color{
    color: #16daec;
}
.bg_icon{
    position: absolute;
    width: 91vh;
    height: 56vh;
    background: url("../assets/bg_earth1.png");
    background-size: 100% 100%;
    margin: auto;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    opacity: 0.9;
    z-index: 0;
}
</style>