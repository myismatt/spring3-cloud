package com.hk.system.bean.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hk.framework.bean.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备地点信息表
 * </p>
 *
 * @author Matt
 */
@Schema(description = "设备地点信息表")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "xyaq_device_location")
public class DeviceLocation extends BaseEntity {

    @TableField(value = "org_id")
    @Schema(description = "所属组织id")
    private String orgId;

    @TableField(value = "parent_id")
    @Schema(description = "上级名称")
    private String parentId;

    @TableField(value = "`name`")
    @Schema(description = "地点名称")
    private String name;

    @TableField(value = "`short_name`")
    @Schema(description = "地点名称简称")
    private String shortName;

    @TableField(value = "`label`")
    @Schema(description = "标签")
    private String label;

    @TableField(value = "longitude")
    @Schema(description = "经度")
    private String longitude;

    @TableField(value = "latitude")
    @Schema(description = "纬度")
    private String latitude;

    @TableField(value = "remark")
    @Schema(description = "备注")
    private String remark;

    @TableField(value = "sort")
    @Schema(description = "排序")
    private Integer sort;
}