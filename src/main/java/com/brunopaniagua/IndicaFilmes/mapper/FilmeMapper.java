package com.brunopaniagua.IndicaFilmes.mapper;

import com.brunopaniagua.IndicaFilmes.dto.FilmeDTO;
import com.brunopaniagua.IndicaFilmes.model.FilmeModel;
import org.springframework.stereotype.Component;

@Component
public class FilmeMapper {

    public FilmeModel map(FilmeDTO filmeDTO) {
        FilmeModel filmeModel = new FilmeModel();
        filmeModel.setId(filmeDTO.getId());
        filmeModel.setTitulo(filmeDTO.getTitulo());
        filmeModel.setCategoria(filmeDTO.getCategoria());
        filmeModel.setDataQueFoiVisto(filmeDTO.getDataQueFoiVisto());
        return filmeModel;
    }

    public FilmeDTO map(FilmeModel filmeModel) {
        FilmeDTO filmeDTO = new FilmeDTO();
        filmeDTO.setId(filmeModel.getId());
        filmeDTO.setTitulo(filmeModel.getTitulo());
        filmeDTO.setCategoria(filmeModel.getCategoria());
        filmeDTO.setDataQueFoiVisto(filmeModel.getDataQueFoiVisto());
        return filmeDTO;
    }
}
