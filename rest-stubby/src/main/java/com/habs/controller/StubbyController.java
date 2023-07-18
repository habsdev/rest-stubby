package com.habs.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.habs.entities.StubbyConfig;
import com.habs.service.StubbyService;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

@RestController
public class StubbyController {
	
	@Autowired
	private StubbyService stubbyService;
	
	private static final String SHEET_ID = "102dcfQvUAfVUl4xYogQkbDljmUgwbB_cGMO8CyZk47g";
	
	Logger logger = LoggerFactory.getLogger(StubbyController.class);
	
	@GetMapping("/stub")
	public Object stub(@RequestParam(value = "id", defaultValue = "none") String id) throws IOException, GeneralSecurityException {
		
		String response = "{response:''}";
		
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(response);

		
		List<StubbyConfig> configList= stubbyService.retrieveStubConfig(id);
		
		logger.debug(""+configList);
		
		for(StubbyConfig config : configList) {
			JsonPath.parse(document).add(config.getResponseTarget(), JsonPath.parse(config.getValue()).read("$"));
		}
		
		return JsonPath.parse(document).read("$.response");
		//return new Greeting(counter.incrementAndGet(), String.format(template, id));
	}
	
}
