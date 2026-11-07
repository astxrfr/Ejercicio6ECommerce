package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Historial de eventos. Se usa por composición en las clases Auditable
 * para no duplicar la lista de eventos en cada una.
 */
public class RegistroAuditoria {
    private final List<String> eventos = new ArrayList<>();

    public void registrar(String evento) {
        eventos.add(evento);
    }

    public List<String> getEventos() {
        return Collections.unmodifiableList(eventos);
    }
}
