import com.github.javafaker.Faker
import java.util.Locale

// GeradorPessoa - File

fun main() {
    val faker = Faker(Locale("pt-br"))
//
//    print("Digite um número: ")
//    val numero = readln().toInt()
//
//    // Debug - adicionando ponto de parada para verificação
//    if (numero >= 10) {
//        println(faker.address().city())
//    }

    println(faker.name().fullName())
    println(faker.internet().emailAddress())

    val nome = faker.name().firstName()
    val sobrenome = faker.name().lastName()
    val dominio = faker.internet().domainName()

    println("nome completo: $nome $sobrenome")
    println("email: $nome.$sobrenome$dominio")

}