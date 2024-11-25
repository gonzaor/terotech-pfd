package MODELO;

import java.util.List;

public class Socio {

    private int idSocio;
    private Usuario usuario;
    private boolean dificultadAuditiva;
    private boolean usoLengSenas;
    private boolean participaEnSubComision;
    private List<Subcomision> subcomision ;



    public Socio(int idSocio, Usuario usuario, boolean dificultadAuditiva, boolean usoLengSenas, boolean participaEnSubComision, Subcomision subcomision ){
        this.idSocio = idSocio;
        this.usuario = usuario;
        this.dificultadAuditiva = dificultadAuditiva;
        this.usoLengSenas = usoLengSenas;
        this.participaEnSubComision = participaEnSubComision;
        this.subcomision = (List<Subcomision>) subcomision;
    }

    public int getIdSocio() {return idSocio; }

    public void setIdSocio(int idSocio) {this.idSocio = idSocio; }

    public Usuario getUsuario() {return usuario; }

    public void setUsuario(Usuario usuario) {this.usuario = usuario; }

    public boolean isDificultadAuditiva() {return dificultadAuditiva; }

    public void setDificultadAuditiva(boolean dificultadAuditiva) {this.dificultadAuditiva = dificultadAuditiva; }

    public boolean isUsoLengSenas() {return usoLengSenas; }

    public void setUsoLengSenas(boolean usoLengSenas) {this.usoLengSenas = usoLengSenas; }

    public Subcomision getSubcomision() {return (Subcomision) subcomision; }

    public void setSubcomision(Subcomision subcomision) {this.subcomision = (List<Subcomision>) subcomision; }

    public boolean isParticipaEnSubComision() {return participaEnSubComision; }

    public void setParticipaEnSubComision(boolean participaEnSubComision) {this.participaEnSubComision = participaEnSubComision; }
}
