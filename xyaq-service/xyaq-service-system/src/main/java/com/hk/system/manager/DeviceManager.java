package com.hk.system.manager;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hk.datasource.bean.dto.IdDTO;
import com.hk.system.bean.dto.device.info.DeviceInfoAddDTO;
import com.hk.system.bean.dto.device.location.DeviceLocationMountedDeviceDTO;
import com.hk.system.bean.pojo.DeviceInfo;
import com.hk.system.bean.pojo.DeviceNearby;
import com.hk.system.bean.vo.device.nearby.DeviceInfoNearByVO;
import com.hk.system.service.DeviceInfoService;
import com.hk.system.service.DeviceLocationService;
import com.hk.system.service.DeviceNearbyService;
import com.hk.system.service.OrgService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DeviceManager {

    @Resource
    private OrgService orgService;

    @Resource
    private DeviceInfoService deviceInfoService;

    @Resource
    private DeviceNearbyService deviceNearbyService;

    @Resource
    private DeviceLocationService deviceLocationService;

    public void mountedDevice(DeviceLocationMountedDeviceDTO dto) {

        deviceLocationService.getLocation(List.of(dto.getLocationId()));
        deviceInfoService.getDevice(dto.getDeviceIdList());
        // 更改设备区域
        LambdaUpdateWrapper<DeviceInfo> deviceInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        deviceInfoLambdaUpdateWrapper.in(DeviceInfo::getId, dto.getDeviceIdList());
        deviceInfoLambdaUpdateWrapper.set(DeviceInfo::getDeviceLocationId, dto.getLocationId());
        deviceInfoService.update(deviceInfoLambdaUpdateWrapper);
    }

    public void add(DeviceInfoAddDTO dto) {

        orgService.getOrg(dto.getOrgId());
        Condition.of(dto.getDeviceLocationId(), StringUtils::isNotBlank).handle(k -> deviceLocationService.getLocation(dto.getDeviceLocationId()));
        deviceInfoService.save(toDeviceInfo(dto));
    }

    private DeviceInfo toDeviceInfo(DeviceInfoAddDTO dto) {

        DeviceInfo deviceInfo = new DeviceInfo();
        BeanUtil.copyProperties(dto, deviceInfo);
        Condition.of(dto.getLabelList(), CollectionUtils::isNotEmpty).handle(k -> deviceInfo.setLabel(String.join(",", dto.getLabelList())));
        return deviceInfo;
    }

    public List<DeviceInfoNearByVO> list(IdDTO dto) {

        List<DeviceNearby> list = deviceNearbyService.lambdaQuery()
                .eq(DeviceNearby::getDeviceId, dto.getId())
                .or()
                .eq(DeviceNearby::getNearbyDeviceId, dto.getId())
                .list();
        Set<String> deviceIdSet = list.stream()
                .map(k -> List.of(k.getNearbyDeviceId(), k.getDeviceId()))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toSet());

        List<DeviceInfo> deviceInfoList = deviceInfoService.lambdaQuery()
                .in(DeviceInfo::getId, deviceIdSet)
                .list();

        return deviceInfoList.stream()
                .map(k -> toDeviceInfoNearByVO(k, deviceIdSet))
                .toList();
    }

    private DeviceInfoNearByVO toDeviceInfoNearByVO(DeviceInfo deviceInfo, Set<String> deviceIdSet) {

        DeviceInfoNearByVO vo = new DeviceInfoNearByVO();
        BeanUtil.copyProperties(deviceInfo, vo);
        vo.setNearby(deviceIdSet.contains(vo.getId()));
        return vo;
    }
}