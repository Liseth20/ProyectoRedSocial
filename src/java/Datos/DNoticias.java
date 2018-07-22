package Datos;

import java.util.Date;

public class DNoticias {

    //-----------------------Declaración de variables---------------------------//
    private int _idNoticias;
    private String _Titulo;
    private String _Contenido;
    private String _Fecha;
    private String _fk_usuario;

    //-----------------------Declaración de constructores---------------------------//
   
    public DNoticias(int _idNoticias, String _Titulo, String _Contenido, String _Fecha, String _fk_usuario) {
        this._idNoticias = _idNoticias;
        this._Titulo = _Titulo;
        this._Contenido = _Contenido;
        this._Fecha = _Fecha;
        this._fk_usuario = _fk_usuario;
    }
    
     //-------------------Declaración de constructor para mostrar datos-----------------------//
    public DNoticias(int _idNoticias, String _Titulo, String _Contenido, String _Fecha) {
        this._idNoticias = _idNoticias;
        this._Titulo = _Titulo;
        this._Contenido = _Contenido;
         this._Fecha = _Fecha;
   }
     //-------------------Declaración de constructor para cargar datos-----------------------//
        public DNoticias(int _idNoticias, String _Titulo, String _Contenido) {
        this._idNoticias = _idNoticias;
        this._Titulo = _Titulo;
        this._Contenido = _Contenido;
         this._Fecha = _Fecha;
   }

    public DNoticias() {
    }
    //-----------------------Declaración de getters y setters---------------------------//

    public int getIdNoticias() {
        return _idNoticias;
    }

    public void setIdNoticias(int _idNoticias) {
        this._idNoticias = _idNoticias;
    }

    public String getTitulo() {
        return _Titulo;
    }

    public void setTitulo(String _Titulo) {
        this._Titulo = _Titulo;
    }

    public String getContenido() {
        return _Contenido;
    }

    public void setContenido(String _Contenido) {
        this._Contenido = _Contenido;
    }

    public String getFecha() {
        return _Fecha;
    }

    public void setFecha(String _Fecha) {
        this._Fecha = _Fecha;
    }

    public String getFk_usuario() {
        return _fk_usuario;
    }

    public void setFk_usuario(String _fk_usuario) {
        this._fk_usuario = _fk_usuario;
    }

}
