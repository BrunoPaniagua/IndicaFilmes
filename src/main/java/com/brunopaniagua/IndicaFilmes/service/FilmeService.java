package com.brunopaniagua.IndicaFilmes.service;

import com.brunopaniagua.IndicaFilmes.dto.FilmeDTO;
import com.brunopaniagua.IndicaFilmes.mapper.FilmeMapper;
import com.brunopaniagua.IndicaFilmes.model.FilmeModel;
import com.brunopaniagua.IndicaFilmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FilmeMapper filmeMapper;

    public FilmeService(FilmeRepository filmeRepository, FilmeMapper filmeMapper) {
        this.filmeRepository = filmeRepository;
        this.filmeMapper = filmeMapper;
    }

    public FilmeDTO cadastrarFilme(FilmeDTO filmeDTO) {
        FilmeModel novoFilme = filmeMapper.map(filmeDTO);
        filmeRepository.save(novoFilme);
        return filmeMapper.map(novoFilme);
    }

    public List<FilmeDTO> listarFilmes() {
        List<FilmeModel> filmeList = filmeRepository.findAll();
        return filmeList.stream()
                .map(filmeMapper::map)
                .collect(Collectors.toList());
    }

    public FilmeDTO buscarFilmePorId(Long id) {
        Optional<FilmeModel> filme = filmeRepository.findById(id);
        return filme.map(filmeMapper::map).orElse(null);
    }

    public void removerFilme(Long id) {
        filmeRepository.deleteById(id);
    }

    public FilmeDTO atualizarFilme(Long id, FilmeDTO filmeDTO) {
        Optional<FilmeModel> optionalFilme = filmeRepository.findById(id);

        if (optionalFilme.isPresent()) {
            FilmeModel filme = optionalFilme.get();

            if (filmeDTO.getTitulo() != null) {
                filme.setTitulo(filmeDTO.getTitulo());
            }
            if (filmeDTO.getCategoria() != null) {
                filme.setCategoria(filmeDTO.getCategoria());
            }
            if (filmeDTO.getDataQueFoiVisto() != null) {
                filme.setDataQueFoiVisto(filmeDTO.getDataQueFoiVisto());
            }

            filmeRepository.save(filme);
            return filmeMapper.map(filme);
        }

        return null;
    }

}
