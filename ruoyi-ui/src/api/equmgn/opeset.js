import request from '@/utils/request'

// 查询工序设置列表
export function listOpeset(query) {
  return request({
    url: '/equmgn/opeset/list',
    method: 'get',
    params: query
  })
}

// 查询工序设置详细
export function getOpeset(opesetName) {
  return request({
    url: '/equmgn/opeset/' + opesetName,
    method: 'get'
  })
}

// 新增工序设置
export function addOpeset(data) {
  return request({
    url: '/equmgn/opeset',
    method: 'post',
    data: data
  })
}

// 修改工序设置
export function updateOpeset(data) {
  return request({
    url: '/equmgn/opeset',
    method: 'put',
    data: data
  })
}

// 删除工序设置
export function delOpeset(opesetName) {
  return request({
    url: '/equmgn/opeset/' + opesetName,
    method: 'delete'
  })
}
