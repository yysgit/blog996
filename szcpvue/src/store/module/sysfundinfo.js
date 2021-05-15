import {
  addFundInfo,
  editFundInfoById,
  getFundInfoList,
  deleteFundInfoById
} from '@/api/sysfundinfo'
import { setToken, getToken } from '@/libs/util'

export default {
  state: {
    token: getToken()
  },
  mutations: {
    setToken(state, token) {
      state.token = token
      setToken(token)
    }
  },
  getters: {

  },
  actions: {
    //提交表单
    addFundInfo({ state, commit }, { fundInfo }) {
      return new Promise((resolve, reject) => {
        try {
          addFundInfo(state.token, fundInfo).then(res => {
            const data = res.data;
            resolve(data)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },
    editFundInfoById({ state, commit }, { fundInfo }) {
      return new Promise((resolve, reject) => {
        try {
          editFundInfoById(state.token, fundInfo).then(res => {
            const data = res.data;
            resolve(data)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },
    deleteFundInfoById({ state, commit }, { fundInfoId }) {
      return new Promise((resolve, reject) => {
        try {
          deleteFundInfoById(state.token, fundInfoId).then(res => {
            const data = res.data;
            resolve(data)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },
    //基金类型管理页面
    getFundInfoList({ state, commit }, { searchPream }) {
      return new Promise((resolve, reject) => {
        try {
          getFundInfoList(state.token,searchPream).then(res => {
            const data = res.data;
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
