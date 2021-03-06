package com.yys.szcp.service;

import com.yys.szcp.entity.DbLog;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface DbLogService {

	
	//获取日志列表
	List<Map> findLogList(Map map);
	int findLogListCount(Map map);

	//添加日志
	void addLog(DbLog log);

	//删除指定日期以前的日志
	int delLogsByDate(Date date);
}
