//package com.bal.user_service.client;
//
//import com.bal.user_service.dto.TaskResponseDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@FeignClient(name = "task-service", url = "http://localhost:8082")
//public interface TaskServiceClient {
//
//    @GetMapping("/api/tasks/user/{userId}")
//    List<TaskResponseDTO> getAllUserTasks(@PathVariable String userId);
//}