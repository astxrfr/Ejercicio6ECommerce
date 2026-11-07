import controlador.ControladorIntegraciones;
import java.util.Optional;
import modelo.*;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        CatalogoIntegraciones catalogo = new CatalogoIntegraciones();
        cargaInicial(catalogo);
        ControladorIntegraciones controlador = new ControladorIntegraciones(catalogo);
        VistaConsola vista = new VistaConsola();

        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();
            switch (opcion) {
                case 1 -> vista.mostrarLista("Integraciones activas", controlador.listarActivas());
                case 2 -> {
                    String id = vista.pedirTexto("ID a buscar: ");
                    Optional<ApiIntegracion> api = controlador.buscarPorId(id);
                    vista.mostrarMensaje(api.map(ApiIntegracion::getParametrosConfiguracion)
                            .orElse("No existe una integración con ID '" + id.trim() + "'."));
                }
                case 3 -> vista.mostrarLista("Catálogo ordenado por latencia", controlador.ordenarPorLatencia());
                case 4 -> {
                    String id = vista.pedirTexto("ID de la integración: ");
                    Optional<ApiIntegracion> api = controlador.buscarPorId(id);
                    if (api.isPresent()) vista.mostrarMensajes(controlador.simularCheckout(api.get()));
                    else vista.mostrarMensaje("No existe una integración con ID '" + id.trim() + "'.");
                }
                case 0 -> vista.mostrarMensaje("Hasta luego.");
                default -> vista.mostrarMensaje("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void cargaInicial(CatalogoIntegraciones c) {
        c.agregar(new PasarelaTarjeta("TARJ-001", "PagoSeguro GT", "PagoSeguro", "https://api.pagoseguro.example/v1",
                EstadoConexion.CONECTADA, 180, 3.5, "4421", true));
        c.agregar(new PasarelaTarjeta("TARJ-002", "CardLatam", "CardLatam", "https://api.cardlatam.example/v2",
                EstadoConexion.DEGRADADA, 240, 3.9, "9087", false));
        c.agregar(new TransferenciaBancaria("TRF-001", "BancoNorte ACH", "BancoNorte", "https://ach.banconorte.example",
                EstadoConexion.CONECTADA, 420, 0.5, "BancoNorte", 1));
        c.agregar(new TransferenciaBancaria("TRF-002", "InterBanco", "InterBanco", "https://api.interbanco.example",
                EstadoConexion.CONECTADA, 390, 0.8, "InterBanco", 2));
        c.agregar(new BilleteraDigital("BIL-001", "PayWallet", "PayWallet", "https://api.paywallet.example",
                EstadoConexion.CONECTADA, 95, 2.0, 5000));
        c.agregar(new BilleteraDigital("BIL-002", "QuetzalPay", "QuetzalPay", "https://api.quetzalpay.example",
                EstadoConexion.CONECTADA, 130, 1.8, 2000));
        c.agregar(new CourierNacional("LOG-001", "ExpressGT", "ExpressGT", "https://api.expressgt.example",
                EstadoConexion.CONECTADA, 310, 12.5, "22 departamentos"));
        c.agregar(new CourierInternacional("LOG-002", "GlobalCargo", "GlobalCargo", "https://api.globalcargo.example",
                EstadoConexion.DEGRADADA, 680, 45, 8, "US, MX, ES"));
        // Integración "nueva": basta con esta línea para que funcione bajo los mismos contratos.
        c.agregar(new UltimaMilla("LOG-003", "RápidoYa", "RápidoYa", "https://api.rapidoya.example",
                EstadoConexion.CONECTADA, 75, 15, 25));
        c.agregar(new CertificadorFEL("FEL-001", "CertiFEL", "CertiFEL", "https://api.certifel.example",
                EstadoConexion.CONECTADA, 260, "1234567-8", "A"));
        c.agregar(new CertificadorFEL("FEL-002", "FacturaYa", "FacturaYa", "https://api.facturaya.example",
                EstadoConexion.DESCONECTADA, 0, "1234567-8", "B"));
    }
}
