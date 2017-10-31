package com.example.danni.firebasetabs;

/**
 * Created by danni on 26/10/2017.
 */
public class Tiendas {
    private String Nombre;
    private String TipoNegocio;
    private String PedidoMin;
    private String CostoEnvio;
    private String Rate;
    private String TiempoEnvio;
    private String IdNegocio;

    public Tiendas() {
    }
    public Tiendas(String IdNegocio, String Nombre,String TipoNegocio, String PedidoMin, String CostoEnvio, String Rate, String TiempoEnvio) {
        this.IdNegocio=IdNegocio;
        this.Nombre=Nombre;
        this.TipoNegocio=TipoNegocio;
        this.PedidoMin=PedidoMin;
        this.CostoEnvio=CostoEnvio;
        this.Rate=Rate;
        this.TiempoEnvio=TiempoEnvio;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setTipoNegocio(String tipoNegocio) {
        TipoNegocio = tipoNegocio;
    }

    public void setPedidoMin(String pedidoMin) {
        PedidoMin = pedidoMin;
    }

    public void setCostoEnvio(String costoEnvio) {
        CostoEnvio = costoEnvio;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public void setTiempoEnvio(String tiempoEnvio) {
        TiempoEnvio = tiempoEnvio;
    }

    public void setIdNegocio(String idNegocio) {
        IdNegocio = idNegocio;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTipoNegocio() {
        return TipoNegocio;
    }

    public String getPedidoMin() {
        return PedidoMin;
    }

    public String getCostoEnvio() {
        return CostoEnvio;
    }

    public String getRate() {
        return Rate;
    }

    public String getTiempoEnvio() {
        return TiempoEnvio;
    }

    public String getIdNegocio() {
        return IdNegocio;
    }
}
