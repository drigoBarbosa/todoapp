package br.com.rodrigo.x.task.dto;

import br.com.rodrigo.x.user.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private UUID id;

    @NotNull(message = "Campo obrigatório!")
    private UUID IdUser;

    @NotNull(message = "Campo obrigatório!")
    private String title;

    @NotNull(message = "Campo obrigatório!")
    private String description;

    private LocalDateTime creationDate;

    private LocalDate dueDate;

    private boolean withExpiration;

}
