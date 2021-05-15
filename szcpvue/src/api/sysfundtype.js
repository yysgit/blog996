import axios from '@/libs/api.request'
import qs from 'qs'


export const getFundTypeList= (token,searchPream) => {
  return axios.request({
    url: '/sys/fundType/findFundTypeList',
    params: {
      token,searchPream
    },
    method: 'post'
  })
}
export const getFundTypeListByType= (token,searchPream) => {
  return axios.request({
    url: '/sys/fundType/findFundTypeListByType',
    params: {
      token,searchPream
    },
    method: 'post'
  })
}

export const addFundType= (token,fundType) => {
  return axios.request({
    url: '/sys/fundType/addFundType',
    params: {
      token,
      fundType
    },
    method: 'post'
  })
}


export const editFundTypeById= (token,fundType) => {
  return axios.request({
    url: '/sys/fundType/updateFundType',
    params: {
      token,
      fundType
    },
    method: 'post'
  })
}



export const deleteFundTypeById= (token,fundTypeId) => {
  return axios.request({
    url: '/sys/fundType/deleteFundType',
    params: {
      token,
      fundTypeId
    },
    method: 'post'
  })
}



