import {

  addRole,
  editRoleById,
  addOrEditRoleMenu,
  getRoleMenuList,
  getRoleList,
  deleteRoleById,

} from '@/api/user'
import { setToken, getToken } from '@/libs/util'

export default {
  state: {
    token: getToken(),
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
    addOrEditRoleMenu({ state, commit },{idArray,myRoleId}){
      return new Promise((resolve, reject) => {
        try {
          addOrEditRoleMenu(state.token,idArray,myRoleId).then(res => {
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

    //提交表单
    addRole({ state, commit },{role}){
      return new Promise((resolve, reject) => {
        try {
          addRole(state.token,role).then(res => {
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

    //编辑角色
    editRoleById({ state, commit },{role}){
      return new Promise((resolve, reject) => {
        try {
          editRoleById(state.token,role).then(res => {
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

    //角色菜单树
    getRoleMenuList({ state, commit },{roleId}){
      return new Promise((resolve, reject) => {
        try {
          getRoleMenuList(state.token,roleId).then(res => {
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

    //菜单管理页面
    getRoleList({ state, commit },{searchPream}){
      return new Promise((resolve, reject) => {
        try {
          getRoleList(state.token,searchPream).then(res => {
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

    //删除表单
    deleteRoleById({ state, commit },{roleId}){
      return new Promise((resolve, reject) => {
        try {
          deleteRoleById(state.token,roleId).then(res => {
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
