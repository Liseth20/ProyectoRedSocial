package Datos;

public class DCanciones {
//-----------------------------Variables----------------------------------------

    private int _idCancion;
    private String _Nombre;
    private String _duracion;
    private int _Album;
    private String _NombreAlbum;
//-------------------------Constructores------------------------------------

    public DCanciones(int _idCancion, String _Nombre, String _duracion, int _Album) {
        this._idCancion = _idCancion;
        this._Nombre = _Nombre;
        this._duracion = _duracion;
        this._Album = _Album;
    }

    public DCanciones(int _idCancion, String _Nombre, String _duracion, String _NombreAlbum) {
        this._idCancion = _idCancion;
        this._Nombre = _Nombre;
        this._duracion = _duracion;
        this._NombreAlbum = _NombreAlbum;
    }

    public DCanciones(int _idCancion, String _Nombre, String _duracion) {
        this._idCancion = _idCancion;
        this._Nombre = _Nombre;
        this._duracion = _duracion;
    }

    public DCanciones() {
    }
//-------------------------Getters y Setters------------------------------------

    public String getNombreAlbum() {
        return _NombreAlbum;
    }

    public void setNombreAlbum(String _NombreAlbum) {
        this._NombreAlbum = _NombreAlbum;
    }

    public int getAlbum() {
        return _Album;
    }

    public void setAlbum(int _Album) {
        this._Album = _Album;
    }

    public int getIdCancion() {
        return _idCancion;
    }

    public void setIdCancion(int _idCancion) {
        this._idCancion = _idCancion;
    }

    public String getNombre() {
        return _Nombre;
    }

    public void setNombre(String _Nombre) {
        this._Nombre = _Nombre;
    }

    public String getDuracion() {
        return _duracion;
    }

    public void setDuracion(String _duracion) {
        this._duracion = _duracion;
    }

}
