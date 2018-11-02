package quizgrails

class Evento {

    String nombre;
    String descripcion;
    Date fechaInicio;
    Date fechaFin;
    int edadPermitida;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        nombre unique: true;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }

}
