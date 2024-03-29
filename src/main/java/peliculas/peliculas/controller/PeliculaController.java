package peliculas.peliculas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    //http://localhost:8080/peliculas
    @GetMapping
    public List<Pelicula> getAllPeliculas(){
        return peliculaService.getAllPeliculas();
    }
    
    //http://localhost:8080/peliculas/1
    @GetMapping("/{id}")
    public Optional<Pelicula> getPeliculaById(@PathVariable Long id) {
        return peliculaService.getPeliculaById(id);
    }
}
