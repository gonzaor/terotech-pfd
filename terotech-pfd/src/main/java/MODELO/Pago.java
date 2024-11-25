package MODELO;

import java.util.Date;

public class Pago {

    private int id;
    private Date fecha;
    private Float monto;
    private Usuario usuario;
    private String tipoPago;
    private String formaPago;



    public Pago(int id, Date fecha, Float monto, Usuario usuario, String tipoPago, String formaPago) {
        this.fecha = fecha;
        this.monto = monto;
        this.usuario = usuario;
        this.tipoPago = tipoPago;
        this.formaPago = formaPago;
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    public String getFormaPago() { return formaPago; }

    public String getTipoPago() { return tipoPago; }

    public void setTipoPago(String tipoPago) { this.tipoPago = tipoPago; }

    public void setFormaPago(String formaPago) {this.formaPago = formaPago; }


}
