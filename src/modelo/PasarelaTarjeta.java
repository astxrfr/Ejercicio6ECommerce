package modelo;

public class PasarelaTarjeta extends PasarelaPago {
    private final String ultimos4;
    private final boolean soporta3DS;

    public PasarelaTarjeta(String id, String nombre, String proveedor, String url,
                           EstadoConexion estado, double latenciaMs, double comision,
                           String ultimos4, boolean soporta3DS) {
        super(id, nombre, proveedor, url, estado, latenciaMs, comision);
        this.ultimos4 = ultimos4;
        this.soporta3DS = soporta3DS;
    }

    @Override
    public boolean cobrar(double monto) {
        if (monto <= 0) { registrarEvento("Cobro rechazado: monto inválido"); return false; }
        double total = monto * (1 + getComisionPorcentaje() / 100);
        registrarEvento(String.format("Cobro con tarjeta *%s por %.2f (incluye comisión)", ultimos4, total));
        return true;
    }

    @Override
    public boolean reintegrar(double monto) {
        if (monto <= 0) return false;
        registrarEvento(String.format("Reintegro de %.2f a la tarjeta *%s", monto, ultimos4));
        return true;
    }

    public String getUltimos4() { return ultimos4; }
    public boolean isSoporta3DS() { return soporta3DS; }

    @Override
    protected String getParametrosEspecificos() {
        return super.getParametrosEspecificos() + "\nTarjeta terminada en: " + ultimos4
                + "\nSoporta 3D Secure: " + (soporta3DS ? "sí" : "no");
    }
}
