import axios from '@/libs/api.request'
import qs from 'qs'




export const getArticleListByName= (token,searchPream) => {
  return axios.request({
    url: '/sys/article/findArticleListByName',
    params: {
      token,searchPream
    },
    method: 'post'
  })
}

export const getArticleList= (token,searchPream) => {
  return axios.request({
    url: '/sys/article/findArticleList',
    params: {
      token,searchPream
    },
    method: 'post'
  })
}


export const addArticle= (token,article) => {
  return axios.request({
    url: '/sys/article/addArticle',
    params: {
      token,
      article
    },
    method: 'post'
  })
}


export const editArticleById= (token,article) => {
  return axios.request({
    url: '/sys/article/updateArticle',
    params: {
      token,
      article
    },
    method: 'post'
  })
}




export const deleteArticleById= (token,articleId) => {
  return axios.request({
    url: '/sys/article/deleteArticle',
    params: {
      token,
      articleId
    },
    method: 'post'
  })
}

export const getMarkdownContent= (token,url) => {
  return axios.request({
    url: '/sys/article/markdown',
    params: {
      token,
      url
    },
    method: 'post'
  })
}


export const editArticleContent= (token,articleContent) => {
  return axios.request({
    url: '/sys/article/updateArticleContent',
    params: {
      token,
      articleContent
    },
    method: 'post'
  })
}




