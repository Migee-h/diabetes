import request from '@/utils/request'

// 查询教育资源管理列表
export function listResources(query) {
  return request({
    url: '/diabetes/education/resources/list',
    method: 'get',
    params: query
  })
}

// 查询教育资源管理详细
export function getResources(resourceId) {
  return request({
    url: '/diabetes/education/resources/' + resourceId,
    method: 'get'
  })
}

// 新增教育资源管理
export function addResources(data) {
  return request({
    url: '/diabetes/education/resources',
    method: 'post',
    data: data
  })
}

// 修改教育资源管理
export function updateResources(data) {
  return request({
    url: '/diabetes/education/resources',
    method: 'put',
    data: data
  })
}

// 删除教育资源管理
export function delResources(resourceId) {
  return request({
    url: '/diabetes/education/resources/' + resourceId,
    method: 'delete'
  })
}
