package com.brunopaniagua.IndicaFilmes.dto;

import com.brunopaniagua.IndicaFilmes.enums.CategoriaFilme;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class FilmeDTO {

    private Long id;
    private String titulo;
    private CategoriaFilme categoria;
    private LocalDate dataQueFoiVisto;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public CategoriaFilme getCategoria() {
        return categoria;
    }

    public LocalDate getDataQueFoiVisto() {
        return dataQueFoiVisto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(CategoriaFilme categoria) {
        this.categoria = categoria;
    }

    public void setDataQueFoiVisto(LocalDate dataQueFoiVisto) {
        this.dataQueFoiVisto = dataQueFoiVisto;
    }
}
