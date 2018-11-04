package quizgrails

class Evento {

    String nombre;
    String descripcion;
    Date fechaInicio;
    Date fechaFin;
    int edadPermitida;
    Date dateCreated;
    Date lastUpdated;


    static belongsTo = [usuario: Usuario]
    static hasMany = [usuario: Usuario]

    static constraints = {
        nombre unique: true;
        fechaInicio nullable: false, blank: false
        fechaFin nullable: false, blank: false
        descripcion nullable: false, blank: false
        edadPermitida nullable: false, blank: false

    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }

}
