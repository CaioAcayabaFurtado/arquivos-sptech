package sptech.projeto03

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/herois")
class HeroiController {

    val herois = mutableListOf<Heroi>(
        Heroi("Puri Puri", "A", 10_000),
        Heroi("Saitama", "C", 500_000),
        Heroi("King", "A", 300),
        Heroi("Cavaleiro sem licença", "C", 10)
    )

    var maisForte = Heroi("Superhomem", "S", 7_000_000)

    /*
    Quando retornamos objetos complexos, o Spring, por padrão
    converte ele em JSON
     */
    @GetMapping("/mais-forte")
    fun maisForte():Heroi {
        return maisForte
    }

    // Aqui vai retornar uma lista de JSONs
    @GetMapping  //   /herois
    fun todos(
        // http://localhost:8080/herois?classe=A
        @RequestParam(required = false) classe:String?,
        @RequestParam(required = false) forcaMinima:Int?
    ):List<Heroi> {
        //if (classe == null && forcaMinima == null){
        //    return herois
        //}
        return herois.filter {
            //(classe != null && it.classe == classe)
            //        ||
            //(forcaMinima != null && it.forca!! >= forcaMinima)
            val filtrarClasse = classe != null
            val filtrarForca = forcaMinima != null

            if (filtrarClasse && filtrarForca){
                return herois.filter{
                    it.classe == classe && it.forca!! >= forcaMinima!!
                }
            }
            if (filtrarClasse) {
                return herois.filter {
                    it.classe == classe
                }
            }
            if (filtrarForca) {
                return herois.filter{it.forca!! >= forcaMinima!!}
            }
            return herois
        }
    }

    @PostMapping()
    fun criar(
        @RequestBody novoHeroi: Heroi
        //@PathVariable apelido:String,
        //@PathVariable classe:String,
        //@PathVariable forca:Int
    ):String{
        //val novoHeroi = Heroi(apelido,classe,forca)
        herois.add(novoHeroi)
        return "Herói cadastrado com sucesso"
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id:Int):String{
        herois.removeAt(id)
        return "Heroi excluido com sucesso"
    }

    @GetMapping("/{id}")
    fun localizar(@PathVariable id:Int):Heroi{
        return herois[id]
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id:Int,
        @RequestBody updtHeroi: Heroi
    ):String{
        herois[id] = updtHeroi
        return "Heroi atualizado com sucesso"
    }

    @PatchMapping ("/{id}/{novaClasse}")
    fun mudarClasse(@PathVariable id:Int, @PathVariable novaClasse:String):String{
        herois[id].classe = novaClasse
        return "Classe atualizada com sucesso"
    }

}