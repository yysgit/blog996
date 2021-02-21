import {
  markdownTest,
  getMarkdownList,
  addMarkdown,
  editMarkdown,
  delMarkdown,
  getMarkdownByid
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

    markdownTest({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          markdownTest(state.token).then(res => {
            const data = res.data;
            resolve(data)
          });
        } catch (error) {
          reject(error)
        }
      })

    },
    findMarkdownList({ state, commit },{searchPream}){
      return new Promise((resolve, reject) => {
        try {
          getMarkdownList(state.token,searchPream).then(res => {
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
    addMarkdown({ state, commit },{dbMarkdown},{content}){
      return new Promise((resolve, reject) => {
        try {
          addMarkdown(state.token,{dbMarkdown},{content}).then(res => {
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
    editMarkdown({ state, commit },{dbMarkdown},{content}){
      return new Promise((resolve, reject) => {
        try {
          editMarkdown(state.token,{dbMarkdown},{content}).then(res => {
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
    delMarkdown({ state, commit },{id}){
      return new Promise((resolve, reject) => {
        try {
          delMarkdown(state.token,{id}).then(res => {
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
    getMarkdownByid({ state, commit },{id}){
      return new Promise((resolve, reject) => {
        try {
          getMarkdownByid(state.token,{id}).then(res => {
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
