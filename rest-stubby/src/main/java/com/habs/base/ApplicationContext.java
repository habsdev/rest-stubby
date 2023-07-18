package com.habs.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.habs.repository.GoogleSheetsReaderDao;
import com.habs.repository.StubbyRepository;
import com.habs.repository.StubbyRepositoryImpl;
import com.habs.service.StubbyService;
import com.habs.service.StubbyServiceImpl;

@Configuration
public class ApplicationContext {

    @Bean
    public StubbyRepository stubbyRepository() {
        return new StubbyRepositoryImpl();
    }
    
    @Bean
    public StubbyService stubbyService() {
    	StubbyServiceImpl stubService = new StubbyServiceImpl();
    	stubService.setStubsConfigRepository(stubbyRepository());
    	return stubService;
    }    
    
    
}
