package com.hk.system.bean.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hk.framework.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型
 * </p>
 *
 * @author Matt
 */
@Schema(description = "字典类型")
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "xyaq_dict_type")
public class DictType extends BaseEntity {
    /**
     * 字典名称
     */
    @TableField(value = "dict_name")
    @Schema(description = "字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @TableField(value = "dict_type")
    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 是否启用
     */
    @TableField(value = "`enable`")
    @Schema(description = "是否启用")
    private String enable;


}