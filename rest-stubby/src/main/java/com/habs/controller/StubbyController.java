package com.habs.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habs.entities.StubConfig;
import com.habs.service.StubbyService;

@RestController
public class StubbyController {

	private static final String SHEET_ID = "102dcfQvUAfVUl4xYogQkbDljmUgwbB_cGMO8CyZk47g";

	Logger logger = LoggerFactory.getLogger(StubbyController.class);

	@Autowired
	private StubbyService stubbyService;

	@GetMapping("/gstub")
	public Object stubGet(@RequestParam(value = "id", defaultValue = "none") String id)
			throws IOException, GeneralSecurityException {

		List<StubConfig> configList = stubbyService.retrieveStubConfig(id);
		Object response = stubbyService.buildStubResponse(configList);
		return response;
	}
	
	@PostMapping("/pstub")
	public Object stubPost(@RequestBody String requestMessage, @RequestParam(value = "id", defaultValue = "none") String id)
			throws IOException, GeneralSecurityException {

		List<StubConfig> configList = stubbyService.retrieveStubConfig(id);	
		Object response = stubbyService.buildStubResponse(configList, requestMessage);
		return response;
	}

}
