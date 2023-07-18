package com.habs.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.habs.entities.StubbyConfig;

@Service
public interface StubbyService {
	public List<StubbyConfig> retrieveStubConfig(String sheetId) throws IOException, GeneralSecurityException;

}
