package quizgrails

import grails.gorm.services.Service

@Service(Evento)
interface EventoService {

    Evento get(Serializable id)

    List<Evento> list(Map args)

    Long count()

    void delete(Serializable id)

    Evento save(Evento evento)

}