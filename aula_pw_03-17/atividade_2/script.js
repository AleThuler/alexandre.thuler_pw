const calcular_quadrado = document.getElementById('quadrado');
const calcular_retangulo = document.getElementById('retangulo');
const calcular_triangulo = document.getElementById('triangulo');
const calcular_circulo = document.getElementById('circulo');

const elementos = document.querySelectorAll('div, body, button, label, input, p, html, #form_quadrado, #form_retangulo, #form_triangulo, #form_circulo');

const cores = {
    'quadrado': '#000816',
        'retangulo': '#160000',
        'triangulo': '#001600',
        'circulo': '#160016'
}

const texto_resposta = document.getElementById('resposta');

calcular_quadrado.style.display = 'none';
calcular_retangulo.style.display = 'none';
calcular_triangulo.style.display = 'none';
calcular_circulo.style.display = 'none';

function mostrar_campo_calculo(id){
    calcular_quadrado.style.display = 'none';
    calcular_retangulo.style.display = 'none';
    calcular_triangulo.style.display = 'none';
    calcular_circulo.style.display = 'none';

    if (cores[id]){
        elementos.forEach(el => {
            el.style.backgroundColor = cores[id];
        });
        
    }
    
    document.getElementById(id).style.display = 'block';
}

calcular_quadrado.addEventListener('submit', function(event){
    event.preventDefault();

    let lado = document.getElementById('lado_quadrado').value;
    let area = lado*lado;

    texto_resposta.innerHTML = area;
});

calcular_retangulo.addEventListener('submit', function(event){
    event.preventDefault();

    let base = document.getElementById('base_retangulo').value;
    let altura = document.getElementById('altura_retangulo').value;

    let area = base * altura;

    texto_resposta.innerHTML = area;
});

calcular_triangulo.addEventListener('submit', function(event){
    event.preventDefault();

    let base = document.getElementById('base_triangulo').value;
    let altura = document.getElementById('altura_triangulo').value;

    let area = (base * altura) / 2;
    
    texto_resposta.innerHTML = area;
});

calcular_circulo.addEventListener('submit', function(event){
    event.preventDefault();
    
    let raio = document.getElementById('raio').value;

    let area = raio * (3.14 **2)

    texto_resposta.innerHTML = area;
})
