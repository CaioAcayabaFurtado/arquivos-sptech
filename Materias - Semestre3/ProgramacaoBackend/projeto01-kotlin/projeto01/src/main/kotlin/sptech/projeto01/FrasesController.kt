package sptech.projeto01

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FrasesController {

    @GetMapping("/cumprimentar")
    fun cumprimentar():String {
        return "Bem vindos à API do Caio"
    }

    @GetMapping("/boa-noite")
    fun boaNoite():String {
        return "Boa noite :)"
    }

    @GetMapping("/resultado/{nome}")
    fun resultado(@PathVariable nome:String):String {
        return "Olá, $nome, em breve terá seu resultado"
    }

    @GetMapping("/somar/{n1}/{n2}")
    fun somar(@PathVariable n1:Double, @PathVariable n2:Double): String{
        val resultado:Double = n1+n2
        // return "A soma entre $n1 e $n2 é ${n1+n2}"
        return "A soma entre $n1 e $n2 é ${resultado}"

    }

    @GetMapping("/frutas/{i}")
    fun frutas(@PathVariable i:Int): String{
        val lista:List<String> = listOf("laranja é laranja", "uva é roxa", "melancia é verde (por fora)")
        return lista[i]
    }
}