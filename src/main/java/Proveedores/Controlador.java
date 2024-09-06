package Proveedores;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class Controlador {

    private List<Proveedor> proveedores = new ArrayList<>();

    public Controlador() {
        inicializarDatos();
    }

    private void inicializarDatos() {
        Proveedor proveedor1 = new Proveedor();
        proveedor1.setId("01");
        proveedor1.setNombre("Coca Cola");
        proveedor1.setDireccion("Avenida Maravilla 123");
        proveedor1.setTelefono("5707-5707");

        Proveedor proveedor2 = new Proveedor();
        proveedor2.setId("02");
        proveedor2.setNombre("Pepsi Cola");
        proveedor2.setDireccion("Avenida Insurgentes 321");
        proveedor2.setTelefono("5858-5858");
        
        Proveedor proveedor3 = new Proveedor();
        proveedor3.setId("03");
        proveedor3.setNombre("Super Cola");
        proveedor3.setDireccion("Diagonal 5");
        proveedor3.setTelefono("5959-5959");

        proveedores.add(proveedor1);
        proveedores.add(proveedor2);
        proveedores.add(proveedor3);
    }

    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        proveedor.setId(String.valueOf(proveedores.size() + 1));
        proveedores.add(proveedor);
        return proveedor;
    }

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedores;
    }

    @GetMapping("/{id}")
    public Proveedor obtenerProveedorPorId(@PathVariable String id) {
        Optional<Proveedor> proveedor = proveedores.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        return proveedor.orElse(null);
    }

    @PutMapping("/{id}")
    public Proveedor actualizarProveedor(@PathVariable String id, @RequestBody Proveedor proveedorActualizado) {
        Proveedor proveedor = obtenerProveedorPorId(id);
        if (proveedor != null) {
            proveedor.setNombre(proveedorActualizado.getNombre());
            proveedor.setDireccion(proveedorActualizado.getDireccion());
            proveedor.setTelefono(proveedorActualizado.getTelefono());
        }
        return proveedor;
    }

    @DeleteMapping("/{id}")
    public void eliminarProveedor(@PathVariable String id) {
        proveedores.removeIf(proveedor -> proveedor.getId().equals(id));
    }
}
