package com.spodraft.asana_airtable.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.spodraft.asana_airtable.entity.Task;

@Service
public class TaskService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void copyTaskToAirtable(Task task) {
        // Construct the API URL and headers
        String apiKey = "pat4uAbSYQhm08SvH.4c485ce2de2256ba6710d9c04df6b32267c23a555050c97def93abbec167493e";
        String baseId = "appSGxKamXW8d0hxM";
        String apiUrl = "https://api.airtable.com/v0/" + baseId + "/Asana%20Tasks";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Create a request object with task data
        HttpEntity<Task> request = new HttpEntity<>(task, headers);
        
        // Make a POST request to create a new row in Airtable
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        
        // Handle the response as needed
    }
}
//Token Id=pat4uAbSYQhm08SvH.4c485ce2de2256ba6710d9c04df6b32267c23a555050c97def93abbec167493e
