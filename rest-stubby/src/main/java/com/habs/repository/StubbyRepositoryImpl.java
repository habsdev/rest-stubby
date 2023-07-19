package com.habs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.habs.base.BaseService;
import com.habs.entities.StubConfig;
import com.habs.repository.GoogleSheetsReaderDao;

@Component
public class StubbyRepositoryImpl extends BaseService implements StubbyRepository{

	private final static String SHEET_RANGE = "Sheet1!A1:Z1000";
	private GoogleSheetsReaderDao googleSheetsReaderDao;
	
	public StubbyRepositoryImpl() {
		this.googleSheetsReaderDao = new GoogleSheetsReaderDao();
	}

	public List<StubConfig> retrieveStubConfig(String sheetId) throws IOException, GeneralSecurityException {
		
		List<StubConfig> stubConfigList = new ArrayList<StubConfig>();
		
		List<List<Object>> recordList = this.googleSheetsReaderDao.readSheet(sheetId, SHEET_RANGE);
		List<Object> headerList = recordList.remove(0);
		
		for(List<Object> record : recordList) {
			StubConfig config = new StubConfig();
			
			for(int i=0; i<headerList.size(); i++) {
				
				String label = (String)headerList.get(i);
				String value = (String)record.get(i);
			
				
				switch (label.toUpperCase()) {
				  case "PRIORITY":
					config.setPriority(Integer.parseInt(value));
				    break;
				  case "STATUS":
					config.setStatus(value);
				    break;
				  case "ACTION":
					config.setAction(value);
					break;
				  case "FILTER PATH":
					config.setFilter(value);
					break;
				  case "VALUE":
					config.setValue(value); 
					break;
				  case "TARGET PATH":
					config.setTarget(value);					  
					break;
				  case "ATTRIBUTE NAME":
					config.setKey(value);					  
					break;					
				  default:
				    // code block
				}								
			}
			
			if("ACTIVE".equalsIgnoreCase(config.getStatus())) {
				stubConfigList.add(config);
			}
			
		}
		return stubConfigList;
	}

}
