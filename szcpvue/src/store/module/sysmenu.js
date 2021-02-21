import {
  addMenu,
  editMenuById,
  getMenuManageList,
  deleteMenuById
} from '@/api/user'
import { setToken, getToken } from '@/libs/util'

export default {
  state: {
    token: getToken(),
    access: '',
  },
  mutations: {
    setToken (state, token) {
      state.token = token
      setToken(token)
    },
  },
  getters: {

  },
  actions: {
    //提交表单
    addMenu({ state, commit },{menu}){
      return new Promise((resolve, reject) => {
        try {
          addMenu(state.token,menu).then(res => {
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

    //编辑表单
    editMenuById({ state, commit },{menu}){
      return new Promise((resolve, reject) => {
        try {
          editMenuById(state.token,menu).then(res => {
            const data = res.data.data;
            resolve(data)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },

    //菜单管理页面
    getMenuManageList({ state, commit }){
      return new Promise((resolve, reject) => {
        try {
          getMenuManageList(state.token).then(res => {
            const data = res.data.data;
            resolve(data)
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },

    //删除表单
    deleteMenuById({ state, commit },{menuId}){
      return new Promise((resolve, reject) => {
        try {
          deleteMenuById(state.token,menuId).then(res => {
            const data = res.data.data;
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
