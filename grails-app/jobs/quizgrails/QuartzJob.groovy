package quizgrails

import java.util.concurrent.TimeUnit

class QuartzJob {


    EventoService eventoService


    static triggers = {
      simple repeatInterval: 60000l

    }

    def execute() {

        List<Evento> Eventos = new ArrayList<Evento>()

        def query = "from Evento WHERE fecha_inicio = CURRENT_TIMESTAMP()-1"
        Eventos = Evento.findAll(query)


        for (int i = 0; i < Eventos.size(); i++) {
            Evento ev = Eventos.get(i)
            Date now = new Date()
            def difference = now.time - ev.fechaInicio.time;
            println difference
            if (difference < TimeUnit.MINUTES.toMillis(5)) {
                println "El Evento" + ev.nombre + " esta a punto de comenzar."
            }
        }



    }
}
