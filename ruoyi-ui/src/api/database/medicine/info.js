import request from '@/utils/request'

// 查询药品管理，用于存储药品相关的信息列表
export function listInfo(query) {
  return request({
    url: '/diabetes/medicine/info/list',
    method: 'get',
    params: query
  })
}

// 查询药品管理，用于存储药品相关的信息详细
export function getInfo(medicineId) {
  return request({
    url: '/diabetes/medicine/info/' + medicineId,
    method: 'get'
  })
}

// 新增药品管理，用于存储药品相关的信息
export function addInfo(data) {
  return request({
    url: '/diabetes/medicine/info',
    method: 'post',
    data: data
  })
}

// 修改药品管理，用于存储药品相关的信息
export function updateInfo(data) {
  return request({
    url: '/diabetes/medicine/info',
    method: 'put',
    data: data
  })
}

// 删除药品管理，用于存储药品相关的信息
export function delInfo(medicineId) {
  return request({
    url: '/diabetes/medicine/info/' + medicineId,
    method: 'delete'
  })
}
