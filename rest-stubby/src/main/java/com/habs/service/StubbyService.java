package com.habs.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.habs.entities.StubConfig;

@Service
public interface StubbyService {
	
	public List<StubConfig> retrieveStubConfig(String sheetId) throws IOException, GeneralSecurityException;
	
	public Object buildStubResponse(List<StubConfig> configList);
	
	public Object buildStubResponse(List<StubConfig> configList, String requestMessage);

}
