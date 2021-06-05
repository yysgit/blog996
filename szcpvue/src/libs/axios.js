import axios from 'axios'
import store from '@/store'

import qs from 'qs'


// import { Spin } from 'iview'
const addErrorLog = errorInfo => {
  const {statusText, status, request: {responseURL}} = errorInfo
  let info = {
    type: 'ajax',
    code: status,
    mes: statusText,
    url: responseURL
  }
  if (!responseURL.includes('save_error_logger')) store.dispatch('addErrorLog', info)
}

class HttpRequest {
  constructor(baseUrl = baseURL) {
    this.baseUrl = baseUrl
    this.queue = {}
  }

  getInsideConfig() {
    const config = {
      baseURL: this.baseUrl,
      headers: {
        //传递token
        Authorization: store.state.user.token
      },
      withCredentials: true  //打开cookie
    }
    return config
  }

  destroy(url) {
    delete this.queue[url]
    if (!Object.keys(this.queue).length) {
      // Spin.hide()
    }
  }

  interceptors(instance, url) {
    // 请求拦截
    instance.interceptors.request.use(config => {
      // 添加全局的loading...
      if (!Object.keys(this.queue).length) {
        // Spin.show() // 不建议开启，因为界面不友好
      }
      this.queue[url] = true
      return config
    }, error => {
      return Promise.reject(error)
    })
    // 响应拦截
    instance.interceptors.response.use(res => {
      // console.log('响应拦截123:', res);

      //必须实例化 Vue
      var that = store.state.user.that;
      // console.log('this:', that);
      if (that != null && that != '' && res.data!=null && res.data!="") {

        // console.log('res.data.code',  res.data.code);

        if (res.data.code == 501) {
          // console.log('res.data.msg', res.data.msg);
          that.$Modal.error({
            title: '信息',
            content: res.data.msg,
            onOk: () => {
              // console.log("跳转到:" + that.$config.login);
              store.commit('setToken', '');
              that.$router.push({
                name: that.$config['login']
              })
            }
          })
        }
        if (res.data.code == 500 || res.data.code == 502) {
          // console.log('res.data.msg', res.data.msg);
          that.$Message.error(res.data.msg);
        }
      }


      return res;
    }, error => {
      this.destroy(url)
      let errorInfo = error.response
      if (!errorInfo) {
        const {request: {statusText, status}, config} = JSON.parse(JSON.stringify(error))
        errorInfo = {
          statusText,
          status,
          request: {responseURL: config.url}
        }
      }
      // addErrorLog(errorInfo)
      return Promise.reject(error)
    })
  }

  request(options) {
    //将json 转成 formDate 格式的数据
    options.data = qs.stringify(options.data);

    const instance = axios.create()
    options = Object.assign(this.getInsideConfig(), options)

    this.interceptors(instance, options.url)
    return instance(options)
  }
}

export default HttpRequest
