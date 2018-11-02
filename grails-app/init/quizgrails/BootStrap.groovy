package quizgrails

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        new ProductAnnouncement(message: 'Test').save()

        def userRole = new Role('ROLE_USER').save()

        def me = new User('shanty@hotmail.com', 'user').save()

        UserRole.create me, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }

    def destroy = {
    }

}