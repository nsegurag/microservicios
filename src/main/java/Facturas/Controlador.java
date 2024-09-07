package Facturas;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturas")
public class Controlador {

    private List<Factura> facturas = new ArrayList<>();

    public Controlador() {
        inicializarDatos();
    }

    private void inicializarDatos() {
        Factura factura1 = new Factura();
        factura1.setId("001");
        factura1.setClienteId("001");
        factura1.setMonto(250.75);
        factura1.setFecha("2024-09-05");

        Factura factura2 = new Factura();
        factura2.setId("002");
        factura2.setClienteId("002");
        factura2.setMonto(500.00);
        factura2.setFecha("2024-09-04");
        
        Factura factura3 = new Factura();
        factura3.setId("003");
        factura3.setClienteId("003");
        factura3.setMonto(150.50);
        factura3.setFecha("2024-09-03");

        facturas.add(factura1);
        facturas.add(factura2);
        facturas.add(factura3);
    }

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        factura.setId("F" + (facturas.size() + 1));
        facturas.add(factura);
        return factura;
    }

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturas;
    }

    @GetMapping("/{id}")
    public Factura obtenerFacturaPorId(@PathVariable String id) {
        Optional<Factura> factura = facturas.stream()
            .filter(f -> f.getId().equals(id))
            .findFirst();
        return factura.orElse(null);
    }

    @PutMapping("/{id}")
    public Factura actualizarFactura(@PathVariable String id, @RequestBody Factura facturaActualizada) {
        Factura factura = obtenerFacturaPorId(id);
        if (factura != null) {
            factura.setClienteId(facturaActualizada.getClienteId());
            factura.setMonto(facturaActualizada.getMonto());
            factura.setFecha(facturaActualizada.getFecha());
        }
        return factura;
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable String id) {
        facturas.removeIf(factura -> factura.getId().equals(id));
    }
}
