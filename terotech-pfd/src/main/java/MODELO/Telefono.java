package MODELO;

public class Telefono {
    private int idTelefono;
    private int idUsuario;
    private String pais;
    private String localidad;
    private long numero;
    private String tipoTelefono;

    // Constructor vacío
    public Telefono() {}

    // Constructor completo
    public Telefono(int idTelefono, int idUsuario, String pais, String localidad, long numero, String tipoTelefono) {
        this.idTelefono = idTelefono;
        this.idUsuario = idUsuario;
        this.pais = pais;
        this.localidad = localidad;
        this.numero = numero;
        this.tipoTelefono = tipoTelefono;
    }

    // Getters y Setters
    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(String tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "idTelefono=" + idTelefono +
                ", idUsuario=" + idUsuario +
                ", pais='" + pais + '\'' +
                ", localidad='" + localidad + '\'' +
                ", numero=" + numero +
                ", tipoTelefono='" + tipoTelefono + '\'' +
                '}';
    }
}
