import request from '@/utils/request'

// 查询患者基本信息列表
export function listPatient(query) {
  return request({
    url: '/diabetes/patient/list',
    method: 'get',
    params: query
  })
}

// 查询患者基本信息详细
export function getPatient(patientId) {
  return request({
    url: '/diabetes/patient/' + patientId,
    method: 'get'
  })
}

// 新增患者基本信息
export function addPatient(data) {
  return request({
    url: '/diabetes/patient',
    method: 'post',
    data: data
  })
}

// 修改患者基本信息
export function updatePatient(data) {
  return request({
    url: '/diabetes/patient',
    method: 'put',
    data: data
  })
}

// 修改患者基本信息
export function aiHealth(data) {
  return request({
    url: '/diabetes/patient/aiHealth',
    method: 'post',
    data: data
  })
}

// 删除患者基本信息
export function delPatient(patientId) {
  return request({
    url: '/diabetes/patient/' + patientId,
    method: 'delete'
  })
}
