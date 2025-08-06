class Relogio (
    var modelo:String,
    var preco:Double,
    var horas:Int,
    var minutos:Int,
    var segundos:Int
) {

    fun zerar(){
        horas = 0
        minutos = 0
        segundos = 0
    }

    fun validar(){
        if (horas >= 24 || minutos > 60 || segundos > 60 || horas < 0 || minutos < 0 || segundos < 0) {
            if (horas >= 24 || horas < 0) {
                println("Valor de horas inválido. Ajustado!")
                horas = 0
            }
            if (minutos >= 60 || minutos < 0) {
                println("Valor de horas inválido. Ajustado!")
                minutos = 0
                //horas += 1
            }
            if (segundos >= 60 || segundos < 0) {
                println("Valor de horas inválido. Ajustado!")
                segundos = 0
                //minutos += 1
            }
        } else {
            println("Todos os valores ajustados!")
        }
    }

    fun verHora():String{
        validar()
        return"""
            ${"%02d".format(horas)}:${"%02d".format(minutos)}:${"%02d".format(segundos)}
        """.trimIndent()
    }

    fun descricaoRelogio():String{
        return"""
            Modelo: $modelo
            Preço: $preco
        """.trimIndent()
    }


}