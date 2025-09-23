export default function Imagem({recurso}) {
    const urlPadrao = "https://static.todamateria.com.br/upload/pi/ng/pinguim01-cke.jpg";
    return(
        <>
            <img src={recurso || urlPadrao} style={{ height: '200px'}} alt="Imagem de exemplo" />
        </>
    );
}