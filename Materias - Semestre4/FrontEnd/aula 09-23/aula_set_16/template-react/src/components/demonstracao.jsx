export default function Demonstracao({exibir, conteudo}) {
    return (
        <>
        <div style={{backgroundColor: exibir ? 'lightgreen' : 'lightcoral', padding: '10px', textAlign: 'center'}}>
            {exibir? conteudo : <h1>Conteúdo oculto</h1>}
        </div>
        </>
    );
}