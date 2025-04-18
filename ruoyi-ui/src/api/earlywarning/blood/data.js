import request from '@/utils/request'

// 查询血糖数据列表
export function listData(query) {
  return request({
    url: '/diabetes/blood/data/list',
    method: 'get',
    params: query
  })
}

// 查询血糖数据详细
export function getData(dataId) {
  return request({
    url: '/diabetes/blood/data/' + dataId,
    method: 'get'
  })
}

// 新增血糖数据
export function addData(data) {
  return request({
    url: '/diabetes/blood/data',
    method: 'post',
    data: data
  })
}

// 修改血糖数据
export function updateData(data) {
  return request({
    url: '/diabetes/blood/data',
    method: 'put',
    data: data
  })
}

// 删除血糖数据
export function delData(dataId) {
  return request({
    url: '/diabetes/blood/data/' + dataId,
    method: 'delete'
  })
}

// 统计数据，风险预测
export function sugarStats(data) {
  return request({
    url: '/diabetes/blood/data/stats',
    method: 'post',
    data: data
  })
}