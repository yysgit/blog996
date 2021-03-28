import axios from '@/libs/api.request'
import qs from 'qs'


export const getArticleMenuList= (token) => {
  return axios.request({
    url: '/sys/articleMenu/findArticleMenuList',
    params: {
      token
    },
    method: 'post'
  })
}


export const addArticleMenu= (token,articleMenu) => {
  return axios.request({
    url: '/sys/articleMenu/addArticleMenu',
    params: {
      token,
      articleMenu
    },
    method: 'post'
  })
}


export const editArticleMenuById= (token,articleMenu) => {
  return axios.request({
    url: '/sys/articleMenu/updateArticleMenu',
    params: {
      token,
      articleMenu
    },
    method: 'post'
  })
}



export const deleteArticleMenuById= (token,articleMenuId) => {
  return axios.request({
    url: '/sys/articleMenu/deleteArticleMenu',
    params: {
      token,
      articleMenuId
    },
    method: 'post'
  })
}


export const getArticleMenuToArticleList= (token) => {
  return axios.request({
    url: '/sys/articleMenu/findArticleMenuToArticleList',
    params: {
      token
    },
    method: 'post'
  })
}


export const getArticleMenuToArticleListForHome= (token) => {
  return axios.request({
    url: '/sys/articleMenu/findArticleMenuToArticleListForHome',
    params: {
      token
    },
    method: 'post'
  })
}






