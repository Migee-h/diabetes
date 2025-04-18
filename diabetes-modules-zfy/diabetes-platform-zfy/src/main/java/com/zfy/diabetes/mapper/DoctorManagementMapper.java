package com.zfy.diabetes.mapper;

import com.zfy.diabetes.domain.DoctorManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16654
* @description 针对表【doctor_management(医生管理信息表)】的数据库操作Mapper
* @createDate 2025-04-16 10:41:04
* @Entity com.zfy.diabetes.domain.DoctorManagement
*/
public interface DoctorManagementMapper extends BaseMapper<DoctorManagement> {
    /**
     * 查询医生管理信息
     *
     * @param doctorId 医生管理信息主键
     * @return 医生管理信息
     */
    public DoctorManagement selectDoctorManagementByDoctorId(Long doctorId);

    /**
     * 查询医生管理信息列表
     *
     * @param doctorManagement 医生管理信息
     * @return 医生管理信息集合
     */
    public List<DoctorManagement> selectDoctorManagementList(DoctorManagement doctorManagement);

    /**
     * 新增医生管理信息
     *
     * @param doctorManagement 医生管理信息
     * @return 结果
     */
    public int insertDoctorManagement(DoctorManagement doctorManagement);

    /**
     * 修改医生管理信息
     *
     * @param doctorManagement 医生管理信息
     * @return 结果
     */
    public int updateDoctorManagement(DoctorManagement doctorManagement);

    /**
     * 删除医生管理信息
     *
     * @param doctorId 医生管理信息主键
     * @return 结果
     */
    public int deleteDoctorManagementByDoctorId(Long doctorId);

    /**
     * 批量删除医生管理信息
     *
     * @param doctorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDoctorManagementByDoctorIds(Long[] doctorIds);
}




