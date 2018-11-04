package quizdegrails

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Usuario implements  Serializable{

    private static final long serialVersionUID = 1

    String nombre;
    String apellido;
    String cedula;
    String email;
    Date fechaNacimiento;

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired


    Set<Role> getAuthorities() {
        (UsuarioRole.findAllByUser(this) as List<UsuarioRole>)*.role as Set<Role>
    }

    static constraints = {
        nombre nullable: true, blank: true
        apellido nullable: true, blank: true
        cedula nullable: true, blank: true
        fechaNacimiento nullable: true, blank: true
        email nullable: true, blank: true
        username nullable: false, blank: false, unique: true
        password nullable: false, blank: false, password: true
        enabled display: false, editable: false
        accountExpired display: false, editable: false
        accountLocked display: false, editable: false
        passwordExpired display: false, editable: false
    }

    static mapping = {
        password column: '`password`'
    }


}
