package Datos;

//@author BestMusicFM Inc
public class DArtista {

    //--------------Declaración de variables------------------------
    private int _fkUsuario;
    private int _genero;
    private int _imagen;
    private String _tipoArtista;
    private String _fechaIncio;
    private String _biografia;
    private String _nombreBanda;

    
       
    //----------------Contructores----------------------------------------
    public DArtista(int _fkUsuario, int _genero, String _tipoArtista, String _fechaIncio, int _imagen, String _biografia, String _nombreBanda) {
        this._fkUsuario = _fkUsuario;
        this._genero = _genero;
        this._tipoArtista = _tipoArtista;
        this._fechaIncio = _fechaIncio;
        this._imagen = _imagen;
        this._biografia = _biografia;
        this._nombreBanda = _nombreBanda;
    }

    public DArtista(int _fkUsuario, String _nombreBanda) {
        this._fkUsuario = _fkUsuario;
        this._nombreBanda = _nombreBanda;
    }

    public DArtista() {
    }

    //---------------Getter y setter------------------------------------
    public int getFkUsuario() {
        return _fkUsuario;
    }

    public void setFkUsuario(int _fkUsuario) {
        this._fkUsuario = _fkUsuario;
    }

    public int getGenero() {
        return _genero;
    }

    public void setGenero(int _genero) {
        this._genero = _genero;
    }

    public String getTipoArtista() {
        return _tipoArtista;
    }

    public void setTipoArtista(String _tipoArtista) {
        this._tipoArtista = _tipoArtista;
    }

    public String getFechaIncio() {
        return _fechaIncio;
    }

    public void setFechaIncio(String _fechaIncio) {
        this._fechaIncio = _fechaIncio;
    }

    public int getImagen() {
        return _imagen;
    }

    public void setImagen(int _imagen) {
        this._imagen = _imagen;
    }

    public String getBiografia() {
        return _biografia;
    }

    public void setBiografia(String _biografia) {
        this._biografia = _biografia;
    }

    public String getNombreBanda() {
        return _nombreBanda;
    }

    public void setNombreBanda(String _nombreBanda) {
        this._nombreBanda = _nombreBanda;
    }

}
