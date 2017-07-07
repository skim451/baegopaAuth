package com.baegopa.auth.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baegopa.auth.service.SampleService;

@Controller
public class TestController {
	
	@Resource(name="sampleService")
    private SampleService sampleService;
	
	@ResponseBody 
	@RequestMapping(value="/")
	public String root() {
		return "Hello World!"; 
	}
	
	@ResponseBody
	@RequestMapping(value="/userList",method=RequestMethod.POST)
	public String userList(@RequestBody Map<String, String> body) throws Exception {
		String retval = new String(); 
		
		List<Map<String, String>> userMapList = sampleService.selectUserList(body);
		
		for(Map<String, String> userMap : userMapList) {
			for(String key : userMap.keySet()) {
				retval.concat(key + ", ");
			}
		}
		return retval;
	}
}
