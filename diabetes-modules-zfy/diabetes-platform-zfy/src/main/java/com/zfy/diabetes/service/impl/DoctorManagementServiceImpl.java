package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.zfy.diabetes.domain.DoctorManagement;
import com.zfy.diabetes.service.DoctorManagementService;
import com.zfy.diabetes.mapper.DoctorManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 16654
* @description 针对表【doctor_management(医生管理信息表)】的数据库操作Service实现
* @createDate 2025-04-16 10:41:04
*/
@Service
public class DoctorManagementServiceImpl extends ServiceImpl<DoctorManagementMapper, DoctorManagement>
    implements DoctorManagementService{
    @Autowired
    private DoctorManagementMapper doctorManagementMapper;

    /**
     * 查询医生管理信息
     *
     * @param doctorId 医生管理信息主键
     * @return 医生管理信息
     */
    @Override
    public DoctorManagement selectDoctorManagementByDoctorId(Long doctorId)
    {
        return doctorManagementMapper.selectDoctorManagementByDoctorId(doctorId);
    }

    /**
     * 查询医生管理信息列表
     *
     * @param doctorManagement 医生管理信息
     * @return 医生管理信息
     */
    @Override
    public List<DoctorManagement> selectDoctorManagementList(DoctorManagement doctorManagement)
    {
        return doctorManagementMapper.selectDoctorManagementList(doctorManagement);
    }

    /**
     * 新增医生管理信息
     *
     * @param doctorManagement 医生管理信息
     * @return 结果
     */
    @Override
    public int insertDoctorManagement(DoctorManagement doctorManagement)
    {
        doctorManagement.setCreateTime(DateUtils.getNowDate());
        return doctorManagementMapper.insertDoctorManagement(doctorManagement);
    }

    /**
     * 修改医生管理信息
     *
     * @param doctorManagement 医生管理信息
     * @return 结果
     */
    @Override
    public int updateDoctorManagement(DoctorManagement doctorManagement)
    {
        doctorManagement.setUpdateTime(DateUtils.getNowDate());
        return doctorManagementMapper.updateDoctorManagement(doctorManagement);
    }

    /**
     * 批量删除医生管理信息
     *
     * @param doctorIds 需要删除的医生管理信息主键
     * @return 结果
     */
    @Override
    public int deleteDoctorManagementByDoctorIds(Long[] doctorIds)
    {
        return doctorManagementMapper.deleteDoctorManagementByDoctorIds(doctorIds);
    }

    /**
     * 删除医生管理信息信息
     *
     * @param doctorId 医生管理信息主键
     * @return 结果
     */
    @Override
    public int deleteDoctorManagementByDoctorId(Long doctorId)
    {
        return doctorManagementMapper.deleteDoctorManagementByDoctorId(doctorId);
    }
}




