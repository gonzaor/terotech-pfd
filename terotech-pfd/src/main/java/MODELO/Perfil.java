package MODELO;
import java.util.List;

public class Perfil {

    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private List<Usuario> usuarios;

    public Perfil(String nombre, String descripcion, Boolean estado, List<Usuario> usuarios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.usuarios = usuarios;
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

    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}

