package com.easy.system.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.api.vo.DictTypeVO;
import com.easy.datasource.bean.dto.IdDTO;
import com.easy.framework.exception.CustomizeException;
import com.easy.system.bean.dto.dict.DictDTO;
import com.easy.system.bean.dto.dict.DictDataDTO;
import com.easy.system.bean.dto.dict.DictSearchDTO;
import com.easy.system.bean.pojo.DictData;
import com.easy.system.bean.pojo.DictType;
import com.easy.system.service.DictTypeService;
import com.easy.utils.lang.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * 字典类型
 * <p>
 * 2022/1/13 18:05
 *
 * @author Matt
 */
@Tag(name = "字典类型")
@RestController
@RequestMapping("/dict")
public class DictTypeController {


    private final DictTypeService dictTypeService;

    public DictTypeController(DictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

    /**
     * 重载缓存
     */
    @PostMapping(value = "/overload")
    @Operation(description = "重载字典缓存")
    public void overloadDict() {
        dictTypeService.loadingDictCache();
    }

    /**
     * 获取字典类型以及其下的字典数据
     *
     * @param dto 字典类型
     * @return 字典数据
     */
    @PostMapping("/dict_by_type")
    @Operation(description = "获取字典类型以及其下的字典数据")
    public DictTypeVO getDictTypeByDictType(@RequestBody DictSearchDTO dto) {
        if (StringUtil.isBlank(dto.getDictType())) {
            throw new CustomizeException("字典类型不能为空");
        }
        return dictTypeService.getDictTypeByDictType(dto.getDictType());
    }

    /**
     * 查询字典类型详细
     *
     * @param dto 主键
     * @return 字典类型信息
     */
    @PostMapping(value = "/get")
    @Operation(description = "获取字典类型详情")
    public DictType getDictTypeInfoById(@Valid @RequestBody IdDTO dto) {
        return dictTypeService.getDictTypeInfoById(dto.getId());
    }

    /**
     * 字典类型和字典数据集合查询
     *
     * @param dto 查询入参
     * @return 集合数据
     */
    @PostMapping(value = "/type_data_list")
    @Operation(description = "集合查询")
    public List<DictType> listTypeAndData(@RequestBody DictSearchDTO dto) {
        return dictTypeService.getTypeAndDataList(dto);
    }

    /**
     * 字典类型-分页查询
     *
     * @param dto 查询入参
     * @return 分页数据
     */
    @PostMapping(value = "/type/page")
    @Operation(description = "分页查询")
    @SaCheckPermission("system.dictType.page")
    public Page<DictType> pageDictType(@RequestBody DictSearchDTO dto) {
        return dictTypeService.pageDictType(dto);
    }


    /**
     * 字典类型-新增
     *
     * @param dto 新增入参
     */
    @PostMapping(value = "/type/add")
    @Operation(description = "新增字典类型")
    @SaCheckPermission("system.dictType.add")
    public String addDictType(@Valid @RequestBody DictDTO dto) {
        dictTypeService.addDictType(dto);
        return "";
    }

    /**
     * 字典类型-更新
     *
     * @param dto 字典信息
     */
    @PostMapping(value = "/type/update")
    @Operation(description = "修改字典类型")
    @SaCheckPermission("system.dictType.update")
    public String updateDictType(@Valid @RequestBody DictDTO dto) {
        dictTypeService.updateDictType(dto);
        return "";
    }

    /**
     * 字典类型-删除
     *
     * @param id 主键
     */
    @PostMapping(value = "/type/del")
    @Operation(description = "删除字典类型")
    @SaCheckPermission("system.dictType.del")
    public String delDictType(@RequestBody IdDTO id) {
        dictTypeService.delDictType(id);
        return "";
    }


    /**
     * 字典类型-分页查询
     *
     * @param dto 查询入参
     * @return 分页数据
     */
    @PostMapping(value = "/data/page")
    @Operation(description = "分页查询")
    @SaCheckPermission("system.dictData.page")
    public Page<DictData> pageDictData(@RequestBody DictSearchDTO dto) {
        return dictTypeService.pageDictData(dto);
    }

    /**
     * 字典数据-新增
     *
     * @param dto 新增入参
     */
    @PostMapping(value = "/data/add")
    @Operation(description = "新增字典数据")
    @SaCheckPermission("system.dictData.add")
    public String addDictData(@Valid @RequestBody DictDataDTO dto) {
        dictTypeService.addDictData(dto);
        return "";
    }

    /**
     * 字典数据-更新
     *
     * @param dto 字典信息
     */
    @PostMapping(value = "/data/update")
    @Operation(description = "修改字典类型")
    @SaCheckPermission("system.dictData.update")
    public String updateDictData(@Valid @RequestBody DictDataDTO dto) {
        dictTypeService.updateDictData(dto);
        return "";
    }

    /**
     * 字典数据-删除
     *
     * @param ids 主键集合
     */
    @PostMapping(value = "/data/del")
    @Operation(description = "删除字典类型")
    @SaCheckPermission("system.dictData.del")
    public String delDictData(@RequestBody List<String> ids) {
        dictTypeService.delDictData(ids);
        return "";
    }
}