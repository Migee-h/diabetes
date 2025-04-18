import request from '@/utils/request'

// 查询健康评估与干预记录列表
export function listAssessment(query) {
  return request({
    url: '/diabetes/health/assessment/list',
    method: 'get',
    params: query
  })
}

// 查询健康评估与干预记录详细
export function getAssessment(assessmentId) {
  return request({
    url: '/diabetes/health/assessment/' + assessmentId,
    method: 'get'
  })
}

// 新增健康评估与干预记录
export function addAssessment(data) {
  return request({
    url: '/diabetes/health/assessment',
    method: 'post',
    data: data
  })
}

// 修改健康评估与干预记录
export function updateAssessment(data) {
  return request({
    url: '/diabetes/health/assessment',
    method: 'put',
    data: data
  })
}

// 删除健康评估与干预记录
export function delAssessment(assessmentId) {
  return request({
    url: '/diabetes/health/assessment/' + assessmentId,
    method: 'delete'
  })
}
