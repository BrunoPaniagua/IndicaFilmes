package com.brunopaniagua.IndicaFilmes.controller;

import com.brunopaniagua.IndicaFilmes.dto.FilmeDTO;
import com.brunopaniagua.IndicaFilmes.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarFilme(@RequestBody FilmeDTO filmeDTO){
        FilmeDTO filme = filmeService.cadastrarFilme(filmeDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Filme " + filme.getTitulo() + " cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarFilme(){
        List<FilmeDTO> filmes = filmeService.listarFilmes();
        if(filmes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Nenhum filme cadastrado");
        }
        return ResponseEntity.ok(filmes);
    }

    //buscar por id
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarFilme(@PathVariable long id){
        FilmeDTO filmeProcurado = filmeService.buscarFilmePorId(id);
        if(filmeProcurado != null){
            return ResponseEntity.ok(filmeProcurado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Filme com id " + id + " não foi encontrado!");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> deletarFilme(@PathVariable Long id){
        if(filmeService.buscarFilmePorId(id) != null){
            filmeService.removerFilme(id);
            return ResponseEntity.ok("Filme removido com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Filme com o id " + id + " não foi encontrado!");
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarFilme(@PathVariable Long id, @RequestBody FilmeDTO filmeDTO){
        FilmeDTO filmeAtualizado = filmeService.atualizarFilme(id, filmeDTO);
        if(filmeAtualizado != null){
             return ResponseEntity.ok(filmeAtualizado);
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body("Filme com id " + id + " não foi encontrado!");
    }

}
