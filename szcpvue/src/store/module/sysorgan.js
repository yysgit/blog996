import {
  addOrganChild,
  editOrganById,
  getOrganList,
  deleteOrganById
} from '@/api/user'
import { setToken, getToken } from '@/libs/util'

export default {
  state: {
    token: getToken()
  },
  mutations: {
    setToken (state, token) {
      state.token = token
      setToken(token)
    }
  },
  getters: {

  },
  actions: {
    //提交表单
    addOrganChild({ state, commit },{organ}){
      return new Promise((resolve, reject) => {
        try {
          addOrganChild(state.token,organ).then(res => {
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
    editOrganById({ state, commit },{organ}){
      return new Promise((resolve, reject) => {
        try {
          editOrganById(state.token,organ).then(res => {
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
    deleteOrganById({ state, commit },{organId}){
      return new Promise((resolve, reject) => {
        try {
          deleteOrganById(state.token,organId).then(res => {
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
    //机构管理页面
    getOrganList({ state, commit }){
      return new Promise((resolve, reject) => {
        try {
          getOrganList(state.token).then(res => {
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
