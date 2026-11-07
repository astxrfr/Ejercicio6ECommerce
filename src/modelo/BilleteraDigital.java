package modelo;

public class BilleteraDigital extends PasarelaPago {
    private final double limiteTransaccion;

    public BilleteraDigital(String id, String nombre, String proveedor, String url,
                            EstadoConexion estado, double latenciaMs, double comision,
                            double limiteTransaccion) {
        super(id, nombre, proveedor, url, estado, latenciaMs, comision);
        this.limiteTransaccion = limiteTransaccion;
    }

    @Override
    public boolean cobrar(double monto) {
        if (monto <= 0 || monto > limiteTransaccion) {
            registrarEvento(String.format("Cobro rechazado: %.2f fuera del límite (%.2f)", monto, limiteTransaccion));
            return false;
        }
        registrarEvento(String.format("Cobro con billetera por %.2f", monto));
        return true;
    }

    @Override
    public boolean reintegrar(double monto) {
        if (monto <= 0) return false;
        registrarEvento(String.format("Reintegro inmediato de %.2f a la billetera", monto));
        return true;
    }

    public double getLimiteTransaccion() { return limiteTransaccion; }

    @Override
    protected String getParametrosEspecificos() {
        return super.getParametrosEspecificos() + "\nLímite por transacción: " + limiteTransaccion;
    }
}
