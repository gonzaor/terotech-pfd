package MODELO;
import java.util.List;

public class Funcionalidad {
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private List<Perfil> perfiles;

    public Funcionalidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
}