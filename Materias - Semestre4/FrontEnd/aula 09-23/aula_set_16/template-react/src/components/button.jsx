export default function Button ({desabilitado}){
    const estiloBotao = { backgroundColor: desabilitado ? 'gray' : 'blue' };
    return(
        <>
            <button style={estiloBotao} disabled={desabilitado} onClick={() => alert('Botão clicado!')}>Clique aqui</button>
        </>
    );
}