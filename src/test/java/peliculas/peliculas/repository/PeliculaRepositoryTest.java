package peliculas.peliculas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import peliculas.peliculas.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void guardarPeliculaTest() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Todos los perros se van al cielo");

        Pelicula resultado = peliculaRepository.save(pelicula);

        assertNotNull(resultado.getId());
        assertEquals("Todos los perros se van al cielo", resultado.getTitulo());
    }

}