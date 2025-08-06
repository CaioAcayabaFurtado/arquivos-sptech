package sptech.projeto02

import com.fasterxml.jackson.annotation.JsonIgnore

class Heroi(
    var apelido:String? = null,
    var classe:String? = null,
    var forca:Int? = null,
    @JsonIgnore var senha:String = "1234"
){
    fun getSalario():Double{
        // se for null é 0
        return forca?.times(1.5)?:0.0
    }
}
