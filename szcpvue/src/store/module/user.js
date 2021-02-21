import {
  setThatVue,
  login,
  logout,
  getUserInfo,
  addAdminUser,
  editAdminUserById,
  updatePasswordAdminUser,
  getOrganUserTree,
  getAdminUserList,
  getRoleAllList,
  deleteAdminUserById,
  editPasswordAdminUserById,
  getMessage,
  getHomeData,

} from '@/api/user'
import { setToken, getToken } from '@/libs/util'

export default {
  state: {
    that:'',
    username: '',
    userId: '',
    userRoleId:'',
    userOrganId:'',
    userLevel:'',
    avatorImgPath: '',
    token: getToken(),
    access: '',
    hasGetInfo: false,
    unreadCount: 0,
    messageUnreadList: [],
    messageReadedList: [],
    messageTrashList: [],
    messageContentStore: {},
    meunList:[],
    authentionList:[],
    iconList:[],
    adminUser:{}

  },
  mutations: {
    setThat(state,that){
      state.that = that
    },
    setAvator (state, avatorPath) {
      state.avatorImgPath = avatorPath
    },
    setUserId (state, id) {
      state.userId = id
    },
    setUserRoleId (state, id) {
      state.userRoleId = id
    },
    setUserOrganId (state, id) {
      state.userOrganId = id
    },
    setUserLevel (state, level){
      state.userLevel=level
    },
    setUserName (state, name) {
      state.username = name
    },
    setAccess (state, access) {
      state.access = access
    },
    setToken (state, token) {
      state.token = token
      setToken(token)
    },
    setHasGetInfo (state, status) {
      state.hasGetInfo = status
    },
    setMessageCount (state, count) {
      state.unreadCount = count
    },
    setMessageUnreadList (state, list) {
      state.messageUnreadList = list
    },
    setMessageReadedList (state, list) {
      state.messageReadedList = list
    },
    setMessageTrashList (state, list) {
      state.messageTrashList = list
    },
    setMeunList (state, list) {
      state.meunList = list
    },
    setAuthentionList (state, list) {
      state.authentionList = list
    },
    setIconList(state,list){
      state.iconList = list
    },
    setAdminUser (state, user) {
      state.adminUser = user
    },
    updateMessageContentStore (state, { msg_id, content }) {
      state.messageContentStore[msg_id] = content
    },
    moveMsg (state, { from, to, msg_id }) {
      const index = state[from].findIndex(_ => _.msg_id === msg_id)
      const msgItem = state[from].splice(index, 1)[0]
      msgItem.loading = false
      state[to].unshift(msgItem)
    }
  },
  getters: {
    messageUnreadCount: state => state.messageUnreadList.length,
    messageReadedCount: state => state.messageReadedList.length,
    messageTrashCount: state => state.messageTrashList.length,
  },
  actions: {

    //设置that
    setThatVue({commit},{that}){
      return new Promise((resolve, reject) => {
        commit('setThat', that);
        resolve("success");
      })
    },

    // 登录
    handleLogin ({ commit }, {username, password ,vcode}) {
      username = username.trim()
      return new Promise((resolve, reject) => {
       console.log(username,password);
        login({
          username,
          password,
          vcode
        }).then(res => {

          const data = res.data;
          if(data.code==200){
            commit('setToken', data.msg)
          }
          resolve(data)
        }).catch(err => {
          reject(err)
        })
      })
    },

    // 退出登录
    handleLogOut ({ state, commit }) {
      return new Promise((resolve, reject) => {
        /*logout(state.token).then(() => {
          commit('setToken', '')
          commit('setAccess', [])
          resolve()
        }).catch(err => {
          reject(err)
        })*/
        // 如果你的退出登录无需请求接口，则可以直接使用下面三行代码而无需使用logout调用接口
        commit('setToken', '')
        commit('setAccess', [])
        resolve()
      })
    },
    // 获取用户相关信息
    getUserInfo ({ state, commit }) {
      return new Promise((resolve, reject) => {
        try {
          getUserInfo(state.token).then(res => {
            if (res.data == '') {
              resolve(null);
            } else if(res.data.code!=200){
              resolve(res.data);
            }else {
              const data = res.data.data
             // console.log(data);
              commit('setAvator', data.adminUser.adminFullname)
              commit('setUserName', data.adminUser.adminName)
              commit('setUserId', data.adminUser.id)
              commit('setUserRoleId', data.adminUser.roleId)
              commit('setUserOrganId', data.adminUser.organId)
              commit('setUserLevel', data.adminUser.level)
              commit('setAccess', [])
              commit('setHasGetInfo', true)
              commit('setMeunList', data.meunList)
              commit('setAuthentionList', data.authentionList)
              commit('setAdminUser', data.adminUser)
              resolve(data)
            }
          }).catch(err => {
            reject(err)
          })
        } catch (error) {
          reject(error)
        }
      })
    },

    //提交表单
    addAdminUser({ state, commit },{adminUser}){
  return new Promise((resolve, reject) => {
    try {
      addAdminUser(state.token,adminUser).then(res => {

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


    editAdminUserById({ state, commit },{adminUser}){
      return new Promise((resolve, reject) => {
        try {
          editAdminUserById(state.token,adminUser).then(res => {

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


    updatePasswordAdminUser({ state, commit },{adminUser}){
      return new Promise((resolve, reject) => {
        try {
          updatePasswordAdminUser(state.token,adminUser).then(res => {

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

    deleteAdminUserById({ state, commit },{adminUserId}){
      return new Promise((resolve, reject) => {
        try {
          deleteAdminUserById(state.token,adminUserId).then(res => {

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

    editPasswordAdminUserById({ state, commit },{adminUserId}){
      return new Promise((resolve, reject) => {
        try {
          editPasswordAdminUserById(state.token,adminUserId).then(res => {

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


    getOrganUserTree({ state, commit }){
      return new Promise((resolve, reject) => {
        try {
          getOrganUserTree(state.token).then(res => {

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

    getRoleAllList({ state, commit }){
      return new Promise((resolve, reject) => {
        try {
          getRoleAllList(state.token).then(res => {

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

    getHomeData({ state, commit }){
      return new Promise((resolve, reject) => {
        try {
          getHomeData(state.token).then(res => {

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
    //用户管理页面
    getAdminUserList({ state, commit },{searchPream}){
      return new Promise((resolve, reject) => {
        try {
          getAdminUserList(state.token,searchPream).then(res => {
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
