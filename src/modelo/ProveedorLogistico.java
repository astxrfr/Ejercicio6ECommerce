public abstract class ProveedorLogistico extends ApiIntegracion implements Documentable {
    public ProveedorLogistico(String id, String nombre, String proveedor, String url, EstadoConexion estadoConexion, double latenciaPromedioMs) {
        super(id, nombre, proveedor, url, estadoConexion, latenciaPromedioMs);
    }
}
