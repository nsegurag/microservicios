package Clientes;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")

public class Controlador {

    private List<Clientes> clientes = new ArrayList<>();

    public Controlador() {
        inicializarDatos();
    }

    private void inicializarDatos() {
        Clientes cliente1 = new Clientes();
        cliente1.setId("01");
        cliente1.setNombre("Coca Cola");
        cliente1.setCorreo("ejemplo@gmail.com");
        cliente1.setTelefono("5707-5707");

        Clientes cliente2 = new Clientes();
        cliente2.setId("02");
        cliente2.setNombre("Coca Cola");
        cliente2.setCorreo("ejemplo@gmail.com");
        cliente2.setTelefono("5707-5707");
        
        Clientes cliente3 = new Clientes();
        cliente3.setId("03");
        cliente3.setNombre("Coca Cola");
        cliente3.setCorreo("ejemplo@gmail.com");
        cliente3.setTelefono("5707-5707");

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
    }

    @PostMapping
    public Clientes crearCliente(@RequestBody Clientes cliente) {
        cliente.setId(String.valueOf(clientes.size() + 1));
        clientes.add(cliente);
        return cliente;
    }

    @GetMapping
    public List<Clientes> listarCliente() {
        return clientes;
    }

    @GetMapping("/{id}")
    public Clientes obtenerClientePorId(@PathVariable String id) {
        Optional<Clientes> cliente = clientes.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        return cliente.orElse(null);
    }

    @PutMapping("/{id}")
    public Clientes actualizarCliente(@PathVariable String id, @RequestBody Clientes ClienteActualizado) {
        Clientes cliente = obtenerClientePorId(id);
        if (cliente != null) {
            cliente.setNombre(ClienteActualizado.getNombre());
            cliente.setCorreo(ClienteActualizado.getCorreo());
            cliente.setTelefono(ClienteActualizado.getTelefono());
        }
        return cliente;
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable String id) {
        clientes.removeIf(cliente -> cliente.getId().equals(id));
    }
}
