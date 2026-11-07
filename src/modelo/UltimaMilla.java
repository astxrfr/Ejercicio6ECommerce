package modelo;

/** Entregas de última milla: tarifa fija dentro de un radio de cobertura. */
public class UltimaMilla extends ProveedorLogistico {
    private final double radioKm;
    private final double tarifaFija;

    public UltimaMilla(String id, String nombre, String proveedor, String url,
                       EstadoConexion estado, double latenciaMs,
                       double radioKm, double tarifaFija) {
        super(id, nombre, proveedor, url, estado, latenciaMs);
        this.radioKm = radioKm;
        this.tarifaFija = tarifaFija;
    }

    @Override
    public double cotizar(double pesoKg, double distanciaKm) {
        return distanciaKm <= radioKm ? tarifaFija : -1;
    }

    public double getRadioKm() { return radioKm; }
    public double getTarifaFija() { return tarifaFija; }

    @Override
    protected String getParametrosEspecificos() {
        return "Radio de cobertura: " + radioKm + " km\nTarifa fija: " + tarifaFija;
    }
}
