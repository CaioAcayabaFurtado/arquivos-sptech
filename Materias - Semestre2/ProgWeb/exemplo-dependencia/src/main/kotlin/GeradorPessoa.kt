import com.github.javafaker.Faker
import java.util.Locale

fun main() {
    val faker = Faker()

    //println(faker.superhero().power())
    //println(faker.pokemon().name())
    println("Digite um numero:")
    val numero = readln().toInt()
    if (numero >= 10) {
        print(faker.address().city())
    }
}