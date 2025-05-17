package com.brunopaniagua.IndicaFilmes.controller;

import com.brunopaniagua.IndicaFilmes.service.FilmeService;
import com.brunopaniagua.IndicaFilmes.service.GeminiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
        return geminiService.gerarIndicacao()
                .map(indicacao -> ResponseEntity.ok(indicacao))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma indicação disponível."));
    }

}
