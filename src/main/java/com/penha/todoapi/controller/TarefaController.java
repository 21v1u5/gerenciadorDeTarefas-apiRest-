package com.penha.todoapi.controller;

import com.penha.todoapi.dto.TarefaRequestDTO;
import com.penha.todoapi.dto.TarefaResponseDTO;
import com.penha.todoapi.entity.Tarefa;
import com.penha.todoapi.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;

    @Operation(summary = "Adicionar tarefa", description = "Adicionar o que voce escrever na lista")
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(@RequestBody TarefaRequestDTO dto){
        return ResponseEntity.ok(service.criar(dto));
    }

    @Operation(summary = "Listar tarefas", description = "Retorna todas as tarefas da lista")
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "buscar tarefas por ID", description = "Retorna os detalhaes de uma tarefa especifica")
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "atualizar tarefa", description = "modifica os dados de uma tarefa existente")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(summary = "deletar tarefa", description = "Remove uma tarefa da lista")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
