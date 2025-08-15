package com.penha.todoapi.controller;

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

    @Operation(summary = "Adicionar tarefa", description = "Adicionar o que voce escrever na lista de tarefas")
    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa){
        return ResponseEntity.ok(service.criar(tarefa));
    }

    @Operation(summary = "Listar tarefas", description = "Retorna tudo que foi adicionado na lita de tarefas")
    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @Operation(summary = "buscar tarefas", description = "Retorna os detalhaes de uma tarefa que voce escolher")
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(summary = "atualizar tarefa", description = "modifica alguma tarefa que voce ja tenha adicionado na lista")
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa){
        return ResponseEntity.ok(service.atualizar(id, tarefa));
    }

    @Operation(summary = "deletar tarefa", description = "Deleta tudo referente a tarefa que voce adicionou")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
