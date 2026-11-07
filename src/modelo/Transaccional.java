package modelo;

public interface Transaccional {
    boolean cobrar(double monto);
    /** Overloading: cobro indicando la moneda. */
    boolean cobrar(double monto, String moneda);
    boolean reintegrar(double monto);
}
