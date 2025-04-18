import request from '@/utils/request'

// 查询膳食数据管理，用于存储膳食相关的信息列表
export function listManagement(query) {
  return request({
    url: '/diabetes/food/management/list',
    method: 'get',
    params: query
  })
}

// 查询膳食数据管理，用于存储膳食相关的信息详细
export function getManagement(foodId) {
  return request({
    url: '/diabetes/food/management/' + foodId,
    method: 'get'
  })
}

// 新增膳食数据管理，用于存储膳食相关的信息
export function addManagement(data) {
  return request({
    url: '/diabetes/food/management',
    method: 'post',
    data: data
  })
}

// 修改膳食数据管理，用于存储膳食相关的信息
export function updateManagement(data) {
  return request({
    url: '/diabetes/food/management',
    method: 'put',
    data: data
  })
}

// 删除膳食数据管理，用于存储膳食相关的信息
export function delManagement(foodId) {
  return request({
    url: '/diabetes/food/management/' + foodId,
    method: 'delete'
  })
}
