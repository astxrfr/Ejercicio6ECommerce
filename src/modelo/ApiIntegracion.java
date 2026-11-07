import java.util.Objects;

public abstract class ApiIntegracion implements Comparable<ApiIntegracion> {
    private String id;
    private String nombre;
    private String proveedor;
    private String url;
    private EstadoConexion estadoConexion;
    private double latenciaPromedioMs;

    public ApiIntegracion(String id, String nombre, String proveedor, String url, EstadoConexion estadoConexion, double latenciaPromedioMs) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.url = url;
        this.estadoConexion = estadoConexion;
        this.latenciaPromedioMs = latenciaPromedioMs;
    }

    @Override
    public int compareTo(ApiIntegracion o) {
        return Double.compare(this.latenciaPromedioMs, o.latenciaPromedioMs);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ApiIntegracion other)) return false;
        return Objects.equals(other.id, this.id);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getUrl() {
        return url;
    }

    public EstadoConexion getEstadoConexion() {
        return estadoConexion;
    }

    public double getLatenciaPromedioMs() {
        return latenciaPromedioMs;
    }
}
