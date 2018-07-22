package Datos;

public class DAlbum {
    //-----------------------Declaración de variables---------------------------//

    private int _idAlbum;
    private String _Nombre;
    private String _Descripcion;
    private String _fk_artista;

    //-----------------------Declaración de constructores---------------------------//
    public DAlbum() {
    }

    public DAlbum(int _idAlbum, String _Nombre, String _Descripcion, String _fk_artista) {
        this._idAlbum = _idAlbum;
        this._Nombre = _Nombre;
        this._Descripcion = _Descripcion;
        this._fk_artista = _fk_artista;
    }

    public DAlbum(int _idAlbum, String _Nombre, String _Descripcion) {
        this._idAlbum = _idAlbum;
        this._Nombre = _Nombre;
        this._Descripcion = _Descripcion;

    }

    public int getIdAlbum() {
        return _idAlbum;
    }

    public void setIdAlbum(int _idAlbum) {
        this._idAlbum = _idAlbum;
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

    public String getFk_artista() {
        return _fk_artista;
    }

    public void setFk_artista(String _fk_artista) {
        this._fk_artista = _fk_artista;
    }

}
