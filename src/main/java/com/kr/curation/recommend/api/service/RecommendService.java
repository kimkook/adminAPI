package com.kr.curation.recommend.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kr.curation.recommend.api.mapper.RecommendMapper;

@Service
@Transactional
public class RecommendService {

	@Autowired
	RecommendMapper recommendMapper;
}
