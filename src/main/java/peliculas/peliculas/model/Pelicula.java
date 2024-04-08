package peliculas.peliculas.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pel")
    private Long id;
    @Column(name= "titulo")
    private String titulo;
    @Column(name= "anio")
    private Long anio;
    @Column(name= "director")
    private String director;
    @Column(name= "genero")
    private String genero;
    @Column(name= "sinopsis")
    private String sinopsis;

    //Getters and setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getAnio() {
        return anio;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    } 
}