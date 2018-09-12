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

import com.wisely.domain.common.BaseMetaSample;
import com.wisely.domain.small.BaseMetaBacking;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.BaseMetaBackingService;
import com.wisely.service.BaseMetaSampleService;
import com.wisely.service.SmallDemoMetaDataService;

import net.minidev.json.JSONObject;


@Controller
@RequestMapping(value = "/metaMng")
public class MetaController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SmallDemoMetaDataService service;
	
	@Autowired
	private  BaseMetaSampleService  sampleService;
	
	@Autowired
	private  BaseMetaBackingService  bankService;
	

	

	@RequestMapping(value="small/sample")
	public @ResponseBody ResultVO getSmallSamlple(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<BaseMetaSample> list = sampleService.findAll();
		res.setData(list);
		return res;
	}

	@RequestMapping(value="small/backing")
	public @ResponseBody ResultVO getSmallBacking(HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		List<BaseMetaBacking> list = bankService.findAll();
		res.setData(list);
		return res;
	}
	
	@RequestMapping(value="sampleDemo/{small}")
	public @ResponseBody ResultVO getSmallSamlpleAndBack(@PathVariable(value="small")boolean small,HttpServletRequest req) {
		ResultVO res = new ResultVO(true);
		JSONObject list = service.findBySmall(small);
		res.setData(list);
		return res;
	}

	

}