package peliculas.peliculas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.service.PeliculaService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.constraints.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;

    //http://localhost:8080/peliculas
    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        log.info("GET /peliculas");
        log.info("Retornando todas las peliculas");
        List<EntityModel<Pelicula>> peliculasResources = peliculas.stream()
            .map( pelicula -> EntityModel.of(pelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculasResources, linkTo.withRel("peliculas"));

        return resources;
    }
    /*public List<Pelicula> getAllPeliculas(){
        log.info("GET /peliculas");
        log.info("Retornado todas las peliculas");
        return peliculaService.getAllPeliculas();
    }*/
    
    //http://localhost:8080/peliculas/1
    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);

        if (pelicula.isPresent()) {
            return EntityModel.of(pelicula.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));
        } else {
            throw new PeliculaNotFoundException("Pelicula not found with id: " + id);
        }
    }
    /*public ResponseEntity<Object> getPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        if (pelicula.isEmpty()) {
            log.error("No se encontró la pelicula con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el estudiante con ID " + id));
        }
        return ResponseEntity.ok(pelicula);
    }*/

    /*@PostMapping
    public Pelicula creatPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.creatPelicula(pelicula);
    }*/

    @PostMapping
    public EntityModel<Pelicula> createStudent(@Validated @RequestBody Pelicula pelicula) {
        Pelicula createdPelicula = peliculaService.createPelicula(pelicula);
            return EntityModel.of(createdPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createdPelicula.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));

    }
    /*public ResponseEntity<Object> createPelicula(@Validated @RequestBody Pelicula pelicula) {
        Pelicula createdPelicula = peliculaService.createPelicula(pelicula);
        if (createdPelicula == null) {
            log.error("Error al crear la pelicula {}", pelicula);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear la pelicula"));
        }
        return ResponseEntity.ok(createdPelicula);
    }*/

    @PutMapping("/{id}")
    public EntityModel<Pelicula> updateStudent(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Pelicula updatedPelicula = peliculaService.updatePelicula(id, pelicula);
        return EntityModel.of(updatedPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("all-peliculas"));

    }
    /*public Pelicula updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        return peliculaService.updatePelicula(id, pelicula);
    }*/

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id){
        peliculaService.deletePelicula(id);
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
