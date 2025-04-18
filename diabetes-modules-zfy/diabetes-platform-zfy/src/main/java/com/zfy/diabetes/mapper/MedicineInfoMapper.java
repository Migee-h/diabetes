package com.zfy.diabetes.mapper;

import com.zfy.diabetes.domain.MedicineInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16654
* @description 针对表【medicine_info(药品管理表，用于存储药品相关的信息)】的数据库操作Mapper
* @createDate 2025-04-16 16:41:53
* @Entity com.zfy.diabetes.domain.MedicineInfo
*/
public interface MedicineInfoMapper extends BaseMapper<MedicineInfo> {

    /**
     * 查询药品管理，用于存储药品相关的信息
     *
     * @param medicineId 药品管理，用于存储药品相关的信息主键
     * @return 药品管理，用于存储药品相关的信息
     */
    public MedicineInfo selectMedicineInfoByMedicineId(Long medicineId);

    /**
     * 查询药品管理，用于存储药品相关的信息列表
     *
     * @param medicineInfo 药品管理，用于存储药品相关的信息
     * @return 药品管理，用于存储药品相关的信息集合
     */
    public List<MedicineInfo> selectMedicineInfoList(MedicineInfo medicineInfo);

    /**
     * 新增药品管理，用于存储药品相关的信息
     *
     * @param medicineInfo 药品管理，用于存储药品相关的信息
     * @return 结果
     */
    public int insertMedicineInfo(MedicineInfo medicineInfo);

    /**
     * 修改药品管理，用于存储药品相关的信息
     *
     * @param medicineInfo 药品管理，用于存储药品相关的信息
     * @return 结果
     */
    public int updateMedicineInfo(MedicineInfo medicineInfo);

    /**
     * 删除药品管理，用于存储药品相关的信息
     *
     * @param medicineId 药品管理，用于存储药品相关的信息主键
     * @return 结果
     */
    public int deleteMedicineInfoByMedicineId(Long medicineId);

    /**
     * 批量删除药品管理，用于存储药品相关的信息
     *
     * @param medicineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMedicineInfoByMedicineIds(Long[] medicineIds);

}




