package com.hk.system.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.framework.exception.CustomizeException;
import com.hk.system.bean.pojo.DeviceLocation;
import com.hk.system.bean.vo.IdVO;
import com.hk.system.bean.vo.device.location.DeviceLocationTreeSearchVO;
import com.hk.system.bean.vo.device.location.DeviceLocationTreeVO;
import com.hk.system.bean.vo.device.location.DeviceLocationVO;
import com.hk.system.dao.DeviceLocationMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Service
public class DeviceLocationService extends ServiceImpl<DeviceLocationMapper, DeviceLocation> {

    public List<DeviceLocationTreeVO> tree(DeviceLocationTreeSearchVO vo) {

        LambdaQueryWrapper<DeviceLocation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(vo.getOrgId()), DeviceLocation::getOrgId, vo.getOrgId());
        List<DeviceLocation> list = list(lambdaQueryWrapper);
        return buildTree(list);
    }

    private List<DeviceLocationTreeVO> buildTree(List<DeviceLocation> list) {

        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        // 分离数据
        List<DeviceLocationTreeVO> rootList = list.stream()
                .filter(deviceLocation -> deviceLocation.getOrgId().equals(deviceLocation.getParentId()))
                .map(this::toDeviceLocationTreeVO)
                .toList();
        Map<String, List<DeviceLocationTreeVO>> leafMap = list.stream()
                .map(this::toDeviceLocationTreeVO)
                .collect(Collectors.groupingBy(DeviceLocationTreeVO::getParentId));

        // 组装数据
        buildBranch(rootList, leafMap);
        return rootList;
    }

    private void buildBranch(List<DeviceLocationTreeVO> rootList, Map<String, List<DeviceLocationTreeVO>> leafMap) {

        if (CollectionUtils.isEmpty(rootList)) {
            return;
        }
        for (DeviceLocationTreeVO deviceLocationTree : rootList) {
            List<DeviceLocationTreeVO> deviceLocations = leafMap.get(deviceLocationTree.getId());
            leafMap.remove(deviceLocationTree.getId());
            deviceLocationTree.setChildren(deviceLocations);
            buildBranch(deviceLocations, leafMap);
        }
    }

    private DeviceLocationTreeVO toDeviceLocationTreeVO(DeviceLocation vo) {
        DeviceLocationTreeVO deviceLocationTreeVO = new DeviceLocationTreeVO();
        BeanUtil.copyProperties(vo, deviceLocationTreeVO);
        deviceLocationTreeVO.setChildren(new ArrayList<>());
        return deviceLocationTreeVO;
    }

    public DeviceLocationVO info(IdVO vo) {

        DeviceLocation deviceLocation = this.baseMapper.selectById(vo.getId());
        if (Objects.isNull(deviceLocation)) {
            throw new CustomizeException("数据不存在");
        }
        return toDeviceLocationVO(deviceLocation);
    }

    private DeviceLocationVO toDeviceLocationVO(DeviceLocation vo) {
        DeviceLocationVO deviceLocationVO = new DeviceLocationVO();
        BeanUtil.copyProperties(vo, deviceLocationVO);
        return deviceLocationVO;
    }

    public void del(IdVO vo) {

        // 有下级区域不允许删除
        LambdaQueryWrapper<DeviceLocation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceLocation::getParentId, vo.getId());
        List<DeviceLocation> list = list(lambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            throw new CustomizeException("存在下级区域，不允许删除");
        }

        // TODO 有挂载设备不允许删除
        // TODO 删除区域
    }
}