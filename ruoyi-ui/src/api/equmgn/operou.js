import request from '@/utils/request'

// 查询工序路线列表
export function listOperou(query) {
  return request({
    url: '/equmgn/operou/list',
    method: 'get',
    params: query
  })
}

// 查询工序路线详细
export function getOperou(operouName) {
  return request({
    url: '/equmgn/operou/' + operouName,
    method: 'get'
  })
}

// 新增工序路线
export function addOperou(data) {
  return request({
    url: '/equmgn/operou',
    method: 'post',
    data: data
  })
}

// 修改工序路线
export function updateOperou(data) {
  return request({
    url: '/equmgn/operou',
    method: 'put',
    data: data
  })
}

// 删除工序路线
export function delOperou(operouName) {
  return request({
    url: '/equmgn/operou/' + operouName,
    method: 'delete'
  })
}
