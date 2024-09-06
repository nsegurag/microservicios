package Producto;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class Controlador {

    private List<Producto> productos = new ArrayList<>();

    public Controlador() {
        inicializarDatos();
    }

    private void inicializarDatos() {
        Producto producto1 = new Producto();
        producto1.setId("001");
        producto1.setNombre("Laptop");
        producto1.setPrecio(1200.00);
        producto1.setStock(10);

        Producto producto2 = new Producto();
        producto2.setId("002");
        producto2.setNombre("Teléfono");
        producto2.setPrecio(600.00);
        producto2.setStock(20);

        Producto producto3 = new Producto();
        producto3.setId("003");
        producto3.setNombre("Tablet");
        producto3.setPrecio(400.00);
        producto3.setStock(15);

        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        producto.setId("P" + (productos.size() + 1));
        productos.add(producto);
        return producto;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productos;
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable String id) {
        Optional<Producto> producto = productos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        return producto.orElse(null);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable String id, @RequestBody Producto productoActualizado) {
        Producto producto = obtenerProductoPorId(id);
        if (producto != null) {
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
        }
        return producto;
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable String id) {
        productos.removeIf(producto -> producto.getId().equals(id));
    }
}

