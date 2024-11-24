package MODELO;
import java.util.Date;
import java.util.List;


public class Usuario {
    private int id;
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
    private String estado;
    private List<Perfil> perfiles;

    public int getId(int id) {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nombrePerfil;

    public String getNombrePerfil() {
        return this.nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private Perfil perfil;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public List<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public tipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(tipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public CategoriaSocio getCategoriaSocio() {
        return categoriaSocio;
    }

    public void setCategoriaSocio(CategoriaSocio categoriaSocio) {
        this.categoriaSocio = categoriaSocio;
    }

    public boolean isDificultadAuditiva() {
        return dificultadAuditiva;
    }

    public void setDificultadAuditiva(boolean dificultadAuditiva) {
        this.dificultadAuditiva = dificultadAuditiva;
    }

    public boolean isManejaLenguajeDeSenias() {
        return manejaLenguajeDeSenias;
    }

    public void setManejaLenguajeDeSenias(boolean manejaLenguajeDeSenias) {
        this.manejaLenguajeDeSenias = manejaLenguajeDeSenias;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

//    hay que ver que onda entre esto y la base de datos
//    deberian tener casi los mismos campos (lo necesito para terminar UsuarioDAO.agregarUsuario())

    public Subcomision getSubcomision() {
        return subcomision;
    }

    public void setSubcomision(Subcomision subcomision) {
        this.subcomision = subcomision;
    }

    public void registrar() { }
    public void modificar() { }
    public void eliminar() { }
    public boolean login() { return true; }
    public void modificarDatosPropios() {  }
}
