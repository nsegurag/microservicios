package Facturas;

import lombok.Data;

@Data
public class Factura {
    private String id;
    private String clienteId;
    private double monto;
    private String fecha;
}
