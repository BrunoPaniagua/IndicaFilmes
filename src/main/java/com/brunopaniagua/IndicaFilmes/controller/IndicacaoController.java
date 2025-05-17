package com.brunopaniagua.IndicaFilmes.controller;

import com.brunopaniagua.IndicaFilmes.dto.FilmeDTO;
import com.brunopaniagua.IndicaFilmes.service.FilmeService;
import com.brunopaniagua.IndicaFilmes.service.GeminiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class IndicacaoController {

    private final GeminiService geminiService;
    private final FilmeService filmeService;

    public IndicacaoController(GeminiService geminiService, FilmeService filmeService) {
        this.geminiService = geminiService;
        this.filmeService = filmeService;
    }

    @GetMapping("/indicar")
    public Mono<ResponseEntity<String>> gerarIndicacao(){
        List<FilmeDTO> filmes = filmeService.listarFilmes();
        return geminiService.gerarIndicacao(filmes)
                .map(indicacao -> ResponseEntity.ok(indicacao))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma indicação disponível."));
    }

}
