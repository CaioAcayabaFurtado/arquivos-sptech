import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)


/*
  Acima, estamos definindo a Raíz (root) para o React renderizar seu conteúdo dentro desse elemento.
  No arquivo "index.html", temos apenas uma div <div id="root"></div>, que será manipulada pelo React.
  Para isso, temos o método "createRoot", da biblioteca 'react-dom', que recebe "qual elemento deve usar como raíz para o React".
    - Nesse caso, passamos a div 'root' como raíz, acessada com document.getElementById('root')
  Depois de instanciarmos a raíz do React, utilizamos o método ".render()" para renderizar o conteúdo dentro dessa div.
  No exemplo acima, estamos importando o componente <App> e renderizando ele dentro da raíz do React.
*/