package modelo;

public abstract class ProveedorLogistico extends ApiIntegracion implements Documentable {
    private int guiasEmitidas = 0;

    protected ProveedorLogistico(String id, String nombre, String proveedor, String url,
                                 EstadoConexion estado, double latenciaMs) {
        super(id, nombre, proveedor, url, estado, latenciaMs);
    }

    @Override
    public String getTipo() { return "LOGISTICA"; }

    public abstract double cotizar(double pesoKg, double distanciaKm);

    public String rastrear(String guia) {
        return "Paquete " + guia + " en tránsito (consultado a " + getNombre() + ")";
    }

    protected String siguienteGuia() {
        guiasEmitidas++;
        return "GUIA-" + getId() + "-" + guiasEmitidas;
    }

    @Override
    public String generarDocumento() {
        return "<guiaEnvio><proveedor>" + getNombre() + "</proveedor><numero>"
                + siguienteGuia() + "</numero></guiaEnvio>";
    }
}
