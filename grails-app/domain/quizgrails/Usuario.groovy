package quizgrails

import java.time.format.DateTimeFormatter

class Usuario {

    String nombre;
    String apellido;
    String cedula;
    String email;
    Date fechaNacimiento;
    Date dateCreated;
    Date lastUpdated;

    static constraints = {
        email unique: true;
    }


    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }
}
