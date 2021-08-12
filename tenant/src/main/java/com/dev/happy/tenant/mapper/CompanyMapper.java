package com.dev.happy.tenant.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dev.happy.tenant.entity.Company;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CompanyMapper extends BaseMapper<Company> {
    @Select("select code from t_company where tenant_id= #{tenantId}")
    List<String> companyCodeList(String tenantId);
}
