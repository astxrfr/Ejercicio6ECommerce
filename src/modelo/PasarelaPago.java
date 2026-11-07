public abstract class PasarelaPago extends ApiIntegracion implements Transaccional, Auditable {
    public PasarelaPago(String id, String nombre, String proveedor, String url, EstadoConexion estadoConexion, double latenciaPromedioMs) {
        super(id, nombre, proveedor, url, estadoConexion, latenciaPromedioMs);
    }
}
