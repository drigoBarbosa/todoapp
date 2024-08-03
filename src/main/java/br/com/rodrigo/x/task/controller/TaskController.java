package br.com.rodrigo.x.task.controller;

import br.com.rodrigo.x.task.dto.TaskDTO;
import br.com.rodrigo.x.task.dto.TaskUpdatingDTO;
import br.com.rodrigo.x.task.model.Task;
import br.com.rodrigo.x.task.service.TaskService;
import br.com.rodrigo.x.util.dto.ResponseDTO;
import br.com.rodrigo.x.util.exception.TaskNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("todoapp/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public Task createTask(@NotNull(message = "Task null") @RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @GetMapping("/find/{uuid}")
    public Optional<Task> getTaskById(@NotNull(message = "Id null") @PathVariable("uuid") UUID uuid) {
        return taskService.findTaskById(uuid);
    }

    @PutMapping("/update/{uuid}")
    public Task updateTask(@Valid @RequestBody TaskUpdatingDTO taskUpdatingDTO, @NotNull(message = "Id null") @PathVariable("uuid") UUID uuid) {
        return taskService.updateTask(taskUpdatingDTO, uuid);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> deleteTask(@NotNull(message = "Id null") @PathVariable("uuid") UUID uuid) {
        try {
            taskService.deleteTask(uuid);
            return ResponseEntity.noContent().build();
        } catch (TaskNotFoundException e) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .status("400")
                    .message(e.getMessage())
                    .build();
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }


}
