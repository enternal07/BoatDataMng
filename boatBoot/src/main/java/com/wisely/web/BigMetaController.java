package com.wisely.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisely.domain.BaseMetaSample;
import com.wisely.domain.TestModel;
import com.wisely.domain.TestSystem;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaSampleService;
import com.wisely.service.SmallDemoMetaDataService;
import com.wisely.service.TestModelService;
import com.wisely.service.TestSystemService;


@Controller
@RequestMapping(value = "/bigmetaMng")
public class BigMetaController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SmallDemoMetaDataService service;
	
	@Autowired
	private  BaseMetaSampleService  sampleService;
	
	@Autowired
	private  TestSystemService  serviceTestSystem;
	
	@Autowired
	private TestModelService serviceTestModel;
	

	@RequestMapping(value="big/sample")
	public @ResponseBody ResultVO getBigSamlple(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<BaseMetaSample> list = sampleService.findBig();
		res.setData(list);
		return res;
	}

	@RequestMapping(value="big/testModel")
	public @ResponseBody ResultVO getBigTestModel(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<TestModel> list = serviceTestModel.findAll();
		res.setData(list);
		return res;
	}
	
	@RequestMapping(value="big/testSystems")
	public @ResponseBody ResultVO getBigTestSystems(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<TestSystem> list = serviceTestSystem.findAll();
		res.setData(list);
		return res;
	}
	

}