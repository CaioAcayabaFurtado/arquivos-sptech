class Produto() {

    var nome:String=""
        set(value) {
            if (value.length<4){
                field="N/D"
            } else{
                field=value
            }
        }
        get(){
            return field.uppercase()
        }

    var preco:Double=0.0
        set(value) {
            if (value>=0.01) {
                field = value
            }
        }

    var brinde:Boolean=false
        set(value){
            if (value == true){
                preco = 0.01
            }
        }
}
