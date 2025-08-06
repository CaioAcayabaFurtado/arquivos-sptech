// caso queira construir o atributo junto, passar sem "var" ou "val"
class Cidade(nome: String = "") {

    var nome: String = nome
        //        get() = field.uppercase() // exemplo simples
        get() {
            if (field.isBlank()) {
                return "(não informado)"
            }
            return field.uppercase()
        }
        set(value) {
            if (value.length >= 3) {
                field = value
            }
        }
}