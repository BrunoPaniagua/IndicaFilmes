package com.brunopaniagua.IndicaFilmes.repository;

import com.brunopaniagua.IndicaFilmes.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeModel, Long> {
}
