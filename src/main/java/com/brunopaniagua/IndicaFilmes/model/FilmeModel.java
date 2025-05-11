package com.brunopaniagua.IndicaFilmes.model;

import com.brunopaniagua.IndicaFilmes.enums.CategoriaFilme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table (name = "filme")
@NoArgsConstructor
@AllArgsConstructor
public class FilmeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "categoria")
    private CategoriaFilme categoria;

    @Column(name = "dataQueFoiVisto")
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
