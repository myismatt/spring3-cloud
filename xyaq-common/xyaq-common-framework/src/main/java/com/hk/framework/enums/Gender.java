package com.hk.framework.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举
 * </p>
 *
 * @author Matt
 */
@Getter
@AllArgsConstructor
public enum Gender {

    /**
     * 性别
     */
    WOMAN(0, "女"),
    MAN(1, "男");

    @EnumValue
    private final Integer code;
    private final String name;

}