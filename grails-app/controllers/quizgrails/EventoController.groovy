package quizgrails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EventoController {

    EventoService eventoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond eventoService.list(params), model:[eventoCount: eventoService.count()]
    }

    def show(Long id) {
        respond eventoService.get(id)
    }

    def create() {
        respond new Evento(params)
    }

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

    def edit(Long id) {
        respond eventoService.get(id)
    }

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
