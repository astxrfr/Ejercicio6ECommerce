package modelo;

/** Courier internacional: suma aranceles a la cotización e incluye datos aduaneros en la guía. */
public class CourierInternacional extends ProveedorLogistico {
    private final double tarifaPorKg;
    private final double arancelPorcentaje;
    private final String paises;

    public CourierInternacional(String id, String nombre, String proveedor, String url,
                                EstadoConexion estado, double latenciaMs,
                                double tarifaPorKg, double arancelPorcentaje, String paises) {
        super(id, nombre, proveedor, url, estado, latenciaMs);
        this.tarifaPorKg = tarifaPorKg;
        this.arancelPorcentaje = arancelPorcentaje;
        this.paises = paises;
    }

    @Override
    public double cotizar(double pesoKg, double distanciaKm) {
        double base = pesoKg * tarifaPorKg;
        return base * (1 + arancelPorcentaje / 100);
    }

    /** Sobrescribe la guía base agregando la sección de aduana. */
    @Override
    public String generarDocumento() {
        return "<guiaEnvio><proveedor>" + getNombre() + "</proveedor><numero>" + siguienteGuia()
                + "</numero><aduana><arancel>" + arancelPorcentaje + "%</arancel><paises>"
                + paises + "</paises></aduana></guiaEnvio>";
    }

    public double getArancelPorcentaje() { return arancelPorcentaje; }
    public String getPaises() { return paises; }

    @Override
    protected String getParametrosEspecificos() {
        return "Tarifa por kg: " + tarifaPorKg + "\nArancel: " + arancelPorcentaje
                + "%\nPaíses: " + paises;
    }
}
