package com.penha.todoapi.service;


import com.penha.todoapi.dto.TarefaRequestDTO;
import com.penha.todoapi.dto.TarefaResponseDTO;
import com.penha.todoapi.entity.Tarefa;
import com.penha.todoapi.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaResponseDTO criar(TarefaRequestDTO dto){
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo((dto.titulo()));
        tarefa.setDescricao(dto.descricao());
        tarefa.setConcluida(false);
        tarefa.setDataCriacao(LocalDate.now());

        Tarefa salva = repository.save(tarefa);
        return toResponseDTO(salva);
    }

    public List<TarefaResponseDTO> listar() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public TarefaResponseDTO buscarPorId(Long id){
        return toResponseDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada")));
    }

    public TarefaResponseDTO atualizar(Long id, TarefaRequestDTO dto){
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setConcluida(dto.concluida());

        Tarefa atualizada = repository.save(tarefa);
        return toResponseDTO(atualizada);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    private TarefaResponseDTO toResponseDTO(Tarefa tarefa){
        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getConcluida(),
                tarefa.getDataCriacao()
        );
    }
}
