let nome_user = prompt("Qual o seu nome?");
console.log(nome_user);

let data_nascimento = prompt("Qual foi o seu ano de nascimento?");
data_nascimento = parseInt(data_nascimento);
console.log(data_nascimento);

console.log(`Olá ${nome_user}! Você deve ter ${2026 - data_nascimento}`);

let resultado = `Olá ${nome_user}! Você deve ter ${2026 - data_nascimento}`;
let texto = document.getElementById("resposta");
texto.innerHTML = resultado;