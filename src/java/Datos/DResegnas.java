package Datos;

public class DResegnas {

    //-----------------------Declaración de variables---------------------------//
    private int _idResegna;
    private int _fk_artista;
    private int _fk_album;
    private String _Descripcion;
    private double _Calificacion;
    private String _Fecha;
    private String _NombreArtista;
    private String _NombreAlbum;
    private int _Experto;
    private String _NombreExperto;

    //-----------------------Declaración de constructores---------------------------//
    public DResegnas(int _idResegna, String _NombreArtista, String _NombreAlbum, String _Descripcion, Double _Calificacion, String _Fecha, String nombreExperto) {
        this._idResegna = _idResegna;
        this._NombreArtista = _NombreArtista;
        this._NombreAlbum = _NombreAlbum;
        this._Descripcion = _Descripcion;
        this._Calificacion = _Calificacion;
        this._Fecha = _Fecha;
        this._NombreExperto = nombreExperto;
    }

    public DResegnas(int _idResegna, String _Descripcion, Double _Calificacion) {
        this._idResegna = _idResegna;
        this._Descripcion = _Descripcion;
        this._Calificacion = _Calificacion;
    }

    public DResegnas() {
    }

    //-----------------------Declaración getters y setters---------------------------//
    public int getExperto() {
        return _Experto;
    }

    public void setExperto(int _Experto) {
        this._Experto = _Experto;
    }

    public String getNombreExperto() {
        return _NombreExperto;
    }

    public void setNombreExperto(String _NombreExperto) {
        this._NombreExperto = _NombreExperto;
    }

    public int getIdResegna() {
        return _idResegna;
    }

    public void setIdResegna(int _idResegna) {
        this._idResegna = _idResegna;
    }

    public int getFk_artista() {
        return _fk_artista;
    }

    public void setFk_artista(int _fk_artista) {
        this._fk_artista = _fk_artista;
    }

    public int getFk_album() {
        return _fk_album;
    }

    public void setFk_album(int _fk_album) {
        this._fk_album = _fk_album;
    }

    public String getDescripcion() {
        return _Descripcion;
    }

    public void setDescripcion(String _Descripcion) {
        this._Descripcion = _Descripcion;
    }

    public Double getCalificacion() {
        return _Calificacion;
    }

    public void setCalificacion(Double _Calificacion) {
        this._Calificacion = _Calificacion;
    }

    public String getFecha() {
        return _Fecha;
    }

    public void setFecha(String _Fecha) {
        this._Fecha = _Fecha;
    }

    public String getNombreArtista() {
        return _NombreArtista;
    }

    public void setNombreArtista(String _NombreArtista) {
        this._NombreArtista = _NombreArtista;
    }

    public String getNombreAlbum() {
        return _NombreAlbum;
    }

    public void setNombreAlbum(String _NombreAlbum) {
        this._NombreAlbum = _NombreAlbum;
    }

}
