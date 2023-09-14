package com.hk.system.bean.dto.device.location;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "区域-新增-请求参数")
public class DeviceLocationAddDTO {

    @Pattern(regexp = "\\d.*", message = "id只能为数字")
    @NotBlank(message = "组织id" + "不能为空")
    @Schema(title = "组织id")
    private String orgId;

    @Pattern(regexp = "\\d.*", message = "id只能为数字")
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

    @NotNull(message = "请选择" + "是否启用" + "0：启用；1：不启用")
    @Pattern(regexp = "[01]", message = "0：启用；1：不启用")
    @Schema(description = "是否启用")
    private String del;

    @Size(max = 300, message = "备注只能在 0-300 之间")
    @Schema(description = "备注")
    private String remark;

    @Pattern(regexp = "\\d.*", message = "排序只能为数字")
    @Schema(description = "排序")
    private String sort;
}