package MODELO;

public class Socio {

    private int idSocio;
    private Usuario usuario;
    private boolean dificultadAuditiva;
    private boolean usoLengSenas;

    public Socio(int idSocio, Usuario usuario, boolean dificultadAuditiva, boolean usoLengSenas){
        this.idSocio = idSocio;
        this.usuario = usuario;
        this.dificultadAuditiva = dificultadAuditiva;
        this.usoLengSenas = usoLengSenas;
    }

    public int getIdSocio() {return idSocio; }

    public void setIdSocio(int idSocio) {this.idSocio = idSocio; }

    public Usuario getUsuario() {return usuario; }

    public void setUsuario(Usuario usuario) {this.usuario = usuario; }

    public boolean isDificultadAuditiva() {return dificultadAuditiva; }

    public void setDificultadAuditiva(boolean dificultadAuditiva) {this.dificultadAuditiva = dificultadAuditiva; }

    public boolean isUsoLengSenas() {return usoLengSenas; }

    public void setUsoLengSenas(boolean usoLengSenas) {this.usoLengSenas = usoLengSenas; }
}
