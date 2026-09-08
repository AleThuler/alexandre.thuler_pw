var primeiro_valor = document.querySelector("#primeiro_numero");
var segundo_valor = document.querySelector("#segundo_numero");
var resultado = document.getElementById("resultado");

function somar() {
    resultado.innerHTML = Number(primeiro_valor.value) + Number(segundo_valor.value);
}

function subtrair() {
    resultado.innerHTML = Number(primeiro_valor.value) - Number(segundo_valor.value);
}

function multiplicar() {
    resultado.innerHTML = Number(primeiro_valor.value) * Number(segundo_valor.value);
}

function dividir() {
    resultado.innerHTML = Number(primeiro_valor.value) / Number(segundo_valor.value);
}