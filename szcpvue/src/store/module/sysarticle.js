import {
  addArticle,
  editArticleById,
  editArticleContent,
  getArticleList,
  deleteArticleById,
  getMarkdownContent,
} from '@/api/sysarticle'
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
    addArticle({ state, commit },{article}){
      return new Promise((resolve, reject) => {
        try {
          addArticle(state.token,article).then(res => {
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
    editArticleById({ state, commit },{article}){
      return new Promise((resolve, reject) => {
        try {
          editArticleById(state.token,article).then(res => {
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
    deleteArticleById({ state, commit },{articleId}){
      return new Promise((resolve, reject) => {
        try {
          deleteArticleById(state.token,articleId).then(res => {
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
    getArticleList({ state, commit },{searchPream}){
      return new Promise((resolve, reject) => {
        try {
          getArticleList(state.token,searchPream).then(res => {
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
    getMarkdownContent({ state, commit },{url}){
      return new Promise((resolve, reject) => {
        try {
          getMarkdownContent(state.token,url).then(res => {
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
    
    editArticleContent({ state, commit },{articleContent}){
      return new Promise((resolve, reject) => {
        try {
          editArticleContent(state.token,articleContent).then(res => {
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
