import request from '@/utils/request'

// 查询物料分类列表
export function listItemcaf(query) {
  return request({
    url: '/equmgn/itemcaf/list',
    method: 'get',
    params: query
  })
}

// 查询物料分类详细
export function getItemcaf(itemcafName) {
  return request({
    url: '/equmgn/itemcaf/' + itemcafName,
    method: 'get'
  })
}

// 新增物料分类
export function addItemcaf(data) {
  return request({
    url: '/equmgn/itemcaf',
    method: 'post',
    data: data
  })
}

// 修改物料分类
export function updateItemcaf(data) {
  return request({
    url: '/equmgn/itemcaf',
    method: 'put',
    data: data
  })
}

// 删除物料分类
export function delItemcaf(itemcafName) {
  return request({
    url: '/equmgn/itemcaf/' + itemcafName,
    method: 'delete'
  })
}
