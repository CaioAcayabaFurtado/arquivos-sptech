package sptech.projeto04

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@RestController
@RequestMapping("/herois")
class HeroiController {

    val herois = mutableListOf<Heroi>(
        Heroi("Puri Puri", "A", 10_000,
            dataAposentadoria = LocalDate.parse("1995-12-19")),

        Heroi("Saitama", "C", 500_000),

        Heroi("King", "A", 300,
            dataAposentadoria = LocalDate.parse("2010-12-19")),

        Heroi("Cavaleiro sem licença", "C", 10,
            dataAposentadoria = LocalDate.parse("2010-12-19"))
    )

    var maisForte = Heroi("Superhomem", "S", 7_000_000)

    /*
    Quando retornamos objetos complexos, o Spring, por padrão
    converte ele em JSON
    @GetMapping -> indica que é chamado pelo método GET do protocolo HTTP
     */
    @GetMapping("/mais-forte")
    fun maisForte():Heroi {
        return maisForte
    }

    // Aqui vai retornar uma lista de JSONs
    /*
    Aqui usamos um recurso chamado Request Param ou Query Param
    Normalmente usamos ele para filtrar resultados.
    O primeiro Request Param sempre é precedido de ?
    Caso existam mais de 1, entre eles colocamos &

    Importante: Se há pelo menos 1 request param,
    dizemos que a URL tem uma Query String

    O nome do parâmetro na Query String, por padrão, é o mesmo nome do parâmtro da função
     */
    @GetMapping  //   /herois ou /herois?classe=A ou /herois?classe=C&forcaMinima=7000 ou /herois?forcaMinima=7000&classe=C ou /herois?forcaMinima=7000
    fun lista(@RequestParam(required = false) classe:String?,
              @RequestParam(required = false) forcaMinima:Int?):
            ResponseEntity<List<Heroi>> {
/*
ResponseEntity -> indica uma resposte de requisição
O tipo entre <> indica o tipo de dado caso de tudo certo
 */

        if (classe == null && forcaMinima == null) {
            if (herois.isEmpty()) {
// retornando uma resposta com status 204 e SEM corpo de resposta - build()
                return ResponseEntity.status(204).build()
            }
// retornando uma resposta com status 200 e a lista 'herois' no corpo da resposta
            return ResponseEntity.status(200).body(herois)
        }

        val filtraClasse = classe != null
        val filtraForca = forcaMinima != null

        val listaFiltrada = mutableListOf<Heroi>()

        if (filtraClasse && filtraForca) {
            listaFiltrada.addAll(herois.filter {
                it.classe == classe && it.forca!! >= forcaMinima!!
            })
        }
        else if (filtraClasse) {
            listaFiltrada.addAll(herois.filter{ it.classe == classe })
        } else {
            listaFiltrada.addAll(
                herois.filter { it.forca!! >= forcaMinima!! })
        }

        if (listaFiltrada.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(listaFiltrada)

    }

    /*@PostMapping("/{apelido}/{classe}/{forca}")
    fun criar(@PathVariable apelido:String, @PathVariable classe: String, @PathVariable forca: Int):String {
        val novoHeroi = Heroi(apelido, classe, forca)
        herois.add(novoHeroi)
        return "Herói cadastrado com sucesso"
    }*/

    /*
    Aqui criamos um EndPoint que só pode ser chamado pelo método POST do HTTP.
    Ele precisa que seja enviado um corpo de requisição JSON cujos campos sejam compatíveis com os da classe Herói
     */
    @PostMapping
    fun criar(@RequestBody novoHeroi: Heroi):ResponseEntity<Heroi> {
        herois.add(novoHeroi)
        return ResponseEntity.status(201).body(novoHeroi)
    }

/*
Aqui criamos um EndPoint que só pode ser chamado pelo método DELETE do HTTP.
 */
    @DeleteMapping("/{id}")
// versão que retorna 204 e sem corpo caso exclua
    fun excluir(@PathVariable id:Int):ResponseEntity<Void> {
// retorno ResponseEntity<Void> indica que nunca haverá corpo na resposta
        val idValido = id in 0..herois.size-1;

        if (idValido) {
            herois.removeAt(id)
        }

        return ResponseEntity.status(if (idValido) 204 else 404).build()
    }

    // versão que retorna 204 e sem corpo caso exclua
    fun excluirV2(@PathVariable id:Int):ResponseEntity<Heroi> {
        val idValido = id in 0..herois.size-1;

        if (idValido) {
            val heroiExcluido = herois.removeAt(id)
            return ResponseEntity.status(200).body(heroiExcluido)
        }

        return ResponseEntity.status(404).build()
    }

    @GetMapping("/{id}")
    fun recuperar(@PathVariable id:Int):ResponseEntity<Heroi> {
        if (id in 0..herois.size-1) {
            // id válido
            return ResponseEntity.status(200).body(herois[id])
        }
        // id inválido
        return ResponseEntity.status(404).build()
    }

    // versão com tratamento de exceção ao invés de validação do id
    fun recuperarV2(@PathVariable id:Int):ResponseEntity<Heroi> {
        try {
            return ResponseEntity.status(200).body(herois[id])
            // ou return ResponseEntity.ok(herois[id])
        } catch (excecao: IndexOutOfBoundsException) {
            return ResponseEntity.status(404).build()
        }
    }


/*
Aqui criamos um EndPoint que só pode ser chamado pelo método PUT do HTTP.
Ele precisa que seja enviado um "id" como path param
Ele também precisa que seja enviado um corpo de requisição JSON cujos campos sejam compatíveis com os da classe Herói
 */
    @PutMapping("/{id}")
    fun atualizar(@PathVariable id:Int, @RequestBody heroiAtualizado: Heroi):ResponseEntity<Heroi> {
        if (id in 0..herois.size-1) {
            herois[id] = heroiAtualizado
            return ResponseEntity.status(200).body(heroiAtualizado)
        }
        return ResponseEntity.status(404).build()
    }

    /*
    Aqui criamos um EndPoint que só pode ser chamado pelo método PATCH do HTTP.
     */
    @PatchMapping("/{id}/{novaClasse}")
    fun mudarClasse(@PathVariable id:Int, @PathVariable novaClasse:String): ResponseEntity<Heroi> {
        if (id in 0..herois.size-1) {
            herois[id].classe = novaClasse
            return ResponseEntity.status(200).body(herois[id])
        }
        return ResponseEntity.status(404).build()
    }

    @GetMapping("/aposentados")
    fun aposentados(@RequestParam aposentadoDesde:LocalDate): ResponseEntity<List<Heroi>> {

        val filtrados = herois.filter {
            it.dataAposentadoria != null
            && it.dataAposentadoria!! >= aposentadoDesde
        }

        if (filtrados.isEmpty()) {
            return ResponseEntity.status(204).build()
        }
        return ResponseEntity.status(200).body(filtrados)
    }
}