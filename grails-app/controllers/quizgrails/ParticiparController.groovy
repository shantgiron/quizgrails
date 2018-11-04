package quizgrails

import java.sql.Timestamp
import java.time.LocalDate
import java.time.Period

class ParticiparController {

    def springSecurityService
    def usuarioService

    def index() {
        Usuario usuario = Usuario.findByUsername(springSecurityService.getCurrentUser().username)

        List<Evento> eventosParticipando = usuario.evento != null ? usuario.evento.asList() : new ArrayList()
        List<Evento> eventosNoParticipando = new ArrayList()

        Date fechaNacimiento = new Date(usuario.fechaNacimiento.time)
        Calendar cal = Calendar.getInstance()
        cal.setTime(fechaNacimiento)

        int usuarioEdad = Period.between(LocalDate.of(cal.get(Calendar.YEAR), fechaNacimiento.month + 1, fechaNacimiento.day), LocalDate.now()).getYears()

        Evento.findAll().each { it ->
            if (!eventosParticipando.contains(it) && it.fechaInicio.date >= new Date().getDate() && usuarioEdad >= it.edadPermitida)
                eventosNoParticipando.add(it)
        }

        [usuario: usuario, eventosParticipando: eventosParticipando, eventosNoParticipando: eventosNoParticipando]
    }

    def agregarEvento() {
        Usuario usuario = Usuario.findById(params["id"])
        def eventosId = params["eventosId"]
        List<Evento> eventos = new ArrayList<>()

        eventosId.each { it ->
            eventos.add(Evento.findById(it))
        }

        usuario.evento.addAll(eventos)
        usuarioService.save(usuario)

        redirect(controller: 'participar', action: 'index')
    }
}