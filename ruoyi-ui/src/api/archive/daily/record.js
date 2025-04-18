import request from '@/utils/request'

// 查询日常生活记录列表
export function listRecord(query) {
  return request({
    url: '/diabetes/daily/record/list',
    method: 'get',
    params: query
  })
}

// 查询日常生活记录详细
export function getRecord(recordId) {
  return request({
    url: '/diabetes/daily/record/' + recordId,
    method: 'get'
  })
}

// 新增日常生活记录
export function addRecord(data) {
  return request({
    url: '/diabetes/daily/record',
    method: 'post',
    data: data
  })  
}

// 修改日常生活记录
export function updateRecord(data) {
  return request({
    url: '/diabetes/daily/record',
    method: 'put',
    data: data
  })
}

// 删除日常生活记录
export function delRecord(recordId) {
  return request({
    url: '/diabetes/daily/record/' + recordId,
    method: 'delete'
  })
}
