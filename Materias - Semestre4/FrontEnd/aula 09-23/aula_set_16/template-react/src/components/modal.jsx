export default function Modal({children, estilo}) {
    return (
        <>
        <div style={estilo}>
            {children}
        </div>
        </>
    );
}