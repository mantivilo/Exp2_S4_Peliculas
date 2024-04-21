package peliculas.peliculas.controller;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import peliculas.peliculas.model.Pelicula;
import peliculas.peliculas.service.PeliculaServiceImpl;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaServiceImpl peliculaServicioMock;

    @Test
    public void obtenerTodosTest() throws Exception {

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("Ciudad de Dios");
        pelicula1.setId(1L);
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Viernes 13");
        pelicula2.setId(2L);
        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);
        when(peliculaServicioMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",Matchers.is("Ciudad de Dios")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",Matchers.is("Viernes 13")));
    }  
}
