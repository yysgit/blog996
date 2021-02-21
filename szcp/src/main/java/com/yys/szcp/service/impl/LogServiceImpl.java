package com.yys.szcp.service.impl;

import com.yys.szcp.entity.DbLog;
import com.yys.szcp.mapper.DbLogMapper;
import com.yys.szcp.service.DbLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class LogServiceImpl implements DbLogService {
	
	@Autowired
	private DbLogMapper logMapper;

	@Override
	public void addLog(DbLog log) {
        logMapper.addLog(log);
	}

	@Override
	public List<Map> findLogList(Map map) {
		return logMapper.findLogList(map);
	}

	@Override
	public int findLogListCount(Map map) {
		return logMapper.findLogListCount(map);
	}

	@Override
	public int delLogsByDate(Date date) {
		return logMapper.delLogsByDate(date);
	}

}
