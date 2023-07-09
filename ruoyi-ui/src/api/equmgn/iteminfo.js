import request from '@/utils/request'

// 查询物料信息列表
export function listIteminfo(query) {
  return request({
    url: '/equmgn/iteminfo/list',
    method: 'get',
    params: query
  })
}

// 查询物料信息详细
export function getIteminfo(iteminfoNumber) {
  return request({
    url: '/equmgn/iteminfo/' + iteminfoNumber,
    method: 'get'
  })
}

// 新增物料信息
export function addIteminfo(data) {
  return request({
    url: '/equmgn/iteminfo',
    method: 'post',
    data: data
  })
}

// 修改物料信息
export function updateIteminfo(data) {
  return request({
    url: '/equmgn/iteminfo',
    method: 'put',
    data: data
  })
}

// 删除物料信息
export function delIteminfo(iteminfoNumber) {
  return request({
    url: '/equmgn/iteminfo/' + iteminfoNumber,
    method: 'delete'
  })
}
