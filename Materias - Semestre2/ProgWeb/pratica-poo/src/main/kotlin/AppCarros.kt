fun main() {
    val carro = Carro(
        "Volvo",
        "XC5",
        ligado=false)
            // é possível reatribuir valores iniciais sem se preocupar com a ordem!(basta passar o nome deles)

    println("Estado do carro: ligado:${carro.ligado}")
    carro.ligar()
    println("Estado do carro: ligado:${carro.ligado}")
    println("Desligando carro...")
    carro.desligar()
    println("Carro ligado? ${carro.ligado}")

    carro.ligar() //ligar=true
    carro.acelerar() //+10
    carro.acelerar() //+10
    carro.acelerar() //+10
    carro.frear() //-10

    println(carro.descricao())

    carro.frear()
    carro.frear()
    carro.frear()

    println("Velocidade: ${carro.velocidade}")

    //exemplo do carro desligado
    carro.desligar()
    println(carro.descricao())
    carro.acelerar()
    carro.acelerar()
    println()
    println(carro.descricao())

    println(carro.caixaPreta())

    carro.exibirVelocidadeComTexto("Este carro esá em ${carro.velocidade}Km/h!!!")
}