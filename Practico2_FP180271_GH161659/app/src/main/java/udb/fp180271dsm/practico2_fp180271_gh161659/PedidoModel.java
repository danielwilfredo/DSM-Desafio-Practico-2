package udb.fp180271dsm.practico2_fp180271_gh161659;

public class PedidoModel {

    private String NombrePlato;
    private int Cantidad;
    private double Precio;

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public PedidoModel()
    {

    }

    public PedidoModel(String NombrePlato, int Cantidad, double precio)
    {
        this.Cantidad=Cantidad;
        this.NombrePlato=NombrePlato;
        this.Precio = precio;
    }

    public String getNombrePlato() {
        return NombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        NombrePlato = nombrePlato;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

}
