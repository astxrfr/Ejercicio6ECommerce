package modelo;

import java.util.List;

public class CertificadorFEL extends ApiIntegracion implements Documentable, Auditable {
    private final String nitEmisor;
    private final String serie;
    private int facturasEmitidas = 0;
    private final RegistroAuditoria auditoria = new RegistroAuditoria();

    public CertificadorFEL(String id, String nombre, String proveedor, String url,
                           EstadoConexion estado, double latenciaMs,
                           String nitEmisor, String serie) {
        super(id, nombre, proveedor, url, estado, latenciaMs);
        this.nitEmisor = nitEmisor;
        this.serie = serie;
    }

    @Override
    public String getTipo() { return "CERTIFICADOR_FEL"; }

    @Override
    public String generarDocumento() {
        facturasEmitidas++;
        registrarEvento("Factura " + serie + "-" + facturasEmitidas + " certificada");
        return "<factura><emisorNit>" + nitEmisor + "</emisorNit><serie>" + serie
                + "</serie><numero>" + facturasEmitidas + "</numero></factura>";
    }

    @Override
    public void registrarEvento(String evento) { auditoria.registrar(getId() + ": " + evento); }

    @Override
    public List<String> getHistorial() { return auditoria.getEventos(); }

    public String getNitEmisor() { return nitEmisor; }
    public String getSerie() { return serie; }

    @Override
    protected String getParametrosEspecificos() {
        return "NIT emisor: " + nitEmisor + "\nSerie: " + serie;
    }
}
