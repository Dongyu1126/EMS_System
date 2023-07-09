import request from '@/utils/request'

// 查询工位绑定列表
export function listLocbind(query) {
  return request({
    url: '/equmgn/locbind/list',
    method: 'get',
    params: query
  })
}

// 查询工位绑定详细
export function getLocbind(locbindWorkshop) {
  return request({
    url: '/equmgn/locbind/' + locbindWorkshop,
    method: 'get'
  })
}

// 新增工位绑定
export function addLocbind(data) {
  return request({
    url: '/equmgn/locbind',
    method: 'post',
    data: data
  })
}

// 修改工位绑定
export function updateLocbind(data) {
  return request({
    url: '/equmgn/locbind',
    method: 'put',
    data: data
  })
}

// 删除工位绑定
export function delLocbind(locbindWorkshop) {
  return request({
    url: '/equmgn/locbind/' + locbindWorkshop,
    method: 'delete'
  })
}
