

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar Genero</title>
    </head>
    <body>
        <h2>Insertar un nuevo genero</h2>
        
         <form action = "LGenero" method="post">
            
            <input type="hidden" name="Accion" value="Insertar">

            Nombre:
            <br> 
            <input type="text" name="nombre" id = "nombre" required="">
            <br>
            <br>

            Descripción:

            <br> 
            <textarea name="descripcion" rows="4" cols="20" id="descripcion" required="">
            </textarea>
            <br>
            <br>


            <input type="submit" name = "btnInsertar" id="button" value="Insertar">


        </form>

        
    </body>
</html>
