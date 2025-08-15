package com.penha.todoapi.dto;

import java.time.LocalDate;
public record TarefaResponseDTO(Long in, String titulo, String descricao, Boolean concluida, LocalDate dataCriacao) {
}
