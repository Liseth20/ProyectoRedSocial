$(document).ready(function () {


    var botones = $("#botones").text();
    console.log(botones);

    if (botones === "Actualizar") {
        $("#Registrar").remove();
        $("#Actualizar").show();
        $("#Elegir").remove();

    } else if (botones === "Registrar") {
        $("#Registrar").show();
        $("#Actualizar").remove();
        $("#Elegir").remove();


    } else {
        $("#Registrar").remove();
        $("#Actualizar").remove();
        $("#Elegir").show();

    }

});