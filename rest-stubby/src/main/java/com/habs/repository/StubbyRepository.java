package com.habs.repository;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.habs.entities.StubConfig;

@Repository
public interface StubbyRepository {
	
	public List<StubConfig> retrieveStubConfig(String sheetId) throws IOException, GeneralSecurityException;

}
