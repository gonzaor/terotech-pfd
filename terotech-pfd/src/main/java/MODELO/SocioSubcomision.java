package MODELO;

public class SocioSubcomision {

        private int idSocio;       // Identificador del socio
        private int idSubcom;      // Identificador de la subcomisión

        // Constructor
        public SocioSubcomision(int idSocio, int idSubcom) {
            this.idSocio = idSocio;
            this.idSubcom = idSubcom;
        }

        // Getters y Setters
        public int getIdSocio() {
            return idSocio;
        }

        public void setIdSocio(int idSocio) {
            this.idSocio = idSocio;
        }

        public int getIdSubcom() {
            return idSubcom;
        }

        public void setIdSubcom(int idSubcom) {
            this.idSubcom = idSubcom;
        }
    }

