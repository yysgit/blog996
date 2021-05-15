import axios from '@/libs/api.request'
import qs from 'qs'


export const getFundInfoList= (token,searchPream) => {
  return axios.request({
    url: '/sys/fundInfo/findFundInfoList',
    params: {
      token,searchPream
    },
    method: 'post'
  })
}


export const addFundInfo= (token,fundInfo) => {
  return axios.request({
    url: '/sys/fundInfo/addFundInfo',
    params: {
      token,
      fundInfo
    },
    method: 'post'
  })
}


export const editFundInfoById= (token,fundInfo) => {
  return axios.request({
    url: '/sys/fundInfo/updateFundInfo',
    params: {
      token,
      fundInfo
    },
    method: 'post'
  })
}



export const deleteFundInfoById= (token,fundInfoId) => {
  return axios.request({
    url: '/sys/fundInfo/deleteFundInfo',
    params: {
      token,
      fundInfoId
    },
    method: 'post'
  })
}



