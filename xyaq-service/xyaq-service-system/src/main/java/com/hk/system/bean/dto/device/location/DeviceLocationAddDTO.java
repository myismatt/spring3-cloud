package com.hk.system.bean.dto.device.location;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域-新增-请求参数")
public class DeviceLocationAddDTO {

    @NotBlank(message = "组织id" + "不能为空")
    @Schema(title = "组织id")
    private String orgId;

    @Schema(title = "上级区域id，留空则视组织为上级")
    private String parentId;

    @NotBlank(message = "区域名称" + "不能为空")
    @Schema(title = "区域名称")
    private String name;

    @Schema(description = "地点名称简称")
    private String shortName;

    @Schema(description = "标签")
    private List<String> labelList;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @NotBlank(message = "是否启用" + "不能为空")
    @Schema(description = "是否启用")
    private String del;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    private Integer sort;
}