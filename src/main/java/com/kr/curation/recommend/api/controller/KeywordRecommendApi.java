package com.kr.curation.recommend.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kr.curation.recommend.api.service.RecommendService;

@RestController
@RequestMapping("/v1")
public class KeywordRecommendApi {

	@Autowired
	RecommendService recommendService;
	
	/**
	 * EPL 기본테이블을 load 하여 모든 데이터를 넘긴다.
	 * @return EPL_BAS 테이블 리스트
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String getEplList() throws Exception {
		
		return "메세지 확인";
	}
	
	@RequestMapping(value = "/dbtest", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> dbtest(	
																@RequestParam(value="gender", required=true) String gender
																	) throws Exception {
		System.out.println(gender);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("gender", gender);
		return recommendService.testDbConnect(param);
	}
	
	@RequestMapping(value = "/postTest/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dbtest(	@PathVariable String id,
															@RequestBody HashMap<String, Object> parameter
															) throws Exception {
		Map<String,Object> param = new HashMap<String,Object>();
		return recommendService.testDbConnect(param);
	}
	
}
