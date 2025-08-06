package sptech.projeto01

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContadorControler {
    var contagem = 0

    @GetMapping("/contador-acessos")
    fun contar():String{
        contagem++
        return "Você me chamou $contagem vezes"
    }
}