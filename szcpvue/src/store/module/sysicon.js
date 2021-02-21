import {
  getVcode,
  getAllIcon,
} from '@/api/user'


export default {
  state: {

  },
  mutations: {

  },
  getters: {

  },
  actions: {

    //获取验证码
    getVcode() {

      return new Promise((resolve, reject) => {

        getVcode().then(res => {
          const data = res.data
          resolve(data)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 获取所有图标
    getAllIcon({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          getAllIcon().then(res => {
            const data = res.data.data;
            commit('setIconList', data)
            // console.log(data);
            resolve(data)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },

  }
}
