package modelo;

public interface Documentable {
    String generarDocumento();

    default String generarDocumento(String formato) {
        String xml = generarDocumento();
        if ("TEXTO".equalsIgnoreCase(formato)) {
            return xml.replaceAll("<[^>]+>", " ").replaceAll("\\s+", " ").trim();
        }
        return xml;
    }
}
