package modelo;

public abstract class ApiIntegracion implements Comparable<ApiIntegracion> {
    private final String id;
    private String nombre;
    private String proveedor;
    private String url;
    private EstadoConexion estado;
    private double latenciaMs;

    protected ApiIntegracion(String id, String nombre, String proveedor, String url,
                             EstadoConexion estado, double latenciaMs) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El identificador es obligatorio");
        }
        this.id = id.trim().toUpperCase();
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.url = url;
        this.estado = estado;
        setLatenciaMs(latenciaMs);
    }

    public abstract String getTipo();

    protected abstract String getParametrosEspecificos();

    public String getParametrosConfiguracion() {
        return "ID: " + id + "\nNombre: " + nombre + "\nTipo: " + getTipo()
                + "\nProveedor: " + proveedor + "\nURL: " + url
                + "\nEstado: " + estado + "\nLatencia promedio: " + latenciaMs + " ms\n"
                + getParametrosEspecificos();
    }

    public boolean estaActiva() {
        return estado != EstadoConexion.DESCONECTADA;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getProveedor() { return proveedor; }
    public String getUrl() { return url; }
    public EstadoConexion getEstado() { return estado; }
    public double getLatenciaMs() { return latenciaMs; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
    public void setUrl(String url) { this.url = url; }
    public void setEstado(EstadoConexion estado) { this.estado = estado; }

    public void setLatenciaMs(double latenciaMs) {
        if (latenciaMs < 0) {
            throw new IllegalArgumentException("La latencia no puede ser negativa");
        }
        this.latenciaMs = latenciaMs;
    }

    @Override
    public int compareTo(ApiIntegracion otra) {
        int cmp = Double.compare(this.latenciaMs, otra.latenciaMs);
        return cmp != 0 ? cmp : this.id.compareTo(otra.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiIntegracion)) return false;
        return id.equals(((ApiIntegracion) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-20s | %-16s | %-12s | %7.1f ms",
                id, nombre, getTipo(), estado, latenciaMs);
    }
}
