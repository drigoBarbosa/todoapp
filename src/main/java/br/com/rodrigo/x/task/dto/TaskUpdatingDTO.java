package br.com.rodrigo.x.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdatingDTO {

    private UUID id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private Boolean withExpiration;
}
