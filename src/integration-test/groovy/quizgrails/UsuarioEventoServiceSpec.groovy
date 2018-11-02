package quizgrails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UsuarioEventoServiceSpec extends Specification {

    UsuarioEventoService usuarioEventoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UsuarioEvento(...).save(flush: true, failOnError: true)
        //new UsuarioEvento(...).save(flush: true, failOnError: true)
        //UsuarioEvento usuarioEvento = new UsuarioEvento(...).save(flush: true, failOnError: true)
        //new UsuarioEvento(...).save(flush: true, failOnError: true)
        //new UsuarioEvento(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //usuarioEvento.id
    }

    void "test get"() {
        setupData()

        expect:
        usuarioEventoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UsuarioEvento> usuarioEventoList = usuarioEventoService.list(max: 2, offset: 2)

        then:
        usuarioEventoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        usuarioEventoService.count() == 5
    }

    void "test delete"() {
        Long usuarioEventoId = setupData()

        expect:
        usuarioEventoService.count() == 5

        when:
        usuarioEventoService.delete(usuarioEventoId)
        sessionFactory.currentSession.flush()

        then:
        usuarioEventoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UsuarioEvento usuarioEvento = new UsuarioEvento()
        usuarioEventoService.save(usuarioEvento)

        then:
        usuarioEvento.id != null
    }
}
