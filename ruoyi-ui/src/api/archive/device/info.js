import request from '@/utils/request'

// 查询设备信息列表
export function listInfo(query) {
  return request({
    url: '/diabetes/device/info/list',
    method: 'get',
    params: query
  })
}

// 查询设备信息详细
export function getInfo(deviceId) {
  return request({
    url: '/diabetes/device/info/' + deviceId,
    method: 'get'
  })
}

// 新增设备信息
export function addInfo(data) {
  return request({
    url: '/diabetes/device/info',
    method: 'post',
    data: data
  })
}

// 修改设备信息
export function updateInfo(data) {
  return request({
    url: '/diabetes/device/info',
    method: 'put',
    data: data
  })
}

// 删除设备信息
export function delInfo(deviceId) {
  return request({
    url: '/diabetes/device/info/' + deviceId,
    method: 'delete'
  })
}
