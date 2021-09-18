import Vue from 'vue'
import VueRouter from 'vue-router'
import home from '../components/home'
import index from '../components/index.vue'
import css from '../components/css.vue'
import test from '../components/test.vue'
import dataInfo from '../components/HUAWEICloud/dataInfo.vue'
import showSoftNameTable from '../components/HUAWEICloud/showSoftNameTable.vue'
import showNodeTypeTable from '../components/HUAWEICloud/showNodeTypeTable.vue'
import deviceState from '../components/HUAWEICloud/deviceState.vue'
import userUseRate from '../components/HUAWEICloud/userUseRate.vue'
import softwareLicense from '../components/HUAWEICloud/softwareLicense.vue'
import memUse from '../components/HUAWEICloud/memUse.vue'
import firewallInfo from '../components/netData/firewallInfo.vue'
import test2 from "../components/css.vue";
import nodeDeploy from "../components/HUAWEICloud/nodeDeploy.vue";
import useFrequencyAll from "../components/dataDisplay/useFrequencyAll.vue";
import useTimeAll from "../components/dataDisplay/useTimeAll.vue";
import useFrequencyOne from "../components/dataDisplay/useFrequencyOne";
import useTimeOne from "../components/dataDisplay/useTimeOne";
import IPS from "../components/netData/IPS";
import IMCAlarm from "../components/netData/IMCAlarm";
import ipmiLogAlarm from "../components/IPMI/ipmiLogAlarm";
import ipmiLogInfo from "../components/IPMI/ipmiLogInfo";
import epyCard from "../components/employee/epyCard";
import epyCardDPM from "../components/employee/epyCardDPM";
import epyDQMDS from "../components/employee/epyDQMDS";
import epyDQMDSDPM from "../components/employee/epyDQMDSDPM";
import epyNet from "../components/employee/epyNet";
import epySoftware from "../components/employee/epySoftware";
import serverPublic from "../components/server/serverPublic";
import serverWin from "../components/server/serverWin";
import serveStorage from "../components/server/serveStorage";
import App from "@/App";

Vue.use(VueRouter)

const routes = [
    {path: '/test', name: 'test', component: test},
    {path: '/test2', name: 'test2', component: test2},
    {path: '/css', name: 'css', component: css},
    {path: '/', name: 'index', component: index},
    {path: '/home', name: 'home', component: home,
        children: [
            {path: '/firewallInfo', name: 'firewallInfo', component: firewallInfo},
            {path: '/useFrequencyAll', name: 'useFrequencyAll', component: useFrequencyAll},
            {path: '/useTimeAll', name: 'useTimeAll', component: useTimeAll},
            {path: '/useFrequencyOne', name: 'useFrequencyOne', component: useFrequencyOne},
            {path: '/useTimeOne', name: 'useTimeOne', component: useTimeOne},
            {path: '/IPS', name: 'IPS', component: IPS},
            {path: '/IMCAlarm', name: 'IMCAlarm', component: IMCAlarm},
            {path: '/ipmiLogAlarm', name: 'ipmiLogAlarm', component: ipmiLogAlarm},
            {path: '/ipmiLogInfo', name: 'ipmiLogInfo', component: ipmiLogInfo},
            {path: '/epyCard', name: 'epyCard', component: epyCard},
            {path: '/epyDQMDS', name: 'epyDQMDS', component: epyDQMDS},
            {path: '/epyNet', name: 'epyNet', component: epyNet},
            {path: '/epySoftware', name: 'epySoftware', component: epySoftware},
            {path: '/serverPublic', name: 'serverPublic', component: serverPublic},
            {path: '/serverWin', name: 'serverWin', component: serverWin},
            {path: '/epyCardDPM', name: 'epyCardDPM', component: epyCardDPM},
            {path: '/epyDQMDSDPM', name: 'epyDQMDSDPM', component: epyDQMDSDPM},
            {path: '/serveStorage', name: 'serveStorage', component: serveStorage},
            {path: '/nodeDeploy', name: 'nodeDeploy', component: nodeDeploy},
            {path: '/dataInfo', name: 'dataInfo', component: dataInfo},
            {path: '/showSoftNameTable', name: 'showSoftNameTable', component: showSoftNameTable},
            {path: '/showNodeTypeTable', name: 'showNodeTypeTable', component: showNodeTypeTable},
            {path: '/deviceState', name: 'deviceState', component: deviceState},
            {path: '/userUseRate', name: 'userUseRate', component: userUseRate},
            {path: '/softwareLicense', name: 'softwareLicense', component: softwareLicense},
            {path: '/memUse', name: 'memUse', component: memUse},
        ]
    },
]

const router = new VueRouter({
    routes,
    mode: 'history',
})

export default router
