package udb.fp180271dsm.practico2_fp180271_gh161659;

public class FacturaModel {

    String NombreCompleto;
    String Telefono;
    String Fecha;
    double TotalPedido;

    public FacturaModel(String nombreCompleto, String telefono, String fecha, double totalPedido) {
        NombreCompleto = nombreCompleto;
        Telefono = telefono;
        Fecha = fecha;
        TotalPedido = totalPedido;
    }

    public FacturaModel()
    {

    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        NombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public double getTotalPedido() {
        return TotalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        TotalPedido = totalPedido;
    }
}
