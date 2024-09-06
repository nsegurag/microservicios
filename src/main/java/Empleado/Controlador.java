package Empleado;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class Controlador {

    private List<Empleado> empleados = new ArrayList<>();

    public Controlador() {
        inicializarDatos();
    }

    private void inicializarDatos() {
        Empleado empleado1 = new Empleado();
        empleado1.setId("001");
        empleado1.setNombre("Juan Pérez");
        empleado1.setPuesto("Desarrollador");
        empleado1.setSalario(5000);

        Empleado empleado2 = new Empleado();
        empleado2.setId("002");
        empleado2.setNombre("María López");
        empleado2.setPuesto("Analista");
        empleado2.setSalario(4500);

        Empleado empleado3 = new Empleado();
        empleado3.setId("003");
        empleado3.setNombre("Carlos García");
        empleado3.setPuesto("Gerente");
        empleado3.setSalario(7500);

        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);
    }

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        empleado.setId("E" + (empleados.size() + 1));
        empleados.add(empleado);
        return empleado;
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleados;
    }

    @GetMapping("/{id}")
    public Empleado obtenerEmpleadoPorId(@PathVariable String id) {
        Optional<Empleado> empleado = empleados.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst();
        return empleado.orElse(null);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable String id, @RequestBody Empleado empleadoActualizado) {
        Empleado empleado = obtenerEmpleadoPorId(id);
        if (empleado != null) {
            empleado.setNombre(empleadoActualizado.getNombre());
            empleado.setPuesto(empleadoActualizado.getPuesto());
            empleado.setSalario(empleadoActualizado.getSalario());
        }
        return empleado;
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable String id) {
        empleados.removeIf(empleado -> empleado.getId().equals(id));
    }
}
