package Datos;

public class DUsuario {
    //-----------------------Declaración de variables---------------------------
    private int _idUsuario;
    private String _Nombre;
    private String _Ape1;
    private String _Ape2;
    private String _fechaNacimiento;
    private String _User;
    private String _Email;
    private String _Pass;
    private String _FechaCreacion;
    private String _Genero;
    private String _PaisOrigen;
    private String _TipoUsuario;

    
    
    //-----------------------Contructores-------------------------------------------------------
    public DUsuario(String _Nombre, String _Ape1, String _Ape2, String _fechaNacimiento, String _User, String _Email, 
            String _Pass, String _FechaCreacion, String _Genero, String _PaisOrigen, String _TipoUsuario, int _idUsuario) {
        this._Nombre = _Nombre;
        this._Ape1 = _Ape1;
        this._Ape2 = _Ape2;
        this._fechaNacimiento = _fechaNacimiento;
        this._User = _User;
        this._Email = _Email;
        this._Pass = _Pass;
        this._FechaCreacion = _FechaCreacion;
        this._Genero = _Genero;
        this._PaisOrigen = _PaisOrigen;
        this._TipoUsuario = _TipoUsuario;
        this._idUsuario = _idUsuario;
    }

    public DUsuario() {
    }

    
   

    //-----------------------------Getter y setter------------------------------
    
     public int getIdUsuario() {
        return _idUsuario;
    }
    
    public void setIdUsuario(int _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public String getNombre() {
        return _Nombre;
    }

    public void setNombre(String _Nombre) {
        this._Nombre = _Nombre;
    }

    public String getApe1() {
        return _Ape1;
    }

    public void setApe1(String _Ape1) {
        this._Ape1 = _Ape1;
    }

    public String getApe2() {
        return _Ape2;
    }

    public void setApe2(String _Ape2) {
        this._Ape2 = _Ape2;
    }

    public String getFechaNacimiento() {
        return _fechaNacimiento;
    }

    public void setFechaNacimiento(String _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
    }

    public String getUser() {
        return _User;
    }

    public void setUser(String _User) {
        this._User = _User;
    }

    public String getEmail() {
        return _Email;
    }

    public void setEmail(String _Email) {
        this._Email = _Email;
    }

    public String getPass() {
        return _Pass;
    }

    public void setPass(String _Pass) {
        this._Pass = _Pass;
    }

    public String getFechaCreacion() {
        return _FechaCreacion;
    }

    public void setFechaCreacion(String _FechaCreacion) {
        this._FechaCreacion = _FechaCreacion;
    }

    public String getGenero() {
        return _Genero;
    }

    public void setGenero(String _Genero) {
        this._Genero = _Genero;
    }

    public String getPaisOrigen() {
        return _PaisOrigen;
    }

    public void setPaisOrigen(String _PaisOrigen) {
        this._PaisOrigen = _PaisOrigen;
    }

    public String getTipoUsuario() {
        return _TipoUsuario;
    }

    public void setTipoUsuario(String _TipoUsuario) {
        this._TipoUsuario = _TipoUsuario;
    }
    
    
    
}
