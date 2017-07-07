package com.baegopa.auth.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baegopa.auth.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImple implements SampleService{
	
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO; 

	@Override
	public List<Map<String, String>> selectUserList(Map<String, String> map) throws Exception {
		return sampleDAO.selectUserList(map);
	}
	
	
}
