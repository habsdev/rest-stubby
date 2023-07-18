package com.habs.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.habs.entities.StubbyConfig;
import com.habs.service.StubbyService;

@RestController
public class StubbyController {
	
	@Autowired
	private StubbyService stubbyService;
	
	private static final String SHEET_ID = "102dcfQvUAfVUl4xYogQkbDljmUgwbB_cGMO8CyZk47g";
	
	
	@GetMapping("/stub")
	public List<StubbyConfig> greeting(@RequestParam(value = "id", defaultValue = "none") String id) throws IOException, GeneralSecurityException {
		
		List<StubbyConfig> configList= stubbyService.retrieveStubConfig(id);
		return configList;
		//return new Greeting(counter.incrementAndGet(), String.format(template, id));
	}
	
}
