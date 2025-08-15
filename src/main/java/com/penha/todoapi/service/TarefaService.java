package com.penha.todoapi.service;


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

    public Tarefa criar(Tarefa tarefa){
        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setConcluida(false);
        return repository.save(tarefa);
    }

    public List<Tarefa> listar() {
        return repository.findAll();
    }

    public Tarefa buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Tarefa atualizar(Long id, Tarefa nova){
        Tarefa antiga = buscarPorId(id);
        antiga.setTitulo(nova.getTitulo());
        antiga.setDescricao((nova.getDescricao()));
        antiga.setConcluida(nova.getConcluida());
        return repository.save(antiga);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
