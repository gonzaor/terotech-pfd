package MODELO;

public class Subcomision {
    private int idSubcom;          // Identificador único de la subcomisión
    private String descripcion;    // Descripción de la subcomisión

    // Constructor
    public Subcomision(int idSubcom, String descripcion) {
        this.idSubcom = idSubcom;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdSubcom() {
        return idSubcom;
    }

    public void setIdSubcom(int idSubcom) {
        this.idSubcom = idSubcom;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Subcomision{" +
                "idSubcom=" + idSubcom +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}