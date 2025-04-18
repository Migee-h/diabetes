package com.zfy.diabetes.mapper;

import com.zfy.diabetes.domain.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16654
* @description 针对表【patient(患者基本信息表)】的数据库操作Mapper
* @createDate 2025-04-16 17:40:51
* @Entity com.zfy.diabetes.domain.Patient
*/
public interface PatientMapper extends BaseMapper<Patient> {

    /**
     * 查询患者基本信息
     *
     * @param patientId 患者基本信息主键
     * @return 患者基本信息
     */
    public Patient selectPatientByPatientId(Long patientId);

    /**
     * 查询患者基本信息列表
     *
     * @param patient 患者基本信息
     * @return 患者基本信息集合
     */
    public List<Patient> selectPatientList(Patient patient);

    /**
     * 新增患者基本信息
     *
     * @param patient 患者基本信息
     * @return 结果
     */
    public int insertPatient(Patient patient);

    /**
     * 修改患者基本信息
     *
     * @param patient 患者基本信息
     * @return 结果
     */
    public int updatePatient(Patient patient);

    /**
     * 删除患者基本信息
     *
     * @param patientId 患者基本信息主键
     * @return 结果
     */
    public int deletePatientByPatientId(Long patientId);

    /**
     * 批量删除患者基本信息
     *
     * @param patientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePatientByPatientIds(Long[] patientIds);

}




