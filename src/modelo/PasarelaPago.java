package modelo;

import java.util.List;

public abstract class PasarelaPago extends ApiIntegracion implements Transaccional, Auditable {
    private double comisionPorcentaje;
    private final RegistroAuditoria auditoria = new RegistroAuditoria();

    protected PasarelaPago(String id, String nombre, String proveedor, String url,
                           EstadoConexion estado, double latenciaMs, double comisionPorcentaje) {
        super(id, nombre, proveedor, url, estado, latenciaMs);
        this.comisionPorcentaje = comisionPorcentaje;
    }

    @Override
    public String getTipo() { return "PASARELA_PAGO"; }

    @Override
    public boolean cobrar(double monto, String moneda) {
        registrarEvento("Moneda solicitada: " + moneda);
        return cobrar(monto);
    }

    @Override
    public void registrarEvento(String evento) { auditoria.registrar(getId() + ": " + evento); }

    @Override
    public List<String> getHistorial() { return auditoria.getEventos(); }

    public double getComisionPorcentaje() { return comisionPorcentaje; }
    public void setComisionPorcentaje(double c) { this.comisionPorcentaje = c; }

    @Override
    protected String getParametrosEspecificos() {
        return "Comisión: " + comisionPorcentaje + "%";
    }
}
