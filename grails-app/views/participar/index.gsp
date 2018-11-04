<g:applyLayout name="base">
    <content tag="contenido">
        <div class="alert alert-primary">
            <div class="row">
                <div class="col-6">
                    <h5 class="mt-3">Eventos en que participas </h5>
                    <f:table collection="${eventosParticipando}" properties="['id', 'nombre']"/>
                </div>
                <div class="col-6">
                    <h5 class="mt-3">Eventos disponibles para ti</h5>
                    <g:if test="${eventosNoParticipando}">
                        <g:form action="agregarEvento" method="POST">
                            <select name="eventosId" class="custom-select" multiple>
                                <g:each in="${eventosNoParticipando}" var="evento">
                                    <option value="${evento.id}">${evento.nombre}</option>
                                </g:each>
                            </select>
                            <input type="hidden" name="id" value="${usuario.id}">
                            <g:submitButton name="agregarEvento" class="save btn btn-outline-info mt-3"
                                            value="${message(code: 'default.button.update.label')}"/>
                        </g:form>
                    </g:if>
                    <g:else>
                        <div class="alert alert-warning">
                            <i class="fas fa-exclamation-circle"></i> No hay ningun evento disponible
                        </div>
                    </g:else>
                </div>
            </div>
        </div>
    </content>
</g:applyLayout>