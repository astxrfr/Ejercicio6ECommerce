package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import modelo.ApiIntegracion;
import modelo.Auditable;
import modelo.CatalogoIntegraciones;
import modelo.Documentable;
import modelo.Transaccional;

public class ControladorIntegraciones {
    private final CatalogoIntegraciones catalogo;

    public ControladorIntegraciones(CatalogoIntegraciones catalogo) {
        this.catalogo = catalogo;
    }

    public List<ApiIntegracion> listarActivas() { return catalogo.getActivas(); }

    public Optional<ApiIntegracion> buscarPorId(String id) { return catalogo.buscarPorId(id); }

    public List<ApiIntegracion> ordenarPorLatencia() { return catalogo.ordenadasPorLatencia(); }

    public String ejecutar(Transaccional t, double monto) {
        return t.cobrar(monto) ? "Cobro de " + monto + " aprobado" : "Cobro de " + monto + " rechazado";
    }

    public String ejecutar(Transaccional t, double monto, String moneda) {
        return t.cobrar(monto, moneda) ? "Cobro de " + monto + " " + moneda + " aprobado"
                                       : "Cobro de " + monto + " " + moneda + " rechazado";
    }

    public String ejecutar(Documentable d) { return d.generarDocumento(); }

    public String ejecutar(Auditable a) { return "Eventos registrados: " + a.getHistorial(); }

    public String procesarCobro(ApiIntegracion api, double monto) {
        if (api instanceof Transaccional t) return ejecutar(t, monto);
        return noSoportada("cobro", api);
    }

    public String procesarReintegro(ApiIntegracion api, double monto) {
        if (api instanceof Transaccional t) {
            return t.reintegrar(monto) ? "Reintegro de " + monto + " aprobado"
                                       : "Reintegro de " + monto + " rechazado";
        }
        return noSoportada("reintegro", api);
    }

    public String generarDocumento(ApiIntegracion api) {
        if (api instanceof Documentable d) return ejecutar(d);
        return noSoportada("documento", api);
    }

    public String consultarAuditoria(ApiIntegracion api) {
        if (api instanceof Auditable a) return ejecutar(a);
        return noSoportada("auditoría", api);
    }

    public List<String> simularCheckout(ApiIntegracion api) {
        List<String> resultado = new ArrayList<>();
        resultado.add("Cobro (Q100):     " + procesarCobro(api, 100));
        resultado.add("Reintegro (Q50):  " + procesarReintegro(api, 50));
        resultado.add("Documento:        " + generarDocumento(api));
        resultado.add("Auditoría:        " + consultarAuditoria(api));
        return resultado;
    }

    private String noSoportada(String operacion, ApiIntegracion api) {
        return "Operación '" + operacion + "' no soportada por " + api.getNombre() + ": se ignora.";
    }
}
