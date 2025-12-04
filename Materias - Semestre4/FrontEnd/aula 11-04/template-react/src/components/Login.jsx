import { useNavigate } from "react-router-dom";
import { useState } from "react";
export default function Login() {
    const navigate = useNavigate();
    const [login, setLogin] = useState('');
    const [senha, setSenha] = useState('');
    function acessar(){
        if (login.trim() === '' && senha.trim() === '') {
            navigate(`/dashboard/${login}/${senha}`);
        }
    }
   return (
      <>
         <h1 className="bg-blue-500">Login</h1>
         <div>
            <span>Login:</span>
            <input type="text" value={login} onChange={e => setLogin(e.target.value)} />
         </div>
         <div>
            <span>Senha:</span>
            <input type="password" value={senha} onChange={e => setSenha(e.target.value)} />
         </div>
         <button onClick={acessar}>Acessar Dashboard</button>
      </>
   )
}