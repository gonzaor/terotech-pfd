package MODELO;

import java.util.Date;
import java.util.List;

public class Pago {

    private Long id;
    private Date fecha;
    private Float monto;
    private Usuario usuario;

    public Pago(Date fecha, Float monto, Usuario usuario) {
        this.fecha = fecha;
        this.monto = monto;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
