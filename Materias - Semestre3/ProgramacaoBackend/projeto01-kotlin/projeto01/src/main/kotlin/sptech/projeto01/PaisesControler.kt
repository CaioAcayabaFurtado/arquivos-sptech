package sptech.projeto01

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/paises")
class PaisesControler {
    var paises = mutableListOf("Brasil", "México", "Peru")

    @GetMapping
    fun listarTodos():String{
        return "${paises.size} países cadastrados: $paises"
    }

    @GetMapping("/{i}")
    fun listarId(@PathVariable i:Int):String{
        if ( i <= paises.size && i > 0){
            return "País encontrado: ${paises[i]}"
        } else {
            return "ID inválido"
        }
    }

    @GetMapping("/novo/{nomePais}")
    fun listaAdd(@PathVariable nomePais:String):String{
        paises.add(nomePais)
        return "País $nomePais adicionado com sucesso!"
    }

    @GetMapping("/excluir/{i}")
    fun listaRemove(@PathVariable i: Int):String{
        if ( i <= paises.size && i > 0){
            paises.remove(paises[i])
            return "País excluido com sucesso!"
        } else {
            return "ID inválido"
        }
    }

    @GetMapping("/editar/{i}/{novoValor}")
    fun listaRemove(@PathVariable i: Int, @PathVariable novoValor:String):String{
        if ( i <= paises.size && i > 0){
            val paisAntigo = paises[i]
            paises[i] = novoValor
            return "País $paisAntigo agora se chama ${paises[i]}"
        } else {
            return "ID inválido"
        }
    }

}