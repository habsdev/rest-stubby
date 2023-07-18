package com.habs.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.habs.base.TestApplicationContext;
import com.habs.entities.StubbyConfig;

@SpringBootTest
@ContextConfiguration(classes = TestApplicationContext.class)
class StubbyServiceImplTest {
	
	@Autowired
	public StubbyService stubbyService;
	
	public StubbyService getStubsService() {
		return stubbyService;
	}

	public void setStubsService(StubbyService stubbyService) {
		this.stubbyService = stubbyService;
	}	

	private static final String SHEET_ID = "102dcfQvUAfVUl4xYogQkbDljmUgwbB_cGMO8CyZk47g";

	@Test
	@DisplayName("Test retrieveStubConfiguration")
	public void testAddition() throws IOException, GeneralSecurityException {
		List<StubbyConfig> configList = this.stubbyService.retrieveStubConfig(SHEET_ID);
		Assertions.assertTrue(configList.size() > 0, "configuration should be retrieved");
		System.out.println(configList.toString());
		
		int priority = -1;
		
		for (StubbyConfig config : configList) {
			System.out.println(config.toString());
			
			Assertions.assertTrue(config.getAction() != null, "Action should have a value");
			Assertions.assertTrue(config.getPriority() > 0, "Priorty should have a value");
			Assertions.assertTrue(config.getRequestFilter() != null, "Filter should have a value");
			Assertions.assertTrue(config.getResponseTarget() != null, "Target should have a value");
			Assertions.assertTrue(config.getStatus() != null, "Status should have a value");
			Assertions.assertTrue(config.getValue() != null, "Value should have a value");
			Assertions.assertTrue(priority <= config.getPriority(), "Priority should be increasing");
			
			priority = config.getPriority();
		}

	}


}
