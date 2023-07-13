import request from '@/utils/request'

// 查询报工记录列表
export function listReprecords(query) {
  return request({
    url: '/equmgn/reprecords/list',
    method: 'get',
    params: query
  })
}

// 查询报工记录详细
export function getReprecords(reprecordsTicketNumber) {
  return request({
    url: '/equmgn/reprecords/' + reprecordsTicketNumber,
    method: 'get'
  })
}

// 新增报工记录
export function addReprecords(data) {
  return request({
    url: '/equmgn/reprecords',
    method: 'post',
    data: data
  })
}

// 修改报工记录
export function updateReprecords(data) {
  return request({
    url: '/equmgn/reprecords',
    method: 'put',
    data: data
  })
}

// 删除报工记录
export function delReprecords(reprecordsTicketNumber) {
  return request({
    url: '/equmgn/reprecords/' + reprecordsTicketNumber,
    method: 'delete'
  })
}
