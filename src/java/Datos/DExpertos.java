/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Rodrigo Moreno S
 */
public class DExpertos {

    //----------Variables--------------------------------
    private int _Fk_usuario;
    private int _Fk_genero;
    private String Descripcion;
    private String fechaInicio;

    //----------Constructores--------------------------------
    public DExpertos(int _Fk_usuario, int _Fk_genero, String Descripcion, String fechaInicio) {
        this._Fk_usuario = _Fk_usuario;
        this._Fk_genero = _Fk_genero;
        this.Descripcion = Descripcion;
        this.fechaInicio = fechaInicio;
    }

    public DExpertos() {
    }

    //----------Getter y setter--------------------------------
    public int getFk_usuario() {
        return _Fk_usuario;
    }

    public void setFk_usuario(int _Fk_usuario) {
        this._Fk_usuario = _Fk_usuario;
    }

    public int getFk_genero() {
        return _Fk_genero;
    }

    public void setFk_genero(int _Fk_genero) {
        this._Fk_genero = _Fk_genero;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

}
