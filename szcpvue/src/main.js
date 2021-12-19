// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
// import VueAMap from 'vue-amap';
import iView from 'iview'
import config from '@/config'
import importDirective from '@/directive'
import { directive as clickOutside } from 'v-click-outside-x'
import installPlugin from '@/plugin'
import './index.less'
import '@/assets/icons/iconfont.css'
import TreeTable from 'tree-table-vue'
import VOrgTree from 'v-org-tree'
import 'v-org-tree/dist/v-org-tree.css'
import axios from 'axios'

import 'babel-polyfill'
import Es6Promise from 'es6-promise'
require('es6-promise').polyfill()
Es6Promise.polyfill()



Vue.prototype.$axios = axios

 

// 实际打包时应该不引入mock
/* eslint-disable */
require('@/mock')

Vue.use(iView, {
})
Vue.use(TreeTable)
Vue.use(VOrgTree)

// Vue.use(VueAMap);
// VueAMap.initAMapApiLoader({
//   key: 'e1dedc6bdd765d46693986ff7ff969f4',
//   plugin: [
//     // 插件
//     'AMap.Autocomplete',
//     'AMap.PlaceSearch',
//     'AMap.Scale',
//     'AMap.OverView',
//     'AMap.ToolBar',
//     'AMap.MapType',
//     'AMap.PolyEditor',
//     'AMap.CircleEditor',
//     "AMap.Geolocation" //定位控件，用来获取和展示用户主机所在的经纬度位置
//   ],
//   // 默认高德 sdk 版本为 1.4.4
//   v: '1.4.4'
// });


/**
 * @description 注册admin内置插件
 */
installPlugin(Vue)
/**
 * @description 生产环境关掉提示
 */
Vue.config.productionTip = false
/**
 * @description 全局注册应用配置
 */
Vue.prototype.$config = config
/**
 * 注册指令
 */
importDirective(Vue)
Vue.directive('clickOutside', clickOutside)

/* eslint-disable no-new */
store.dispatch('getUserInfo').then((data) => {
  new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
  })

  // if(data==null){
  //   router.push({
  //     name:'sys/login'
  //   })
  // }
  /*else if(data.code==501){
    router.push({
      name:'login'
    })
  }*/
})
