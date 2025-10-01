export default function Card() {
    return (
        <main className="border p-4 max-w-sm rounded-md flex flex-col gap-2">
            <header>
                <span className="text-[#616161] tracking-wide font-medium">
                    15 de Abril, 2025 - 18:35
                </span>
            </header>
            <section>
                <h2 className="font-bold text-[22px] ">Tailwind é sensacional!</h2>
                <p className="text-[#616161] font-medium">
                    Tailwind simplesmente mudou a maneira como eu construo interfaces! A
                    versatilidade e a liberdade para estilizar meus componentes são os
                    grandes diferenciais dessa ferramenta.
                </p>
            </section>
            <footer className="flex gap-2 items-center">
                <img
                    className="w-10 h-10 rounded-full"
                    src="https://tiermaker.com/images/chart/chart/naruto-classico--sem-figurantes--filosofisica--395963/capajpg.png"
                    alt="Imagem de perfil João Pedro"
                />
                <div>
                    <h3 className="text-[18px] font-semibold">João Pedro</h3>
                    <p className="text-sm font-medium text-[#616161]">Engenheiro de Software</p>
                </div>
            </footer>
        </main>
    );
}