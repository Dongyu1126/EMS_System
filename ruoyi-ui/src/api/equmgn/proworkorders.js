import request from '@/utils/request'

// 查询生产工单列表
export function listProworkorders(query) {
  return request({
    url: '/equmgn/proworkorders/list',
    method: 'get',
    params: query
  })
}

// 查询生产工单详细
export function getProworkorders(proworkordersProductStatus) {
  return request({
    url: '/equmgn/proworkorders/' + proworkordersProductStatus,
    method: 'get'
  })
}

// 新增生产工单
export function addProworkorders(data) {
  return request({
    url: '/equmgn/proworkorders',
    method: 'post',
    data: data
  })
}

// 修改生产工单
export function updateProworkorders(data) {
  return request({
    url: '/equmgn/proworkorders',
    method: 'put',
    data: data
  })
}

// 删除生产工单
export function delProworkorders(proworkordersProductStatus) {
  return request({
    url: '/equmgn/proworkorders/' + proworkordersProductStatus,
    method: 'delete'
  })
}
