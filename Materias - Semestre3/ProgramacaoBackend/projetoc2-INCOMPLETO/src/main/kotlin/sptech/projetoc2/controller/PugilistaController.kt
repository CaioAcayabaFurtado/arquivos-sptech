package sptech.projetoc2.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sptech.projetoc2.dtos.CartelPugilistaResponse
import sptech.projetoc2.entity.Pugilista
import sptech.projetoc2.repository.PugilistaRepository

@RestController
@RequestMapping("/pugilistas")
class PugilistaController(val repository: PugilistaRepository) {

    @PostMapping
    @Operation(summary = "Cadastra um novo pugilista e retorna o pugilista cadastrado.",
        description = "Retorna status 201 com o pugilista recém cadastrado no corpo.")
    fun post(@RequestBody novoPugilista: Pugilista): ResponseEntity<Pugilista> {
        val pugilista = repository.save(novoPugilista)
        return ResponseEntity.status(201).body(pugilista)
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Retorna um pugilista por código.",
        description = "Retorna status 200 com o pugilista encontrado no corpo, ou status 404 se o pugilista não for encontrado.")
    fun get(@PathVariable codigo: Int): ResponseEntity<Pugilista> {
        if (!repository.existsById(codigo)) {
            return ResponseEntity.status(404).build()

        }
        return ResponseEntity.status(200).body(repository.findById(codigo).get())
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista de pugilistas, podendo estar filtrada por 'nome' e/ou 'categoria' (ambos case insensitive).",
        description = """
        Se nem 'nome' nem 'categoria' forem informados, retorna todos os pugilistas. 
        Se apenas 'nome' for informado, retorna todos os pugilistas cujo nome contenha o valor informado. 
        Se apenas 'categoria' for informado, retorna todos os pugilistas da categoria informada (valo exato). 
        Se ambos forem informados, retorna todos os pugilistas cujo nome contenha o valor informado e pertençam à categoria informada (valor exato da categoria).
        """)
    fun get(@RequestParam nome: String?, @RequestParam categoria: String?): ResponseEntity<List<Pugilista>> {
        val pugilistas = mutableListOf<Pugilista>()
        if (nome != null) {
            if (categoria != null) {
                pugilistas.addAll(repository.findByNomeIgnoreCaseContainsAndCategoriaNomeIgnoreCase(nome, categoria))
            } else {
                pugilistas.addAll(repository.findByNomeIgnoreCaseContains(nome))
            }
        } else if (categoria != null) {
            pugilistas.addAll(repository.findByCategoriaNomeIgnoreCase(categoria))
        } else {
            pugilistas.addAll(repository.findAll())
        }

        return if (pugilistas.isEmpty()) ResponseEntity.status(204).build()
               else ResponseEntity.status(200).body(pugilistas)
    }

    @GetMapping("/aposentados")
    @Operation(summary = "Retorna uma lista de pugilistas aposentados.",
        description = "Retorna status 204 se não houver pugilistas aposentados ou status 200 com a lista de aposentados, caso contrário")
    fun getAposentados(): ResponseEntity<List<Pugilista>> {
        val pugilistas = mutableListOf<Pugilista>()
        pugilistas.addAll(repository.findByAposentado(true))
        return if (pugilistas.isEmpty()) ResponseEntity.status(204).build()
               else ResponseEntity.status(200).body(pugilistas)
    }

    @GetMapping("/nao-aposentados")
    @Operation(summary = "Retorna uma lista de pugilistas na ativa, ou seja, não aposentados.",
        description = "Retorna status 204 se não houver pugilistas não aposentados ou status 200 com a lista de não aposentados, caso contrário")
    fun getNaoAposentados(): ResponseEntity<List<Pugilista>> {
        val pugilistas = mutableListOf<Pugilista>()
        pugilistas.addAll(repository.findByAposentado(false))
        return if (pugilistas.isEmpty()) ResponseEntity.status(204).build()
               else ResponseEntity.status(200).body(pugilistas)
    }

    @GetMapping("/top3-nocauteadores")
    @Operation(summary = "Retorna uma lista dos top 3 pugilistas com mais vitórias por nocaute.",
        description = "Retorna status 204 se não houver pugilistas ou status 200 com a lista dos top 3 pugilistas, caso contrário")
    fun getTop3(): ResponseEntity<List<Pugilista>> {
        val pugilistas = repository.findTop3ByOrderByVitoriasNocauteDesc()
        return if (pugilistas.isEmpty()) ResponseEntity.status(204).build()
               else ResponseEntity.status(200).body(pugilistas)
    }

    @GetMapping("/cartel/{codigo}")
    @Operation(summary = "Retorna um cartel de pugilista por código.",
        description = "Retorna status 200 com o cartel de pugilista encontrado no corpo, ou status 404 se o pugilista não for encontrado.")
    fun getCartel(@PathVariable codigo: Int): ResponseEntity<CartelPugilistaResponse> {
        if (!repository.existsById(codigo)) {
            return ResponseEntity.status(404).build()
        }
        val cartel = repository.getCartel(codigo)
        return ResponseEntity.status(200).body(cartel)
    }


    @PutMapping("/{codigo}")
    @Operation(summary = "Atualiza um pugilista pelo seu código.",
        description = "Retorna status 200 com o pugilista atualizado no corpo se tudo ocorrer bem. Se 'codigo' informado não existir, retorna status 404 sem corpo.")
    fun update(@PathVariable codigo: Int, @RequestBody pugilista: Pugilista): ResponseEntity<Pugilista> {
        if (!repository.existsById(codigo)) {
            return ResponseEntity.status(404).build()
        }
        val pugilistaAtualizado = pugilista.copy(codigo = codigo) // este código está correto! não mexa!
        return ResponseEntity.status(200).body(pugilistaAtualizado)
    }

    @DeleteMapping("/{codigo}")
    @Operation(summary = "Excluir um pugilista pelo seu código.",
        description = "Retorna status 204 e sem corpo se tudo ocorrer bem. Se 'codigo' informado não existir, retorna status 404 sem corpo.")
    fun delete(@PathVariable codigo: Int): ResponseEntity<Void> {
            if (!repository.existsById(codigo)) {
            return ResponseEntity.status(404).build()
        }
        repository.deleteById(codigo)
        return ResponseEntity.status(204).build()
    }
}