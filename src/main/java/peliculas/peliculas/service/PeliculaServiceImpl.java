package peliculas.peliculas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id) {
        return peliculaRepository.findById(id);
    }
    
}