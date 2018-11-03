package quizgrails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EventoController {

    EventoService eventoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured('ROLE_ADMIN')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond eventoService.list(params), model:[eventoCount: eventoService.count()]
    }

    @Secured('ROLE_ADMIN')
    def show(Long id) {
        respond eventoService.get(id)
    }

    @Secured('ROLE_ADMIN')
    def create() {
        respond new Evento(params)
    }

    @Secured('ROLE_ADMIN')
    def save(Evento evento) {
        if (evento == null) {
            notFound()
            return
        }

        try {
            eventoService.save(evento)
        } catch (ValidationException e) {
            respond evento.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect evento
            }
            '*' { respond evento, [status: CREATED] }
        }
    }

    @Secured('ROLE_ADMIN')
    def edit(Long id) {
        respond eventoService.get(id)
    }

    @Secured('ROLE_ADMIN')
    def update(Evento evento) {
        if (evento == null) {
            notFound()
            return
        }

        try {
            eventoService.save(evento)
        } catch (ValidationException e) {
            respond evento.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'evento.label', default: 'Evento'), evento.id])
                redirect evento
            }
            '*'{ respond evento, [status: OK] }
        }
    }

    @Secured('ROLE_ADMIN')
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        eventoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'evento.label', default: 'Evento'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evento.label', default: 'Evento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
