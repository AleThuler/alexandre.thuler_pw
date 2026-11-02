const API_BASE = 'http://localhost:3000/api/funcionarios'

const modal = document.querySelector('.modal-container')
const tbody = document.querySelector('tbody')
const sNome = document.querySelector('#m-nome')
const sFuncao = document.querySelector('#m-funcao')
const sSalario = document.querySelector('#m-salario')
const btnSalvar = document.querySelector('#btnSalvar')

let itens = []
let idEmEdicao = null

function openModal(edit = false, index = 0) {
  modal.classList.add('active')

  modal.onclick = e => {
    if (e.target.className.indexOf('modal-container') !== -1) {
      modal.classList.remove('active')
    }
  }

  if (edit) {
    sNome.value = itens[index].nome
    sFuncao.value = itens[index].funcao
    sSalario.value = itens[index].salario
    idEmEdicao = itens[index].id
  } else {
    sNome.value = ''
    sFuncao.value = ''
    sSalario.value = ''
    idEmEdicao = null
  }
}

function editItem(index) {
  openModal(true, index)
}

function escapeHtml(texto) {
  return String(texto).replace(/[&<>"']/g, caractere => ({
    '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;'
  }[caractere]))
}

function formatarMoeda(valor) {
  return Number(valor).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })
}

  Mostra a mensagem de erro que o backend mandou ({"error": "..."})
async function mostrarErro(resposta) {
  let mensagem = `Erro ${resposta.status}`
  try {
    const corpo = await resposta.json()
    if (corpo.error) mensagem = corpo.error
  } catch (_) {
  }
  alert(mensagem)
}

function insertItem(item, index) {
  const tr = document.createElement('tr')
  tr.innerHTML = `
    <td>${escapeHtml(item.nome)}</td>
    <td>${escapeHtml(item.funcao)}</td>
    <td>${formatarMoeda(item.salario)}</td>
    <td class="acao">
      <button onclick="editItem(${index})"><i class='bx bx-edit'></i></button>
    </td>
    <td class="acao">
      <button onclick="deleteItem(${index})"><i class='bx bx-trash'></i></button>
    </td>
  `
  tbody.appendChild(tr)
}

async function getItensBD() {
  try {
    const resposta = await fetch(API_BASE)
    if (!resposta.ok) throw new Error('Erro ao carregar')
    return await resposta.json()
  } catch (erro) {
    console.error('Erro API:', erro)
    return []
  }
}

async function loadItens() {
  itens = await getItensBD()
  tbody.innerHTML = ''
  itens.forEach((item, index) => insertItem(item, index))
}

btnSalvar.onclick = async e => {
  if (sNome.value === '' || sFuncao.value === '' || sSalario.value === '') {
    return
  }
  e.preventDefault()

  const dados = {
    nome: sNome.value.trim(),
    funcao: sFuncao.value.trim(),
    salario: Number(sSalario.value)
  }

  const editando = idEmEdicao !== null
  const url = editando ? `${API_BASE}/${idEmEdicao}` : API_BASE
  const metodo = editando ? 'PUT' : 'POST'

  try {
    const resposta = await fetch(url, {
      method: metodo,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(dados)
    })

    if (!resposta.ok) {
      await mostrarErro(resposta)
      return
    }

    modal.classList.remove('active')
    idEmEdicao = null
    loadItens()
  } catch (erro) {
    console.error('Erro ao salvar:', erro)
    alert('Erro ao salvar. Verifique se o servidor está rodando.')
  }
}

async function deleteItem(index) {
  const funcionario = itens[index]
  if (!funcionario) return
  if (!confirm(`Excluir ${funcionario.nome}?`)) return

  try {
    const resposta = await fetch(`${API_BASE}/${funcionario.id}`, { method: 'DELETE' })
    if (!resposta.ok) await mostrarErro(resposta)
    loadItens()
  } catch (erro) {
    console.error('Erro ao deletar:', erro)
    alert('Erro ao excluir. Verifique se o servidor está rodando.')
  }
}

loadItens()
