package br.com.rodrigo.x.task.service;

import br.com.rodrigo.x.task.dto.TaskDTO;
import br.com.rodrigo.x.task.dto.TaskUpdatingDTO;
import br.com.rodrigo.x.task.model.Task;
import br.com.rodrigo.x.task.repository.TaskRepository;
import br.com.rodrigo.x.user.model.User;
import br.com.rodrigo.x.user.service.UserService;
import br.com.rodrigo.x.util.exception.BusinessRuleException;
import br.com.rodrigo.x.util.exception.TaskNotFoundException;
import br.com.rodrigo.x.util.exception.UserNotFoundException;
import ch.qos.logback.classic.spi.IThrowableProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task createTask(TaskDTO taskDTO) {
        return createTaskImpl(taskDTO);
    }

    private Task createTaskImpl(TaskDTO taskDTO) {
        Task task = fromTaskDTO(taskDTO);
        return taskRepository.save(task);
    }

    public Task fromTaskDTO(TaskDTO taskDTO) {
        return fromTaskDTOImpl(taskDTO);
    }

    private Task fromTaskDTOImpl(TaskDTO taskDTO) {
        Task task = new Task();
        if (taskDTO.getId() != null) {
            task.setId(taskDTO.getId());
        }
        // associar a tarega a um usuario
        if (taskDTO.getIdUser() != null) {
            User user = userService.findUserById(taskDTO.getIdUser()).
                orElseThrow(() -> new UserNotFoundException("fromTaskDTO - msg: Usuario não existe"));
            task.setUser(user);
        }
        if (taskDTO.getTitle() != null) {
            task.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getCreationDate() != null) {
            task.setCreationDate(taskDTO.getCreationDate());
        } else {
            task.setCreationDate(LocalDateTime.now());
        }
        // Se a flag de expiracao for true o campo de data de vencimento nao pode ser null
        if (taskDTO.isWithExpiration()) {
            if (taskDTO.getDueDate() != null) {
                task.setDueDate(taskDTO.getDueDate());
                task.setWithExpiration(taskDTO.isWithExpiration());
            } else {
                throw new BusinessRuleException("fromTaskDTO - msg: Data de expiração não pode ser null");
            }
        }
        return task;
    }

    public Optional<Task> findTaskById(UUID uuid) {
        return taskRepository.findById(uuid);
    }

    public Task updateTask(TaskUpdatingDTO taskUpdatingDTO, UUID uuid) {
        return updateTaskImpl(taskUpdatingDTO, uuid);
    }
    private Task updateTaskImpl(TaskUpdatingDTO taskUpdatingDTO, UUID uuid) {
        Task taskUpdating = taskRepository.findById(uuid)
                .orElseThrow(() -> new TaskNotFoundException("updateTaskImpl - msg: Task nao existe"));
        if (taskUpdatingDTO.getDescription() != null) {
            taskUpdating.setDescription(taskUpdatingDTO.getDescription());
        }
        if (taskUpdatingDTO.getTitle() != null) {
            taskUpdating.setTitle(taskUpdatingDTO.getTitle());
        }
        if (taskUpdatingDTO.getWithExpiration() != null) {
            if (taskUpdatingDTO.getWithExpiration()) {
                if (taskUpdatingDTO.getDueDate() != null) {
                    taskUpdating.setDueDate(taskUpdatingDTO.getDueDate());
                } else {
                    throw new BusinessRuleException("updateTaskImpl - msg: Se a expiracao for ativa a data de expiracao e obriagatorio");
                }
            }
            taskUpdating.setWithExpiration(taskUpdatingDTO.getWithExpiration());
        }
       return taskRepository.save(taskUpdating);
    }

    public void deleteTask(UUID uuid) {
        if (!taskRepository.findById(uuid).isPresent()) {
            throw new TaskNotFoundException("deleteTask - msg: Task nao encontrada");
        }
        taskRepository.deleteById(uuid);
    }
}
