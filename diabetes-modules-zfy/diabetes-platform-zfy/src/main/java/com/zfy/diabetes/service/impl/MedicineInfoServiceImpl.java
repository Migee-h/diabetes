package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfy.diabetes.domain.MedicineInfo;
import com.zfy.diabetes.service.MedicineInfoService;
import com.zfy.diabetes.mapper.MedicineInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 16654
* @description 针对表【medicine_info(药品管理表，用于存储药品相关的信息)】的数据库操作Service实现
* @createDate 2025-04-16 16:41:53
*/
@Service
public class MedicineInfoServiceImpl extends ServiceImpl<MedicineInfoMapper, MedicineInfo>
    implements MedicineInfoService{

    @Autowired
    private MedicineInfoMapper medicineInfoMapper;

    /**
     * 查询药品管理，用于存储药品相关的信息
     *
     * @param medicineId 药品管理，用于存储药品相关的信息主键
     * @return 药品管理，用于存储药品相关的信息
     */
    @Override
    public MedicineInfo selectMedicineInfoByMedicineId(Long medicineId)
    {
        return medicineInfoMapper.selectMedicineInfoByMedicineId(medicineId);
    }

    /**
     * 查询药品管理，用于存储药品相关的信息列表
     *
     * @param medicineInfo 药品管理，用于存储药品相关的信息
     * @return 药品管理，用于存储药品相关的信息
     */
    @Override
    public List<MedicineInfo> selectMedicineInfoList(MedicineInfo medicineInfo)
    {
        return medicineInfoMapper.selectMedicineInfoList(medicineInfo);
    }

    /**
     * 新增药品管理，用于存储药品相关的信息
     *
     * @param medicineInfo 药品管理，用于存储药品相关的信息
     * @return 结果
     */
    @Override
    public int insertMedicineInfo(MedicineInfo medicineInfo)
    {
        return medicineInfoMapper.insertMedicineInfo(medicineInfo);
    }

    /**
     * 修改药品管理，用于存储药品相关的信息
     *
     * @param medicineInfo 药品管理，用于存储药品相关的信息
     * @return 结果
     */
    @Override
    public int updateMedicineInfo(MedicineInfo medicineInfo)
    {
        return medicineInfoMapper.updateMedicineInfo(medicineInfo);
    }

    /**
     * 批量删除药品管理，用于存储药品相关的信息
     *
     * @param medicineIds 需要删除的药品管理，用于存储药品相关的信息主键
     * @return 结果
     */
    @Override
    public int deleteMedicineInfoByMedicineIds(Long[] medicineIds)
    {
        return medicineInfoMapper.deleteMedicineInfoByMedicineIds(medicineIds);
    }

    /**
     * 删除药品管理，用于存储药品相关的信息信息
     *
     * @param medicineId 药品管理，用于存储药品相关的信息主键
     * @return 结果
     */
    @Override
    public int deleteMedicineInfoByMedicineId(Long medicineId)
    {
        return medicineInfoMapper.deleteMedicineInfoByMedicineId(medicineId);
    }

}




