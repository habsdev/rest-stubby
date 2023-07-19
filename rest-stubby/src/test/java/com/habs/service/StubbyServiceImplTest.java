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
import com.habs.entities.StubConfig;

@SpringBootTest
@ContextConfiguration(classes = TestApplicationContext.class)
class StubbyServiceImplTest {
	
	@Autowired
	private StubbyService stubbyService;
	
	private static final String SHEET_ID = "102dcfQvUAfVUl4xYogQkbDljmUgwbB_cGMO8CyZk47g";

	@Test
	@DisplayName("Test retrieveStubConfiguration")
	public void testAddition() throws IOException, GeneralSecurityException {
		List<StubConfig> configList = this.stubbyService.retrieveStubConfig(SHEET_ID);
		Assertions.assertTrue(configList.size() > 0, "configuration should be retrieved");
		System.out.println(configList.toString());
		
		int priority = -1;
		
		for (StubConfig config : configList) {
			System.out.println(config.toString());
			
			Assertions.assertTrue(config.getAction() != null, "Action should have a value");
			Assertions.assertTrue(config.getPriority() > 0, "Priorty should have a value");
			Assertions.assertTrue(config.getFilter() != null, "Filter should have a value");
			Assertions.assertTrue(config.getTarget() != null, "Target should have a value");
			Assertions.assertTrue(config.getStatus() != null, "Status should have a value");
			
			if(!"DELETE".equalsIgnoreCase(config.getAction())){
				Assertions.assertTrue(config.getValue() != null, "Value should have a value");
			}
			
			if("PUT".equalsIgnoreCase(config.getAction())){
				Assertions.assertTrue(config.getKey() != null, "Key should have a value");
			}
			
			Assertions.assertTrue(priority <= config.getPriority(), "Entities should be sorted based on priority");
			
			priority = config.getPriority();
		}

	}


}
