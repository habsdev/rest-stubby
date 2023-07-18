package com.habs.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.habs.base.BaseService;
import com.habs.entities.StubbyConfig;
import com.habs.entities.StubbyConfigComparator;
import com.habs.repository.StubbyRepository;

@Service
public class StubbyServiceImpl extends BaseService implements StubbyService {

	private StubbyRepository stubbyRepository;

	public void setStubsConfigRepository(StubbyRepository stubbyRepository) {
		this.stubbyRepository = stubbyRepository;
	}

	public List<StubbyConfig> retrieveStubConfig(String sheetId) throws IOException, GeneralSecurityException {
		List<StubbyConfig> stubbyConfigList = this.stubbyRepository.retrieveStubConfig(sheetId);
		stubbyConfigList.sort(new StubbyConfigComparator());
		return stubbyConfigList;
	}
	
	public String buildResponseMessage(String requestMessage, List<StubbyConfig> stubbyConfigList) {

		
		String responseMessage = "";
		
		for(StubbyConfig config : stubbyConfigList) {
			
			if(!"ACTIVE".equalsIgnoreCase(config.getStatus())){
				continue;
			}
			
			// TODO: Update this later to JSONPath rules
			if(!requestMessage.contains(config.getRequestFilter())) {
				continue;
			}
			
			switch (config.getAction()) {
			  case "UPDATE":
				responseMessage = config.getValue();
			    break;
			  case "DELETE":
				responseMessage = "";
			    break;
			  case "ADD":
				responseMessage = responseMessage+", "+config.getValue();
				break;
			  default:
				// code block
			}
			
		}
		
		return responseMessage;
	}

}
