package com.yys.szcp.controller;


import com.yys.szcp.constant.ExceptionConstant;
import com.yys.szcp.service.DbLogService;
import com.yys.szcp.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("sys/log")
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
	private DbLogService logServiceImpl;

	@RequestMapping("getLogList")
	@ResponseBody
	public ResultUtil getLogList(Integer page, Integer limit){
		ResultUtil resultUtil=new ResultUtil();
		Map map=new HashMap();
		map.put("limit",limit);
		map.put("page",limit);
		resultUtil.setCode(ExceptionConstant.SUCCESS_HTTPREUQEST);
		resultUtil.setCount(logServiceImpl.findLogListCount(map));
		resultUtil.setData(logServiceImpl.findLogList(map));
		return resultUtil;
	}
}
