import {
  addFundType,
  editFundTypeById,
  getFundTypeList,
  getFundTypeListByType,
  deleteFundTypeById
} from '@/api/sysfundtype'
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
    addFundType({ state, commit }, { fundType }) {
      return new Promise((resolve, reject) => {
        try {
          addFundType(state.token, fundType).then(res => {
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
    editFundTypeById({ state, commit }, { fundType }) {
      return new Promise((resolve, reject) => {
        try {
          editFundTypeById(state.token, fundType).then(res => {
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
    deleteFundTypeById({ state, commit }, { fundTypeId }) {
      return new Promise((resolve, reject) => {
        try {
          deleteFundTypeById(state.token, fundTypeId).then(res => {
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
    getFundTypeList({ state, commit }, { searchPream }) {
      return new Promise((resolve, reject) => {
        try {
          getFundTypeList(state.token,searchPream).then(res => {
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

    //基金类型
    getFundTypeListByType({ state, commit }, { searchPream }) {
      return new Promise((resolve, reject) => {
        try {
          getFundTypeListByType(state.token,searchPream).then(res => {
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
