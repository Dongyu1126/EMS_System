import request from '@/utils/request'

// 查询工位信息列表
export function listLocinfo(query) {
  return request({
    url: '/equmgn/locinfo/list',
    method: 'get',
    params: query
  })
}

// 查询工位信息详细
export function getLocinfo(locinfoAffiliatedWorkshop) {
  return request({
    url: '/equmgn/locinfo/' + locinfoAffiliatedWorkshop,
    method: 'get'
  })
}

// 新增工位信息
export function addLocinfo(data) {
  return request({
    url: '/equmgn/locinfo',
    method: 'post',
    data: data
  })
}

// 修改工位信息
export function updateLocinfo(data) {
  return request({
    url: '/equmgn/locinfo',
    method: 'put',
    data: data
  })
}

// 删除工位信息
export function delLocinfo(locinfoAffiliatedWorkshop) {
  return request({
    url: '/equmgn/locinfo/' + locinfoAffiliatedWorkshop,
    method: 'delete'
  })
}
