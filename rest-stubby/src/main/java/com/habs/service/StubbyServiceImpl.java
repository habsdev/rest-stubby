package com.habs.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.habs.base.BaseService;
import com.habs.entities.StubConfig;
import com.habs.entities.StubbyConfigComparator;
import com.habs.repository.StubbyRepository;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@Service
public class StubbyServiceImpl extends BaseService implements StubbyService {

	private StubbyRepository stubbyRepository;

	@Override
	public Object buildStubResponse(List<StubConfig> configList) {
		return this.buildStubResponse(configList, "{request: ''}");
	}

	@Override
	public Object buildStubResponse(List<StubConfig> configList, String requestMessage) {

		// wrap request message into a JSONPath document context for later processing
		String request = "{request:''}";
		Object requestObject = Configuration.defaultConfiguration().jsonProvider().parse(request);
		DocumentContext requestDocument = JsonPath.parse(requestObject);
		Object requestMessageObj = Configuration.defaultConfiguration().jsonProvider().parse(requestMessage);
		requestDocument.set("$.request", requestMessageObj);

		// initialize the response message
		String response = "{response:''}";
		Object responseObject = Configuration.defaultConfiguration().jsonProvider().parse(response);
		DocumentContext responseDocument = JsonPath.parse(responseObject);

		// build the response
		for (StubConfig config : configList) {

			System.out.println(config);
			String targetPath = config.getTarget();
			String filter = config.getFilter();
						
			if(filter != null && !filter.isEmpty()) {
				List<Object> requestSnippet = requestDocument.read(filter);

				System.out.println("requestSnippet: "+requestSnippet);
				if(requestSnippet == null || requestSnippet.isEmpty()) {
					continue;
				}
			}

			Object value = null;

			if (config.getValue() != null && !config.getValue().isEmpty()) {

				String pathValue = config.getValue();

				if (config.getValue().startsWith("$.request")) {
					value = requestDocument.read(pathValue);

				} else if (config.getValue().startsWith("$.response")) {
					value = responseDocument.read(pathValue);

				} else {
					value = JsonPath.parse(pathValue).read("$");
				}
			}

			switch (config.getAction()) {
			case "SET":
				// Set a value of an existing attribute
				responseDocument.set(targetPath, value);
				break;
			case "ADD":
				// Add new entity in an array attribute
				responseDocument.add(targetPath, value);
				break;
			case "PUT":
				// Create new attribute
				responseDocument.put(targetPath, config.getKey(), value);
				break;
			case "DELETE":
				// Delete an attribute
				responseDocument.delete(targetPath);
				break;
			}
		}

		return responseDocument.read("$.response");

	}

	public List<StubConfig> retrieveStubConfig(String sheetId) throws IOException, GeneralSecurityException {
		List<StubConfig> stubbyConfigList = this.stubbyRepository.retrieveStubConfig(sheetId);
		stubbyConfigList.sort(new StubbyConfigComparator());
		return stubbyConfigList;
	}

	public void setStubsConfigRepository(StubbyRepository stubbyRepository) {
		this.stubbyRepository = stubbyRepository;
	}

}
