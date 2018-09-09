package com.wisely.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.scale.LayingSchemePO;
import com.wisely.domain.scale.TestConditionPO;
import com.wisely.domain.scale.TestModelObjPO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.scale.LayingSchemeService;
import com.wisely.service.scale.TestConditionService;
import com.wisely.service.scale.TestModelObjService;


@Controller
@RequestMapping(value = "/scaleMataMng")
public class ScaleMetaController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TestModelObjService testModelObjService;
	
	@Autowired
	private TestConditionService testConditionService;
	
	@Autowired
	private LayingSchemeService layingSchemeService;
	

	@RequestMapping(value="scale/testModelObjs")
	public @ResponseBody ResultVO getTestModelObjs(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<TestModelObjPO> list = testModelObjService.findAll();
		res.setData(list);
		return res;
	}

	@RequestMapping(value="scale/testConditions")
	public @ResponseBody ResultVO getTestCondition(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<TestConditionPO> list = testConditionService.findAll();
		res.setData(list);
		return res;
	}
	@RequestMapping(value="scale/layingSchemes")
	public @ResponseBody ResultVO getLayingScheme(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<LayingSchemePO> list = layingSchemeService.findAll();
		res.setData(list);
		return res;
	}

}