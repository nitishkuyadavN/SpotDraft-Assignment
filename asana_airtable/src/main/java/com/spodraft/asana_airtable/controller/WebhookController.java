package com.spodraft.asana_airtable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spodraft.asana_airtable.entity.Task;
import com.spodraft.asana_airtable.service.TaskService;

@RestController
public class WebhookController {
	 private final TaskService taskService;

	    @Autowired
	    public WebhookController(TaskService taskService) {
	        this.taskService = taskService;
	    }
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(payload);

            // Assuming payload structure: {"taskID": ..., "name": ..., "assignee": ..., "dueDate": ..., "description": ...}
            String taskID = rootNode.get("taskID").asText();
            String name = rootNode.get("name").asText();
            String assignee = rootNode.get("assignee").asText();
            String dueDate = rootNode.get("dueDate").asText();
            String description = rootNode.get("description").asText();
            
            // Create a new Task object
            Task task = new Task();
            task.setTaskID(taskID);
            task.setName(name);
            task.setAssignee(assignee);
            task.setDueDate(dueDate);
            task.setDescription(description);
            
            // Call the Airtable service to push the task to Airtable
            taskService.copyTaskToAirtable(task);
            
            return ResponseEntity.ok("Webhook received and processed successfully");
        } catch (Exception e) {
            // Handle any exceptions that might occur during processing
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing webhook payload: " + e.getMessage());
        }
    }
}
