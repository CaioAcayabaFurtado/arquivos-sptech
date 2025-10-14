import { useState } from "react";

function Calculadora() {
  const [num1, setNum1] = useState("");
  const [num2, setNum2] = useState("");
  const [operacao, setOperacao] = useState("+");
  const [resultado, setResultado] = useState("");

  function calcular() {
    const a = parseFloat(num1);
    const b = parseFloat(num2);
    let res = "";
    switch (operacao) {
      case "+":
        res = a + b;
        break;
      case "-":
        res = a - b;
        break;
      case "*":
        res = a * b;
        break;
      case "/":
        res = b !== 0 ? a / b : "Divisão por zero";
        break;
      default:
        res = "Operação inválida";
    }
    setResultado(res);
  }

  return (
    <div>
      <h2>Calculadora</h2>
      <input type="number" value={num1} onChange={e => setNum1(e.target.value)} placeholder="Primeiro número" />
      <input type="number" value={num2} onChange={e => setNum2(e.target.value)} placeholder="Segundo número" />
      <select value={operacao} onChange={e => setOperacao(e.target.value)}>
        <option value="+">+</option>
        <option value="-">-</option>
        <option value="*">×</option>
        <option value="/">÷</option>
      </select>
      <button onClick={calcular}>Calcular</button>
      <div>Resultado: {resultado}</div>
    </div>
  );
}

export default Calculadora;
