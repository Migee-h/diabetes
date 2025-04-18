import request from '@/utils/request'

// 查询医生管理信息列表
export function listManagement(query) {
  return request({
    url: '/diabetes/doctor/management/list',
    method: 'get',
    params: query
  })
}

// 查询医生管理信息详细
export function getManagement(doctorId) {
  return request({
    url: 'diabetes/doctor/management/' + doctorId,
    method: 'get'
  })
}

// 新增医生管理信息
export function addManagement(data) {
  return request({
    url: 'diabetes/doctor/management',
    method: 'post',
    data: data
  })
}

// 修改医生管理信息
export function updateManagement(data) {
  return request({
    url: 'diabetes/doctor/management',
    method: 'put',
    data: data
  })
}

// 删除医生管理信息
export function delManagement(doctorId) {
  return request({
    url: 'diabetes/doctor/management/' + doctorId,
    method: 'delete'
  })
}
