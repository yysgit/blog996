import axios from '@/libs/api.request'
import qs from 'qs'
export const login = ({ username, password,vcode }) => {
  const data = {
    username,
    password,
    vcode
  }
  return axios.request({
    url: '/sys/admin/login',
    data,
    method: 'post'
  })
}

export const getVcode = () => {
  return axios.request({
    url: '/sys/vcode',
    method: 'get'
  })
}

export const getUserInfo = (token) => {
  return axios.request({
    url: '/sys/admin/findAdminUserInfo',
    params: {
      token
    },
    method: 'get'
  })
}

export const getAllIcon= (token) => {
  return axios.request({
    url: '/sys/menu/findAllIcon',
    params: {
      token
    },
    method: 'get'
  })
}

export const addMenu= (token,menu) => {
  return axios.request({
    url: '/sys/menu/addMenu',
    params: {
      token,
      menu
    },
    method: 'post'
  })
}

export const addRole= (token,role) => {
  return axios.request({
    url: '/sys/role/addRole',
    params: {
      token,
      role
    },
    method: 'post'
  })
}

export const addAdminUser= (token,adminUser) => {
  return axios.request({
    url: '/sys/admin/addAdminUser',
    params: {
      token,
      adminUser
    },
    method: 'post'
  })
}

export const addOrganChild= (token,organ) => {
  return axios.request({
    url: '/sys/organ/addOrgan',
    params: {
      token,
      organ
    },
    method: 'post'
  })
}

export const editOrganById= (token,organ) => {
  return axios.request({
    url: '/sys/organ/updateOrgan',
    params: {
      token,
      organ
    },
    method: 'post'
  })
}

export const editMenuById= (token,menu) => {
  return axios.request({
    url: '/sys/menu/updateMenu',
    params: {
      token,
      menu
    },
    method: 'post'
  })
}

export const editRoleById= (token,role) => {
  return axios.request({
    url: '/sys/role/updateRole',
    params: {
      token,
      role
    },
    method: 'post'
  })
}

export const getMenuManageList= (token) => {
  return axios.request({
    url: '/sys/menu/findMenuByMyRoleId',
    params: {
      token
    },
    method: 'post'
  })
}

export const getOrganList= (token) => {
  return axios.request({
    url: '/sys/organ/findOrganTree',
    params: {
      token
    },
    method: 'post'
  })
}

export const getOrganUserTree= (token) => {
  return axios.request({
    url: '/sys/organ/findOrganUserTree',
    params: {
      token
    },
    method: 'post'
  })
}

export const getRoleMenuList= (token,roleId) => {
  return axios.request({
    url: '/sys/menu/findMenuByRoleId',
    params: {
      token,
      roleId
    },
    method: 'post'
  })
}

export const deleteOrganById= (token,organId) => {
  return axios.request({
    url: '/sys/organ/deleteOrgan',
    params: {
      token,
      organId
    },
    method: 'post'
  })
}

export const getRoleList= (token,searchPream) => {
  return axios.request({
    url: '/sys/role/findRoleList',
    params: {
      token,
      searchPream
    },
    method: 'post'
  })
}

export const getRoleAllList= (token) => {
  return axios.request({
    url: '/sys/role/findRoleAllList',
    params: {
      token
    },
    method: 'post'
  })
}

export const getHomeData= (token) => {
  return axios.request({
    url: '/sys/home/findHomeData',
    params: {
      token
    },
    method: 'post'
  })
}

export const getAdminUserList= (token,searchPream) => {
  return axios.request({
    url: '/sys/admin/findAdminUserListByOrganId',
    params: {
      token,
      searchPream
    },
    method: 'post'
  })
}

export const addOrEditRoleMenu= (token,idArray,myRoleId) => {
  return axios.request({
    url: '/sys/menu/addOrEditRoleMenuByIdArray',
    params: {
      token,
      idArray,
      myRoleId
    },
    method: 'post'
  })
}

export const deleteMenuById= (token,menuId) => {
  return axios.request({
    url: '/sys/menu/deleteMenu',
    params: {
      token,
      menuId
    },
    method: 'post'
  })
}

export const deleteAdminUserById= (token,adminUserId) => {
  return axios.request({
    url: '/sys/admin/deleteAdminUser',
    params: {
      token,
      adminUserId
    },
    method: 'post'
  })
}

export const editPasswordAdminUserById= (token,adminUserId) => {
  return axios.request({
    url: '/sys/admin/editPasswordAdminUser',
    params: {
      token,
      adminUserId
    },
    method: 'post'
  })
}

export const deleteRoleById= (token,roleId) => {
  return axios.request({
    url: '/sys/role/deleteRole',
    params: {
      token,
      roleId
    },
    method: 'post'
  })
}

export const editAdminUserById= (token,adminUser) => {
  return axios.request({
    url: '/sys/admin/updateAdminUser',
    params: {
      token,
      adminUser
    },
    method: 'post'
  })
}

export const updatePasswordAdminUser= (token,adminUser) => {
  return axios.request({
    url: '/sys/admin/updatePasswordAdminUser',
    params: {
      token,
      adminUser
    },
    method: 'post'
  })
}

export const logout = (token) => {
  return axios.request({
    url: '/sys/logout',
    method: 'post'
  })
}

export const getContentByMsgId = msg_id => {
  return axios.request({
    url: '/sys/message/content',
    method: 'get',
    params: {
      msg_id
    }
  })
}

export const hasRead = msg_id => {
  return axios.request({
    url: 'message/has_read',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const removeReaded = msg_id => {
  return axios.request({
    url: 'message/remove_readed',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const restoreTrash = msg_id => {
  return axios.request({
    url: 'message/restore',
    method: 'post',
    data: {
      msg_id
    }
  })
}




