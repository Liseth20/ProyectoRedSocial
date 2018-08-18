package Datos;

/**
 *
 * @author Rodrigo Moreno S
 */
public class DAlbumes {

    //-----------------------Declaración de variables---------------------------//
    private int _idAlbum;
    private String _Nombre;
    private String _Descripcion;
    private int _fk_artista;
    private String _fechaLancimiento;
    private String _nombreArtista;
    private String _sello;
    private String _nombreGenero;
    private int _idGenero;

    //-----------------------Declaración de constructores---------------------------//
    public DAlbumes() {
    }

//Obtener datos para combobox
    public DAlbumes(int _idAlbum, String _Nombre) {
        this._idAlbum = _idAlbum;
        this._Nombre = _Nombre;
    }

//Obtener todos los albumes
    public DAlbumes(int _idAlbum, String _Nombre, String _Descripcion, String _fechaLancimiento, String _nombreArtista, String _sello, String _nombreGenero) {
        this._idAlbum = _idAlbum;
        this._Nombre = _Nombre;
        this._Descripcion = _Descripcion;
        this._fechaLancimiento = _fechaLancimiento;
        this._nombreArtista = _nombreArtista;
        this._sello = _sello;
        this._nombreGenero = _nombreGenero;
    }

//Obtener un album
    public DAlbumes(int _idAlbum, String _Nombre, String _Descripcion, String _fechaLancimiento, String sello) {
        this._idAlbum = _idAlbum;
        this._Nombre = _Nombre;
        this._Descripcion = _Descripcion;
        this._fechaLancimiento = _fechaLancimiento;
        this._sello = sello;
    }

    public DAlbumes(int _idAlbum, String _Nombre, String _Descripcion, String _fechaLancimiento, String _sello, int _idGenero) {
        this._idAlbum = _idAlbum;
        this._Nombre = _Nombre;
        this._Descripcion = _Descripcion;
        this._fechaLancimiento = _fechaLancimiento;
        this._sello = _sello;
        this._idGenero = _idGenero;
    }

//-----------------------Setters y Getters---------------------------//
    public String getSello() {
        return _sello;
    }

    public void setSello(String _sello) {
        this._sello = _sello;
    }

    public String getNombreGenero() {
        return _nombreGenero;
    }

    public void setNombreGenero(String _nombreGenero) {
        this._nombreGenero = _nombreGenero;
    }

    public int getIdGenero() {
        return _idGenero;
    }

    public void setIdGenero(int _idGenero) {
        this._idGenero = _idGenero;
    }

    public String getNombreArtista() {
        return _nombreArtista;
    }

    public void setNombreArtista(String _nombreArtista) {
        this._nombreArtista = _nombreArtista;
    }

    public String getFechaLancimiento() {
        return _fechaLancimiento;
    }

    public void setFechaLancimiento(String _fechaLancimiento) {
        this._fechaLancimiento = _fechaLancimiento;
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

    public int getFk_artista() {
        return _fk_artista;
    }

    public void setFk_artista(int _fk_artista) {
        this._fk_artista = _fk_artista;
    }

}
