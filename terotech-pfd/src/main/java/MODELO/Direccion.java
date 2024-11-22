package MODELO;

public class Direccion {
    private int idDireccion;
    private String calle;
    private int nroPuerta;
    private String apto;

    // Getters y Setters
    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNroPuerta() {
        return nroPuerta;
    }

    public void setNroPuerta(int nroPuerta) {
        this.nroPuerta = nroPuerta;
    }

    public String getApto() {
        return apto;
    }

    public void setApto(String apto) {
        this.apto = apto;
    }
}
