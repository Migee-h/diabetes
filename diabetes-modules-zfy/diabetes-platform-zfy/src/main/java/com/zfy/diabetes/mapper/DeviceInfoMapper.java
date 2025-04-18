package com.zfy.diabetes.mapper;

import com.zfy.diabetes.domain.DeviceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16654
* @description 针对表【device_info(设备信息表)】的数据库操作Mapper
* @createDate 2025-04-16 17:22:04
* @Entity com.zfy.diabetes.domain.DeviceInfo
*/
public interface DeviceInfoMapper extends BaseMapper<DeviceInfo> {

    /**
     * 查询设备信息
     *
     * @param deviceId 设备信息主键
     * @return 设备信息
     */
    public DeviceInfo selectDeviceInfoByDeviceId(Long deviceId);

    /**
     * 查询设备信息列表
     *
     * @param deviceInfo 设备信息
     * @return 设备信息集合
     */
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo);

    /**
     * 新增设备信息
     *
     * @param deviceInfo 设备信息
     * @return 结果
     */
    public int insertDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 修改设备信息
     *
     * @param deviceInfo 设备信息
     * @return 结果
     */
    public int updateDeviceInfo(DeviceInfo deviceInfo);

    /**
     * 删除设备信息
     *
     * @param deviceId 设备信息主键
     * @return 结果
     */
    public int deleteDeviceInfoByDeviceId(Long deviceId);

    /**
     * 批量删除设备信息
     *
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeviceInfoByDeviceIds(Long[] deviceIds);

}




