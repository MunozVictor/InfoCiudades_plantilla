package com.jairobilbao.infociudades;

/**
 * Created by Jairo on 17/02/2016.
 */
public class Ciudad {
    private int codCiudad;
    private String nomCiudad;
    private String provincia;
    private int poblacion;

    public Ciudad() {
    }

    public Ciudad(int codCiudad, String nomCiudad, String provincia, int poblacion) {
        this.codCiudad = codCiudad;
        this.nomCiudad = nomCiudad;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public int getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(int codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getNomCiudad() {
        return nomCiudad;
    }

    public void setNomCiudad(String nomCiudad) {
        this.nomCiudad = nomCiudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }
}
