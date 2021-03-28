import {
  addArticleMenu,
  editArticleMenuById,
  getArticleMenuList,
  getArticleMenuToArticleList,
  getArticleMenuToArticleListForHome,
  deleteArticleMenuById
} from '@/api/sysarticlemenu'
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
    addArticleMenu({ state, commit }, { articleMenu }) {
      return new Promise((resolve, reject) => {
        try {
          addArticleMenu(state.token, articleMenu).then(res => {
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
    editArticleMenuById({ state, commit }, { articleMenu }) {
      return new Promise((resolve, reject) => {
        try {
          editArticleMenuById(state.token, articleMenu).then(res => {
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
    deleteArticleMenuById({ state, commit }, { articleMenuId }) {
      return new Promise((resolve, reject) => {
        try {
          deleteArticleMenuById(state.token, articleMenuId).then(res => {
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
    //文章菜单管理页面
    getArticleMenuList({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          getArticleMenuList(state.token).then(res => {
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
    //文章菜单管理页面
    getArticleMenuToArticleList({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          getArticleMenuToArticleList(state.token).then(res => {
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
    
    getArticleMenuToArticleListForHome({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          getArticleMenuToArticleListForHome(state.token).then(res => {
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
