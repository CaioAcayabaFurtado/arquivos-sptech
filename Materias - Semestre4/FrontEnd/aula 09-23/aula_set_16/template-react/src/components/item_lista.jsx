export default function ItemLista({fruta}) { 
    return (
        <>
            <li style={{
                listStyleType: 'none', 
                border: '1px solid #ccc', 
                padding: '10px', 
                borderRadius: '8px', 
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                flexDirection: 'column',
                marginBottom: '10px'
                }}>
                <img src={fruta.imagem} alt={fruta.descricao}  style={{
                    height: '150px', 
                }}/>
                <h2>{fruta.descricao}</h2> <br />
            </li>
        </>
    );
}