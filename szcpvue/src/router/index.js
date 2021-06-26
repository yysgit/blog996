import Vue from 'vue'
import Router from 'vue-router'
import routes from './routers'
import store from '@/store'
import iView from 'iview'
import { setToken, getToken, canTurnTo, setTitle, menuVerifAuthention } from '@/libs/util'
import config from '@/config'

const { homeName } = config

Vue.use(Router)
const router = new Router({
  routes,
  mode: 'history'
})


//处理Error: Avoided redundant navigation to current location: 错误
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}


const LOGIN_PAGE_NAME = 'sys/login'

//权限过滤
const turnTo = (to, access, next) => {
  if (canTurnTo(to.name, access, routes)) next() // 有权限，可访问
  else next({ replace: true, name: 'error_401' }) // 无权限，重定向到401页面
}

// iView UI 教程
//https://cloud.tencent.com/developer/doc/1271

//监控所有请求路径, 判断是否登录
//资料地址: https://router.vuejs.org/guide/advanced/navigation-guards.html#global-before-guards
router.beforeEach((to, from, next) => {

  //to, from, next
  //to: 向某某路径跳转的路径信息
  //form: 当前的路径信息
  //next: 表示执行跳转函数


  //LoadingBar 加载进度条:  https://cloud.tencent.com/developer/section/1489939
  iView.LoadingBar.start();

  //得到是否有Token,如果有, 表示登陆过了
  const token = getToken();
  // console.log(to.name + ":::" + token);

  // console.log("访问路径：", to.path);

  // console.log(store.state.user.authentionList);


  if (to.path.indexOf("sys") == -1) {
    // console.log(1);
    next() // 正常跳转
  } else if (token && store.state.user.meunList.length == 0) {
    // console.log(2);
    setToken("");
    next({
      name: LOGIN_PAGE_NAME // 跳转到登录页
    })
  } else {
    if (!token && to.name !== LOGIN_PAGE_NAME) {
      // console.log(3);
      // 未登录且要跳转的页面不是登录页: 强制跳转到登陆页面
      next({
        name: LOGIN_PAGE_NAME // 跳转到登录页
      })
    } else if (!token && to.name === LOGIN_PAGE_NAME) {
      // console.log(4);
      // 未登陆且要跳转的页面是登录页: 跳转到登陆页面
      next() // 跳转
    } else if (token && to.name === LOGIN_PAGE_NAME) {
      // console.log(5);
      // 已登录且要跳转的页面是登录页 : 强制跳转到homeName 页面
      next({
        name: homeName // 跳转到homeName页
      })
    } else if (menuVerifAuthention(to.name, store.state.user.authentionList)) {
      // console.log(6);
      // 已登录且要跳转的页面不是登陆页面: 正常访问(添加权限控制)
      next() // 正常跳转
    }

    // console.log(7);

  }
})

router.afterEach(to => {
  setTitle(to, router.app);
  iView.LoadingBar.finish();
  window.scrollTo(0, 0);
//   setTimeout(()=>{

//   //百度统计
//   var _hmt = _hmt || [];
//   (function() {
//     document.getElementById('baidu_yys') && document.getElementById('baidu_yys').remove();
//     var hm = document.createElement("script");
//     hm.src = "https://hm.baidu.com/hm.js?de9810704a72572716d463050072f9da";
//     hm.id = "baidu_yys"
//     var s = document.getElementsByTagName("script")[0]; 
//     s.parentNode.insertBefore(hm, s);
//   })();
// },0);

})

export default router
