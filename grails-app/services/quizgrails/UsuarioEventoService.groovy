package quizgrails

import grails.gorm.services.Service

@Service(UsuarioEvento)
interface UsuarioEventoService {

    UsuarioEvento get(Serializable id)

    List<UsuarioEvento> list(Map args)

    Long count()

    void delete(Serializable id)

    UsuarioEvento save(UsuarioEvento usuarioEvento)

}