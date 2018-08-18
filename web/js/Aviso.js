/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {


    var botones = $("#aviso").text();
    console.log(botones);

    if (botones === "error") {
        $("#Error").show();



    } else {
        $("#Error").hide();

    }
});