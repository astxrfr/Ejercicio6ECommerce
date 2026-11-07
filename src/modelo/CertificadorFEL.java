import java.util.List;

public class CertificadorFEL extends ApiIntegracion implements Documentable, Auditable {
    public CertificadorFEL(String id, String nombre, String proveedor, String url, EstadoConexion estadoConexion, double latenciaPromedioMs) {
        super(id, nombre, proveedor, url, estadoConexion, latenciaPromedioMs);
    }

    @Override
    public void registrarEvento(String evento) {
        
    }

    @Override
    public List<String> getHistorial() {
        return List.of();
    }

    @Override
    public String generarDocumento() {
        return "";
    }

    @Override
    public String generarDocumento(String formato) {
        return "";
    }
}
