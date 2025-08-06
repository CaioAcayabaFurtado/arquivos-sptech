//caso queira construir
class Cidade(nome:String="") {
    var nome:String = nome
        //get() = field.uppercase()
        get(){
            if (field.isBlank()){
                return "(não informado)"
            }
            return field.uppercase()
        }
        set(value){
            if (value.length>=3){
                field=value
            }
        }
}