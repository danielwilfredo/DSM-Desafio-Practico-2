package udb.fp180271dsm.practico2_fp180271_gh161659;

public class PlatosModel {

    private PlatosModel()
    {
        //empty contructor needed
    }

    private PlatosModel(String nombrePlato,int precioPlato, String detallePlato, boolean añadidoAlPedido)
    {
        this.nombrePlato=nombrePlato;
        this.precioPlato=precioPlato;
        this.detallePlato=detallePlato;
        this.añadidoAlPedido=añadidoAlPedido;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }



    public String getDetallePlato() {
        return detallePlato;
    }

    public void setDetallePlato(String detallePlato) {
        this.detallePlato = detallePlato;
    }

    private String nombrePlato;

    public int getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(int precioPlato) {
        this.precioPlato = precioPlato;
    }

    private int precioPlato;
    private String detallePlato;

    public boolean isAñadidoAlPedido() {
        return añadidoAlPedido;
    }

    public void setAñadidoAlPedido(boolean añadidoAlPedido) {
        this.añadidoAlPedido = añadidoAlPedido;
    }

    private boolean añadidoAlPedido;

    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(String idPlato) {
        this.idPlato = idPlato;
    }

    private String idPlato;




}
