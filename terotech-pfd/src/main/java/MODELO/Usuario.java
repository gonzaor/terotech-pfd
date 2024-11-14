package MODELO;
import java.util.Date;
import java.util.List;
import MODELO.*;
import java.util.*;


public class Usuario {
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private String domicilio;
    private List<String> telefonos;
    private String email;
    private String contrasena;
    private tipoUsuario tipoUsuario;
    private CategoriaSocio categoriaSocio;
    private boolean dificultadAuditiva;
    private boolean manejaLenguajeDeSenias;
    private Subcomision subcomision;

    public void registrar() { }
    public void modificar() { }
    public void eliminar() { }
    public boolean login() { return true; }
    public void modificarDatosPropios() {  }
}
