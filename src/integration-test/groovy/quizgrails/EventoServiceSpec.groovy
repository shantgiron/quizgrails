package quizgrails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EventoServiceSpec extends Specification {

    EventoService eventoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Evento(...).save(flush: true, failOnError: true)
        //new Evento(...).save(flush: true, failOnError: true)
        //Evento evento = new Evento(...).save(flush: true, failOnError: true)
        //new Evento(...).save(flush: true, failOnError: true)
        //new Evento(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //evento.id
    }

    void "test get"() {
        setupData()

        expect:
        eventoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Evento> eventoList = eventoService.list(max: 2, offset: 2)

        then:
        eventoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        eventoService.count() == 5
    }

    void "test delete"() {
        Long eventoId = setupData()

        expect:
        eventoService.count() == 5

        when:
        eventoService.delete(eventoId)
        sessionFactory.currentSession.flush()

        then:
        eventoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Evento evento = new Evento()
        eventoService.save(evento)

        then:
        evento.id != null
    }
}
