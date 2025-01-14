package com.tw.spring.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.tw.spring.controller.LivroRequest;
import com.tw.spring.exception.DefaultException;
import com.tw.spring.model.Livro;
import com.tw.spring.repository.LivrosRepository;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
@AllArgsConstructor
public class LivroService {
    private final LivrosRepository livrosRepository;

    public Livro salvar(LivroRequest livroRequest) {
        return livrosRepository.save(livroRequest.converterClasse());
    }

    public List<Livro> buscarTodos() {
        return livrosRepository.findAll();
    }


    public Livro buscarPorIdOuFalhar(@RequestParam(name = "id") Long id) {
        return livrosRepository.findById(id).orElseThrow(() -> new DefaultException(BAD_REQUEST, "id nao encontrado"));
    }

    public Livro atualizarLivro(Long id, LivroRequest livroRequest) {
        var entity = buscarPorIdOuFalhar(id);

        BeanUtils.copyProperties(livroRequest, entity, "id");
        return livrosRepository.save(entity);

    }


    public void deletar(Long id) {
        var objeto = buscarPorIdOuFalhar(id);

        livrosRepository.delete(objeto);

    }
}




