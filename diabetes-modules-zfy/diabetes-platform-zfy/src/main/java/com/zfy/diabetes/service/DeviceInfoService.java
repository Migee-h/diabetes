package com.zfy.diabetes.service;

import com.zfy.diabetes.domain.DeviceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 16654
* @description 针对表【device_info(设备信息表)】的数据库操作Service
* @createDate 2025-04-16 17:22:04
*/
public interface DeviceInfoService extends IService<DeviceInfo> {
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
     * 批量删除设备信息
     *
     * @param deviceIds 需要删除的设备信息主键集合
     * @return 结果
     */
    public int deleteDeviceInfoByDeviceIds(Long[] deviceIds);

    /**
     * 删除设备信息信息
     *
     * @param deviceId 设备信息主键
     * @return 结果
     */
    public int deleteDeviceInfoByDeviceId(Long deviceId);
}
