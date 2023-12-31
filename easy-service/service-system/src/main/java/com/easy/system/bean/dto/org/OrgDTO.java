package com.easy.system.bean.dto.org;

import com.easy.system.bean.enums.OrgLevel;
import com.easy.system.bean.enums.OrgTag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "组织新增-入参")
public class OrgDTO {

    @Schema(description = "上级id")
    private String orgParentId;

    @Schema(description = "组织名称")
    private String orgName;

    @Schema(description = "组织简称")
    private String orgShortName;

    @Schema(description = "组织级别")
    private OrgLevel orgLevel;

    @Schema(description = "组织类型")
    private String orgType;

    @Schema(description = "组织性质")
    private String orgNature;

    @Schema(description = "机构编码")
    private String orgCode;

    @Schema(description = "组织标签")
    private OrgTag orgTag;

    @Schema(description = "省")
    private String orgProvince;

    @Schema(description = "市")
    private String orgCity;

    @Schema(description = "区")
    private String orgDistrict;

    @Schema(description = "详细地址")
    private String orgAddress;

    @Schema(description = "经度")
    private String orgLongitude;

    @Schema(description = "纬度")
    private String orgLatitude;

    @Schema(description = "机构简介")
    private String orgDesc;

    @Schema(description = "logo")
    private String orgLogo;

    @Schema(description = "人员")
    private String orgPersonnel;

    @Schema(description = "顺序")
    private Integer orgSort;

    @Schema(description = "组织状态")
    private String orgStatus;
}