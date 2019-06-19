package com.santiago.tiendita;

public class Categoria {
    public Integer id;
    public String descripcion;
    public String imagen_banner;
    public String imagen_cuadrada;
    public Integer nivel;

    public Categoria(Integer id, String descripcion, String imagen_banner, String imagen_cuadrada, Integer nivel) {
        this.id = id;
        this.descripcion = descripcion;
        this.imagen_banner = imagen_banner;
        this.imagen_cuadrada = imagen_cuadrada;
        this.nivel = nivel;
    }
}
