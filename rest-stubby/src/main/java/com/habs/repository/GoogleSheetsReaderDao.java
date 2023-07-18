package com.habs.repository;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GoogleSheetsReaderDao {
	
	private static final String SERVICE_ACCOUNT_FILE = "/Users/habs/service_account_key.json";
	private static final String SHEET_ID = "102dcfQvUAfVUl4xYogQkbDljmUgwbB_cGMO8CyZk47g";
	private static final String SHEET_RANGE = "Sheet1!A1:Z1000";
	
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
	/*
	public static void main(String[] args) throws IOException, GeneralSecurityException {
		
		GoogleSheetsReaderDao sheetsDao = new GoogleSheetsReaderDao();
		List<List<Object>> values = sheetsDao.readSheet(SHEET_ID, SHEET_RANGE);
		List<Map<String, Object>> json = toJson(values);
		
		System.out.println("json");
		System.out.println(json);
	}
	*/
	
	private static HttpCredentialsAdapter getCredentials() throws IOException, GeneralSecurityException {
		// Load the service account credentials from JSON key file
    	GoogleCredentials credentials = ServiceAccountCredentials.fromStream(
                 new FileInputStream(SERVICE_ACCOUNT_FILE))
                 .createScoped(Lists.newArrayList(SheetsScopes.SPREADSHEETS));
    	 
    	// Create an HTTP credentials adapter
        return new HttpCredentialsAdapter(credentials);
	}

    public List<List<Object>> readSheet(String spreadsheetId, String range) throws IOException, GeneralSecurityException {
    	
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	 
        // Create a Sheets client
        Sheets sheets = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
                .setApplicationName("Google Sheets API Data Access")
                .build();

        // Make the API request to get values from the specified range
        ValueRange response = sheets.spreadsheets().values().get(spreadsheetId, range).execute();

        // Get the values from the response
        List<List<Object>> values = response.getValues();
       
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
            return new ArrayList<List<Object>>();       
        }else {        
	        System.out.println("Data size: "+values.size());
	        return values;
        }
    }
    
    /**
     * @deprecated
     * @param values
     * @return
     */
    public static List<Map<String, Object>> toJson(List<List<Object>> values){
    	
    	List<Map<String, Object>> json = new ArrayList<Map<String, Object>>();
    	
    	List<Object> headers = null;
    	
        for (List<Object> row : values) {
        	System.out.println(row);
        	
        	if(headers == null) {
        		headers = row;
        	}else { 
        		
        		Map<String, Object> entity = new HashMap<String, Object>();
        		for(int i = 0; i < headers.size(); i++) {
        			entity.put((String)headers.get(i), row.get(i));	
        		}
        		json.add(entity);
        	}
        }
    	
    	return json;
    }
    
}



