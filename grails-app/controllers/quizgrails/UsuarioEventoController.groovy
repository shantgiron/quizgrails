package quizgrails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UsuarioEventoController {

    UsuarioEventoService usuarioEventoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('ROLE_ADMIN')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond usuarioEventoService.list(params), model:[usuarioEventoCount: usuarioEventoService.count()]
    }


    @Secured('ROLE_ADMIN')
    def show(Long id) {
        respond usuarioEventoService.get(id)
    }

    @Secured('ROLE_ADMIN')
    def create() {
        respond new UsuarioEvento(params)
    }

    @Secured('ROLE_ADMIN')
    def save(UsuarioEvento usuarioEvento) {
        if (usuarioEvento == null) {
            notFound()
            return
        }

        try {
            usuarioEventoService.save(usuarioEvento)
        } catch (ValidationException e) {
            respond usuarioEvento.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioEvento.label', default: 'UsuarioEvento'), usuarioEvento.id])
                redirect usuarioEvento
            }
            '*' { respond usuarioEvento, [status: CREATED] }
        }
    }

    @Secured('ROLE_ADMIN')
    def edit(Long id) {
        respond usuarioEventoService.get(id)
    }

    @Secured('ROLE_ADMIN')
    def update(UsuarioEvento usuarioEvento) {
        if (usuarioEvento == null) {
            notFound()
            return
        }

        try {
            usuarioEventoService.save(usuarioEvento)
        } catch (ValidationException e) {
            respond usuarioEvento.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioEvento.label', default: 'UsuarioEvento'), usuarioEvento.id])
                redirect usuarioEvento
            }
            '*'{ respond usuarioEvento, [status: OK] }
        }
    }

    @Secured('ROLE_ADMIN')
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        usuarioEventoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioEvento.label', default: 'UsuarioEvento'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioEvento.label', default: 'UsuarioEvento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
