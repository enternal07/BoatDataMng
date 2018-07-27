package com.wisely.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisely.domain.Item;
import com.wisely.domainVO.QueryVO;
import com.wisely.domainVO.ResultVO;
import com.wisely.service.ItemBigService;
import com.wisely.service.ItemContractionService;
import com.wisely.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService service;
	
	@Autowired
	private ItemBigService bigservice;
	
	@Autowired
	private ItemContractionService conservice;

	@RequestMapping("/page")
	public Page<Item> page(@RequestParam(value = "pageindx", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) {

		PageRequest page = this.buildPageRequest(pageIndex, pageIndex, "auto");
		
		return service.findAll(page);
	}
	
	/**
	 * 管理item页面接口
	 * @param pageIndex
	 * @param pageSize
	 * @param item
	 * @return
	 */
	@RequestMapping("/pageSearch")
	public Page<Item> pageSearch(@RequestParam(value = "pageindx", defaultValue = "1") int pageIndex,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize,@RequestBody Item item){
		PageRequest page = this.buildPageRequest(pageIndex, pageIndex, "auto");
		return service.findAuto(item, page);
	}
	
	/**
	 * 控制面板接口,查声管小样数据的数据
	 * @param rate
	 * @param pressure
	 * @param temperature
	 * @param samplepk
	 * @return
	 */
	@RequestMapping(value = "/pageSearchCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO searchItemSmallCondition(@RequestBody QueryVO queryVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		re.setData(service.findByQueryCondtion(queryVO));
		return re;
	}
	
	/**
	 * 控制面板接口,查声管大样数据的数据
	 * @param rate
	 * @param pressure
	 * @param temperature
	 * @param samplepk
	 * @return
	 */
	@RequestMapping(value = "/pageSearchBigCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO searchItemBigCondition(@RequestBody QueryVO queryVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		re.setData(bigservice.getItemBigList(queryVO));
		return re;
	}
	
	/**
	 * 控制面板接口,查声管控制缩比数据的数据
	 * @param rate
	 * @param pressure
	 * @param temperature
	 * @param samplepk
	 * @return
	 */
	@RequestMapping(value = "/pageSearchConCondition",method = RequestMethod.POST)
	public @ResponseBody ResultVO searchItemConCondition(@RequestBody QueryVO queryVO,HttpServletRequest req){
		ResultVO re = new ResultVO(true);
		re.setData( conservice.getItemConList(queryVO));
		return re;
	}

	/**
	 * 构建分页
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	protected PageRequest buildPageRequest(int pageNumber, int pageSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "ts");
		}
		return new PageRequest(pageNumber - 1, pageSize, sort);
	}

}
