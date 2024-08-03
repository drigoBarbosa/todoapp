package br.com.rodrigo.x.task.service;

import br.com.rodrigo.x.task.dto.TaskDTO;
import br.com.rodrigo.x.task.model.Task;
import br.com.rodrigo.x.task.repository.TaskRepository;
import br.com.rodrigo.x.user.model.User;
import br.com.rodrigo.x.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public TaskService taskService;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_processTask() {
        //User user = userService.findUserById(UUID.fromString("8f7cb4b3-28e5-4ddb-807c-2b064e28d024")).get();
        //Assert.notNull(user, "Usuario null");
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setCreationDate(LocalDateTime.now()); // data de criacao
        taskDTO.setIdUser(UUID.fromString("8f7cb4b3-28e5-4ddb-807c-2b064e28d024")); // usuario
        taskDTO.setTitle("Primeira tarefa");
        taskDTO.setDescription("Adicionar a primeira tarefa");
        taskDTO.setWithExpiration(true);
        taskDTO.setDueDate(LocalDate.of(2024, 10, 02));

        // fromTaskDTO
        Task task = new Task();
        task.setCreationDate(taskDTO.getCreationDate());
        // buscar usuario
        User user = userService.findUserById(taskDTO.getIdUser()).get();
        Assert.notNull(user, "Usuario não existe");
        task.setUser(user);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setWithExpiration(taskDTO.isWithExpiration());
        task.setDueDate(taskDTO.getDueDate());

        Task taskCreated = taskRepository.save(task);
        Assert.notNull(taskCreated, "Task não foi criada");
        System.out.println(taskCreated.getId());
    }

    @Test
    public void test_fromTaskDTO() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(UUID.fromString("8f7cb4b3-28e5-4ddb-807c-2b064e28d024"));
        taskDTO.setCreationDate(LocalDateTime.now()); // data de criacao
        taskDTO.setIdUser(UUID.fromString("8f7cb4b3-28e5-4ddb-807c-2b064e28d024")); // usuario
        taskDTO.setTitle("Primeira tarefa");
        taskDTO.setDescription("Adicionar a primeira tarefa");
        taskDTO.setWithExpiration(true);
        taskDTO.setDueDate(LocalDate.of(2024, 10, 02));
//        when(taskRepository.)
        Task task = taskService.fromTaskDTO(taskDTO);
        Assert.notNull(task, "task não deveria retornar null");
        assertEquals(task.getId(), taskDTO.getId(), "Os IDs deveriam ser iguais");
        assertEquals(task.getUser().getId(), taskDTO.getIdUser(), "Os IDs dos usuarios deveriam ser iguais");
        assertEquals(task.getTitle(), taskDTO.getTitle(), "Os titulos deveriam ser iguais");
        assertEquals(task.getDescription(), taskDTO.getDescription(), "As descrições deveriam ser iguais");
        assertEquals(task.getCreationDate(), taskDTO.getCreationDate(), "As datas de criação deveriam ser iguais");
        assertEquals(task.getDueDate(), taskDTO.getDueDate(), "As datas de vencimento deveriam ser iguais");
        assertEquals(task.isWithExpiration(), taskDTO.isWithExpiration(), "As flags das datas de expiração deveriam ser iguais");
    }

    @Test
    public void test_createTask() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setCreationDate(LocalDateTime.now()); // data de criacao
        taskDTO.setIdUser(UUID.fromString("8f7cb4b3-28e5-4ddb-807c-2b064e28d024")); // usuario
        taskDTO.setTitle("Primeira tarefa");
        taskDTO.setDescription("Adicionar a primeira tarefa");
        taskDTO.setWithExpiration(true);
        taskDTO.setDueDate(LocalDate.of(2024, 10, 02));

        Task task = taskService.createTask(taskDTO);
        // se o retorno for null lança IllegalArgumentException
        Assert.notNull(task, "Task não foi criada");
    }

}
