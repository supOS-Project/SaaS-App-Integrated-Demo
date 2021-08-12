package com.dev.happy.tenant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.happy.tenant.entity.SyncLog;
import com.dev.happy.tenant.mapper.SyncLogMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SyncLogService extends ServiceImpl<SyncLogMapper, SyncLog> {

    public boolean checkExistByType(String tenantId, String type) {
        LambdaQueryWrapper<SyncLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SyncLog::getType, type).eq(SyncLog::getTenantId, tenantId);
        return baseMapper.selectCount(queryWrapper) > 0;
    }

    public void saveLogByType(String tenantId, String type) {
        LambdaQueryWrapper<SyncLog> wrapper = new LambdaQueryWrapper();
        wrapper.eq(SyncLog::getType, type)
                .eq(SyncLog::getTenantId, tenantId)
                .last(" limit 1");
        SyncLog tmp = baseMapper.selectOne(wrapper);
        SyncLog syncLog = new SyncLog();
        syncLog.setType(type);
        syncLog.setTenantId(tenantId);
        syncLog.setUpdateTime(new Date());
        if (tmp != null) {
            syncLog.setId(tmp.getId());
            baseMapper.updateById(syncLog);
        } else {
            baseMapper.insert(syncLog);
        }
    }

}
