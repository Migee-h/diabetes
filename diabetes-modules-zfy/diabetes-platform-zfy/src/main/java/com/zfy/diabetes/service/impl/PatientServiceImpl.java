package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.zfy.diabetes.domain.DeviceInfo;
import com.zfy.diabetes.domain.Patient;
import com.zfy.diabetes.service.DeviceInfoService;
import com.zfy.diabetes.service.PatientService;
import com.zfy.diabetes.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

/**
* @author 16654
* @description 针对表【patient(患者基本信息表)】的数据库操作Service实现
* @createDate 2025-04-16 17:40:51
*/
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient>
    implements PatientService{

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DeviceInfoService deviceInfoService;

    /**
     * 查询患者基本信息
     *
     * @param patientId 患者基本信息主键
     * @return 患者基本信息
     */
    @Override
    public Patient selectPatientByPatientId(Long patientId)
    {
        return patientMapper.selectPatientByPatientId(patientId);
    }

    /**
     * 查询患者基本信息列表
     *
     * @param patient 患者基本信息
     * @return 患者基本信息
     */
    @Override
    public List<Patient> selectPatientList(Patient patient)
    {
        return patientMapper.selectPatientList(patient);
    }

    /**
     * 新增患者基本信息
     *
     * @param patient 患者基本信息
     * @return 结果
     */
    @Override
    public int insertPatient(Patient patient)
    {
        //校验患者信息
        validatePatient(patient);

        //查看设备是否存在
        if (patient.getDeviceId()!=null){
            DeviceInfo deviceInfo = deviceInfoService.selectDeviceInfoByDeviceId(patient.getDeviceId());
            if (deviceInfo==null){
                throw new RuntimeException("设备不存在");
            }
        }

        patient.setCreateTime(DateUtils.getNowDate());
        return patientMapper.insertPatient(patient);
    }

    private void validatePatient(Patient patient) {
        // 校验患者手机号是否合法
        String phoneRegex = "^1[3-9]\\d{9}$";
        if (!Pattern.matches(phoneRegex, patient.getPatientPhone())) {
            throw new RuntimeException("患者手机号不合法");
        }

        // 校验患者身份证号是否合法
        String idCardRegex = "^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[0-9Xx]$";
        if (!Pattern.matches(idCardRegex, patient.getPatientNum())) {
            throw new RuntimeException("患者身份证号不合法");
        }

        // 校验患者邮箱是否合法
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, patient.getPatientEmail())) {
            throw new RuntimeException("患者邮箱不合法");
        }
    }
    /**
     * 修改患者基本信息
     *
     * @param patient 患者基本信息
     * @return 结果
     */
    @Override
    public int updatePatient(Patient patient)
    {
        patient.setUpdateTime(DateUtils.getNowDate());
        return patientMapper.updatePatient(patient);
    }

    /**
     * 批量删除患者基本信息
     *
     * @param patientIds 需要删除的患者基本信息主键
     * @return 结果
     */
    @Override
    public int deletePatientByPatientIds(Long[] patientIds)
    {
        return patientMapper.deletePatientByPatientIds(patientIds);
    }

    /**
     * 删除患者基本信息信息
     *
     * @param patientId 患者基本信息主键
     * @return 结果
     */
    @Override
    public int deletePatientByPatientId(Long patientId)
    {
        return patientMapper.deletePatientByPatientId(patientId);
    }

}




