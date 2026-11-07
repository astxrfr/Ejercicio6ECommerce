package modelo;

/** Courier con cobertura nacional y tarifa por kilogramo. */
public class CourierNacional extends ProveedorLogistico {
    private final double tarifaPorKg;
    private final String cobertura;

    public CourierNacional(String id, String nombre, String proveedor, String url,
                           EstadoConexion estado, double latenciaMs,
                           double tarifaPorKg, String cobertura) {
        super(id, nombre, proveedor, url, estado, latenciaMs);
        this.tarifaPorKg = tarifaPorKg;
        this.cobertura = cobertura;
    }

    @Override
    public double cotizar(double pesoKg, double distanciaKm) {
        return pesoKg * tarifaPorKg;
    }

    public double getTarifaPorKg() { return tarifaPorKg; }
    public String getCobertura() { return cobertura; }

    @Override
    protected String getParametrosEspecificos() {
        return "Tarifa por kg: " + tarifaPorKg + "\nCobertura: " + cobertura;
    }
}
