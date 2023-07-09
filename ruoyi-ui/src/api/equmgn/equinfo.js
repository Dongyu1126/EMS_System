import request from '@/utils/request'

// 查询设备信息列表
export function listEquinfo(query) {
  return request({
    url: '/equmgn/equinfo/list',
    method: 'get',
    params: query
  })
}

// 查询设备信息详细
export function getEquinfo(equinfoName) {
  return request({
    url: '/equmgn/equinfo/' + equinfoName,
    method: 'get'
  })
}

// 新增设备信息
export function addEquinfo(data) {
  return request({
    url: '/equmgn/equinfo',
    method: 'post',
    data: data
  })
}

// 修改设备信息
export function updateEquinfo(data) {
  return request({
    url: '/equmgn/equinfo',
    method: 'put',
    data: data
  })
}

// 删除设备信息
export function delEquinfo(equinfoName) {
  return request({
    url: '/equmgn/equinfo/' + equinfoName,
    method: 'delete'
  })
}
