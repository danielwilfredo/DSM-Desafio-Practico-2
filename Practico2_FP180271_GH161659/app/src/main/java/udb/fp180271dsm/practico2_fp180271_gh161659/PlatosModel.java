package udb.fp180271dsm.practico2_fp180271_gh161659;

import java.io.Serializable;

public class PlatosModel implements Serializable {

    public PlatosModel()
    {
        //empty contructor needed
    }

    public PlatosModel(String nombrePlato,int precioPlato, String detallePlato, boolean añadidoAlPedido,int cantidad,String documentID)
    {
        this.nombrePlato=nombrePlato;
        this.precioPlato=precioPlato;
        this.detallePlato=detallePlato;
        this.añadidoAlPedido=añadidoAlPedido;
        this.cantidad=cantidad;
        this.documentID = documentID;
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


    public double getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(double precioPlato) {
        this.precioPlato = precioPlato;
    }

    private double precioPlato;
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


    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    private String documentID;




}
