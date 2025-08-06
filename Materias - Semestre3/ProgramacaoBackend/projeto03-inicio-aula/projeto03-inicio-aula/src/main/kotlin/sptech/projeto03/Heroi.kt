package sptech.projeto03

import com.fasterxml.jackson.annotation.JsonIgnore
/*
Para que yum atributo não seja exposto no JSON gerado
Podemos:
1 - marcar ele como private (desvantagem: nem no código teremos acesso público a ele)
2 - anotar o atributo com @JsonIgnore
 */
class Heroi (
    var apelido: String? = null,
    var classe: String? = null,
    var forca: Int? = null,
    private var senha:String = "1234",
    @JsonIgnore var patrimonio:Double = 0.0
) {
    /*
    Aqui criamos um método que retorna um informação.
    Isso é chamado de
    - Campo virtual
    - Campo calculado
    - Atributo virtual
    - Atributo calculado

    Este campo, no JSON aparecerá como "salario"
    se fosse getIndentidadeSecreta() -> "identidadeSecreta"
     */
    fun getSalario():Double {
        // o salário é 1 forca x 1.5.
        // Se a forca for null, o salário é 0.0
        return forca?.times(1.5) ?: 0.0;
    }
}
