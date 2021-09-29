import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import axios from 'axios'
import Blob from "@/tools/Blob.js"
import Export2Excel from "@/tools/Export2Excel"
Vue.prototype.$http = axios //正确的使用
axios.defaults.baseURL = '/api'
// axios.defaults.baseURL = 'http://localhost:8080/elk'
Vue.config.productionTip = false
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

