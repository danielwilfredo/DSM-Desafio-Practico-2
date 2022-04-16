package udb.fp180271dsm.practico2_fp180271_gh161659;

public class PedidoModel {

    private String IdPlato;
    private String IdFactura;
    private int Cantidad;

    public String getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(String idFactura) {
        IdFactura = idFactura;
    }

    public PedidoModel()
    {

    }

    public PedidoModel(String IdPlato, String IdFactura, int Cantidad)
    {
        this.Cantidad=Cantidad;
        this.IdFactura=IdFactura;
        this.IdPlato=IdPlato;
    }

    public String getIdPlato() {
        return IdPlato;
    }

    public void setIdPlato(String idPlato) {
        IdPlato = idPlato;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

}
