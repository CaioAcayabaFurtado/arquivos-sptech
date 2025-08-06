package sptech.projeto02

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/herois")
class HeroiControler {
    val herois = mutableListOf<Heroi>(
        Heroi("Puri Puri", "A", 10_000),
        Heroi("Saitama", "C", 500_000),
        Heroi("King", "A", 300),
    )
    var maisForte = Heroi("Superhomi","S",7_000_000)

    @GetMapping("/mais-forte")
    fun maisForte():Heroi{
        return maisForte
    }

    @GetMapping
    fun todos():List<Heroi>{
        return herois
    }


}