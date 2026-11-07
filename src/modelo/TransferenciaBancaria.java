package modelo;

public class TransferenciaBancaria extends PasarelaPago {
    private final String bancoDestino;
    private final int diasLiquidacion;

    public TransferenciaBancaria(String id, String nombre, String proveedor, String url,
                                 EstadoConexion estado, double latenciaMs, double comision,
                                 String bancoDestino, int diasLiquidacion) {
        super(id, nombre, proveedor, url, estado, latenciaMs, comision);
        this.bancoDestino = bancoDestino;
        this.diasLiquidacion = diasLiquidacion;
    }

    @Override
    public boolean cobrar(double monto) {
        if (monto <= 0) { registrarEvento("Cobro rechazado: monto inválido"); return false; }
        registrarEvento(String.format("Transferencia de %.2f a %s pendiente de liquidar en %d día(s)",
                monto, bancoDestino, diasLiquidacion));
        return true;
    }

    @Override
    public boolean reintegrar(double monto) {
        if (monto <= 0) return false;
        registrarEvento(String.format("Reintegro de %.2f programado (plazo %d día(s))", monto, diasLiquidacion));
        return true;
    }

    public String getBancoDestino() { return bancoDestino; }
    public int getDiasLiquidacion() { return diasLiquidacion; }

    @Override
    protected String getParametrosEspecificos() {
        return super.getParametrosEspecificos() + "\nBanco destino: " + bancoDestino
                + "\nDías de liquidación: " + diasLiquidacion;
    }
}
