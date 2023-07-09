import request from '@/utils/request'

// 查询车间信息列表
export function listWsinfo(query) {
  return request({
    url: '/equmgn/wsinfo/list',
    method: 'get',
    params: query
  })
}

// 查询车间信息详细
export function getWsinfo(wsinfoName) {
  return request({
    url: '/equmgn/wsinfo/' + wsinfoName,
    method: 'get'
  })
}

// 新增车间信息
export function addWsinfo(data) {
  return request({
    url: '/equmgn/wsinfo',
    method: 'post',
    data: data
  })
}

// 修改车间信息
export function updateWsinfo(data) {
  return request({
    url: '/equmgn/wsinfo',
    method: 'put',
    data: data
  })
}

// 删除车间信息
export function delWsinfo(wsinfoName) {
  return request({
    url: '/equmgn/wsinfo/' + wsinfoName,
    method: 'delete'
  })
}
