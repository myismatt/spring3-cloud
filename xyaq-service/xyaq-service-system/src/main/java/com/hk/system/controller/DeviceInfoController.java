package com.hk.system.controller;

import com.hk.system.bean.vo.device.info.DeviceChangeLocationVO;
import com.hk.system.service.DeviceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>设备信息</p>
 *
 * @author 小徐
 * @since 2023/9/13 11:39
 */
@Tag(name = "设备信息")
@Slf4j
@RestController
@RequestMapping("/device-info")
public class DeviceInfoController {

    @Resource
    private DeviceInfoService deviceInfoService;

    @PostMapping("/change-location")
    @Operation(summary = "更改设备区域", description = "更改设备区域")
    public void changeLocation(@RequestBody @Valid DeviceChangeLocationVO vo) {
        deviceInfoService.changeLocation(vo);
    }
}