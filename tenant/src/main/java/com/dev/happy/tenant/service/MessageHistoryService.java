package com.dev.happy.tenant.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dev.happy.tenant.entity.MessageHistory;
import com.dev.happy.tenant.mapper.MessageHistoryMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageHistoryService extends ServiceImpl<MessageHistoryMapper, MessageHistory> {
}
