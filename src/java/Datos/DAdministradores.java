package Datos;

/**
 *
 * @author BestMusicFM Inc
 */
public class DAdministradores {

    private int _pkIdUsuario;
    private String _FechaContatracion;

    public DAdministradores(int _pkIdUsuario, String _FechaContatracion) {
        this._pkIdUsuario = _pkIdUsuario;
        this._FechaContatracion = _FechaContatracion;
    }

    public DAdministradores() {
    }

    public int getPkIdUsuario() {
        return _pkIdUsuario;
    }

    public void setPkIdUsuario(int _pkIdUsuario) {
        this._pkIdUsuario = _pkIdUsuario;
    }

    public String getFechaContatracion() {
        return _FechaContatracion;
    }

    public void setFechaContatracion(String _FechaContatracion) {
        this._FechaContatracion = _FechaContatracion;
    }
    
    
}
