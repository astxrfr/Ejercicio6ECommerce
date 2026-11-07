package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Modelo: contiene la ÚNICA lista polimórfica del sistema. */
public class CatalogoIntegraciones {
    private final List<ApiIntegracion> integraciones = new ArrayList<>();

    /** Agrega una integración; devuelve false si es nula o el id ya existe. */
    public boolean agregar(ApiIntegracion api) {
        if (api == null || integraciones.contains(api)) return false;
        return integraciones.add(api);
    }

    public List<ApiIntegracion> getTodas() {
        return Collections.unmodifiableList(integraciones);
    }

    public List<ApiIntegracion> getActivas() {
        List<ApiIntegracion> activas = new ArrayList<>();
        for (ApiIntegracion api : integraciones) {
            if (api.estaActiva()) activas.add(api);
        }
        return activas;
    }

    public Optional<ApiIntegracion> buscarPorId(String id) {
        if (id == null) return Optional.empty();
        String buscado = id.trim().toUpperCase();
        return integraciones.stream().filter(a -> a.getId().equals(buscado)).findFirst();
    }

    /** Copia ordenada por latencia (usa compareTo de ApiIntegracion). */
    public List<ApiIntegracion> ordenadasPorLatencia() {
        List<ApiIntegracion> copia = new ArrayList<>(integraciones);
        Collections.sort(copia);
        return copia;
    }

    public int tamanio() { return integraciones.size(); }
}
