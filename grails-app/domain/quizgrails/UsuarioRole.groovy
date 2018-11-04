package quizgrails

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UsuarioRole implements Serializable{

    private static final long serialVersionUID = 1

    Usuario user
    Role role

    @Override
    boolean equals(other) {
        if (other instanceof UsuarioRole) {
            other.userId == user?.id && other.roleId == role?.id
        }
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        if (user) {
            hashCode = HashCodeHelper.updateHash(hashCode, user.id)
        }
        if (role) {
            hashCode = HashCodeHelper.updateHash(hashCode, role.id)
        }
        hashCode
    }

    static UsuarioRole get(long userId, long roleId) {
        criteriaFor(userId, roleId).get()
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long roleId) {
        UsuarioRole.where {
            user == Usuario.load(userId) &&
                    role == Role.load(roleId)
        }
    }

    static UsuarioRole create(Usuario user, Role role, boolean flush = false) {
        def instance = new UsuarioRole(user: user, role: role)
        instance.save(flush: flush)
        instance
    }

    static boolean remove(Usuario u, Role r) {
        if (u != null && r != null) {
            UsuarioRole.where { user == u && role == r }.deleteAll()
        }
    }

    static int removeAll(Usuario u) {
        u == null ? 0 : UsuarioRole.where { user == u }.deleteAll() as int
    }

    static int removeAll(Role r) {
        r == null ? 0 : UsuarioRole.where { role == r }.deleteAll() as int
    }

    static constraints = {
        role validator: { Role r, UsuarioRole ur ->
            if (ur.user?.id) {
                UsuarioRole.withNewSession {
                    if (UsuarioRole.exists(ur.user.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['user', 'role']
        version false
    }
}
