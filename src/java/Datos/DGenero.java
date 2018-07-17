package Datos;

public class DGenero {

    //-----------------------Declaración de variables---------------------------//
    private int _idGenero_musical;
    private String _Nombre;
    private String _Descripcion;

    //-----------------------Declaración de constructores---------------------------//
    public DGenero(int _idGenero_musical, String _Nombre, String _Descripcion) {

        this._idGenero_musical = _idGenero_musical;
        this._Nombre = _Nombre;
        this._Descripcion = _Descripcion;
    }

    public DGenero(String _Nombre, String _Descripcion) {

        this._Nombre = _Nombre;
        this._Descripcion = _Descripcion;
    }

    public DGenero() {
    }

    //-----------------------Declaración de getters y setters---------------------------//
    public int getIdGenero_musical() {
        return _idGenero_musical;
    }

    public void setIdGenero_musical(int _idGenero_musical) {
        this._idGenero_musical = _idGenero_musical;
    }

    public String getNombre() {
        return _Nombre;
    }

    public void setNombre(String _Nombre) {
        this._Nombre = _Nombre;
    }

    public String getDescripcion() {
        return _Descripcion;
    }

    public void setDescripcion(String _Descripcion) {
        this._Descripcion = _Descripcion;
    }

}
