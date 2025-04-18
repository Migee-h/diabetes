package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.zfy.diabetes.domain.DeviceInfo;
import com.zfy.diabetes.service.DeviceInfoService;
import com.zfy.diabetes.mapper.DeviceInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 16654
* @description 针对表【device_info(设备信息表)】的数据库操作Service实现
* @createDate 2025-04-16 17:22:04
*/
@Service
public class DeviceInfoServiceImpl extends ServiceImpl<DeviceInfoMapper, DeviceInfo>
    implements DeviceInfoService{

    @Autowired
    private DeviceInfoMapper deviceInfoMapper;

    /**
     * 查询设备信息
     *
     * @param deviceId 设备信息主键
     * @return 设备信息
     */
    @Override
    public DeviceInfo selectDeviceInfoByDeviceId(Long deviceId)
    {
        return deviceInfoMapper.selectDeviceInfoByDeviceId(deviceId);
    }

    /**
     * 查询设备信息列表
     *
     * @param deviceInfo 设备信息
     * @return 设备信息
     */
    @Override
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo)
    {
        return deviceInfoMapper.selectDeviceInfoList(deviceInfo);
    }

    /**
     * 新增设备信息
     *
     * @param deviceInfo 设备信息
     * @return 结果
     */
    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfo.setCreateTime(DateUtils.getNowDate());
        return deviceInfoMapper.insertDeviceInfo(deviceInfo);
    }

    /**
     * 修改设备信息
     *
     * @param deviceInfo 设备信息
     * @return 结果
     */
    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo)
    {
        deviceInfo.setUpdateTime(DateUtils.getNowDate());
        return deviceInfoMapper.updateDeviceInfo(deviceInfo);
    }

    /**
     * 批量删除设备信息
     *
     * @param deviceIds 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoByDeviceIds(Long[] deviceIds)
    {
        return deviceInfoMapper.deleteDeviceInfoByDeviceIds(deviceIds);
    }

    /**
     * 删除设备信息信息
     *
     * @param deviceId 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoByDeviceId(Long deviceId)
    {
        return deviceInfoMapper.deleteDeviceInfoByDeviceId(deviceId);
    }

}




