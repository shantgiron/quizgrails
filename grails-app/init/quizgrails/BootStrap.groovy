package quizgrails

class BootStrap {


    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def rolUsuario = new Role(authority: "ROLE_USER").save()

        def testUser = new Usuario(username: 'admin', password: 'admin').save()

        UsuarioRole.create(testUser, adminRole)
        UsuarioRole.create(testUser, rolUsuario)

        UsuarioRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
