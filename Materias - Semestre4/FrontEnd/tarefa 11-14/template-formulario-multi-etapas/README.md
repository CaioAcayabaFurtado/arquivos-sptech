
# 🧾 Formulário Multi-Etapas com React Router

Um projeto desenvolvido em **React** que implementa um **formulário dividido em múltiplas etapas**, utilizando o **React Router** para simular um processo completo de cadastro com **navegação entre telas** e **armazenamento de dados do usuário**.

---

## 🚀 Objetivo do Projeto

O objetivo é criar uma aplicação funcional onde o usuário preenche informações pessoais, de endereço e, por fim, revisa todos os dados antes da confirmação.
Cada etapa deve ser **um componente React independente**, com navegação controlada por **rotas**.

---

## 🧩 Estrutura das Etapas

### **Etapa 1 – Dados Pessoais**

Campos obrigatórios:

* Nome
* E-mail
* Telefone

### **Etapa 2 – Endereço**

Campos obrigatórios:

* Endereço
* Cidade
* Estado

### **Etapa 3 – Confirmação**

* Exibe um **resumo completo** de todas as informações preenchidas.
* Permite que o usuário revise e confirme os dados.

### **Etapa 4 – Concluído**

* Exibe uma tela final com **o nome do usuário** e uma **mensagem de agradecimento**.

---

## ⚙️ Funcionalidades Principais

* 🔹 Cada etapa é representada por **um componente React separado**.
* 🔹 Ao clicar em **“Próximo”**, os dados são salvos e o usuário é redirecionado para a próxima etapa.
* 🔹 Na **etapa final**, o usuário visualiza um **resumo de todos os dados** antes de confirmar.
* 🔹 O **indicador de progresso** muda conforme o avanço das etapas.
* 🔹 As **rotas** controlam o fluxo de navegação entre as páginas do formulário.

---

## 🧭 Mapeamento das Rotas

| Caminho         | Etapa          |
| --------------- | -------------- |
| `/`             | Dados pessoais |
| `/user-address` | Endereço       |
| `/confirm-data` | Confirmação    |
| `/done`         | Concluído      |

---

## 💡 Dicas de Implementação

* Utilize o hook [`useNavigate`] do **React Router** para controlar a navegação e enviar dados entre páginas.
* Armazene os valores dos campos utilizando o hook `useState`.
* Consulte o material de **React Router DOM** no Moodle para entender boas práticas de navegação e persistência de dados.

---

## 🔄 Fluxo de Uso

1️⃣ O usuário acessa a aplicação e preenche os **dados pessoais**.
2️⃣ Clica em **“Próximo”** → é direcionado para a etapa de **endereço**.
3️⃣ Após preencher o endereço, clica novamente em **“Próximo”**.
4️⃣ Na **etapa de confirmação**, revisa todas as informações e confirma o envio.
5️⃣ É direcionado para a **tela de concluído**, onde é exibida uma mensagem de agradecimento com seu nome.

---

## 🧱 Observações Importantes

* O **indicador de progresso** está localizado em `./components/Indicador`.
  ⚠️ **Não é necessário alterá-lo.**
* Utilize os **componentes disponibilizados na aplicação**, pois isso influencia na avaliação do projeto (`Button`, `Input`, `CardInfo`). Todos estão localizados na pasta `./components`.
* Mantenha o **layout e o fluxo** fiéis às instruções para garantir o correto funcionamento das etapas.
* As dependências necessárias para a codificação **já estão instaladas**.
* O projeto é apenas um **template** — você é responsável por configurar a lógica completa do fluxo descrito acima.
* ❌ **Não é permitido** o uso de **props** nos componentes que estão dentro da pasta `/pages`, pois isso influencia na avaliação do projeto.
* ❌ **Não é permitido** o uso de **document.getElementById**, pois isso também influencia na avaliação do projeto.

## 📦 Envio do Projeto

O envio no **Moodle** deve ser feito compactando **apenas a pasta `./src`** do projeto.

⚠️ **Não envie a pasta `node_modules`** — caso seja enviada, será atribuída será descontado da **nota de entrega**.

