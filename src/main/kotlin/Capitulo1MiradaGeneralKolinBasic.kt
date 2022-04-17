import java.util.TreeMap

data class Persona(
    private val nombre: String,
    val antiguedad: Int? = null,
    var isMarried: Boolean = false,
){
    public fun setNombre(): String{
        return this.nombre
    }
}

class Rectangulo(
    val alto: Int,
    val ancho: Int
) {
    val isSquare: Boolean
    get(){
        return alto == ancho
    }
}

/**
 * Al igual que en Java, las enumeraciones no son listas de valores:
 * puede declarar propiedades y métodos en las clases de enumeración. Así es como funciona.
 */
enum class Color {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    INDIGO,
    VIOLET,
}

/**
 *  si se define algún método en la clase enum, el punto y coma separa
 *  la lista de constantes de enumeración de las definiciones de método
 */
enum class Colorv2(
    val r: Int,
    val g: Int,
    val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 255, 0),
    YELLOW(75, 0, 6); //; xq separo las constantes de los metodos

    fun rgb() = (r * 256 + g) * 256 + b

    //uso:print(Colorv2.ORANGE.rgb())
}

//when es el Case de kotlin
fun getMnemo(color: Colorv2) =  when(color) {
    Colorv2.RED -> "Richard"
    Colorv2.ORANGE -> "Of"
    Colorv2.YELLOW -> "York"

    //uso: println(getWarmth(Color.ORANGE))
}

fun getWarmth(color: Color) = when(color) {
    Color.RED,
    Color.ORANGE,
    Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE,
    Color.INDIGO,
    Color.VIOLET -> "cold"

    //uso: println(getWarmth(Color.ORANGE))
}

fun mix(c1: Color, c2: Color) =
    when(setOf(c1,c2)) {
        setOf(Color.RED, Color.YELLOW) -> Colorv2.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Colorv2.ORANGE
        else -> throw Exception("No color")

        //uso: println(mix(Color.ORANGE,Color.ORANGE))
    }

/**
 * Si no se proporciona ningún argumento para la expresión when, la condición de rama es cualquier expresión booleana.
 * La función mixOptimized hace lo mismo que la mezcla anteriormente. Su ventaja es que no crea ningún objeto adicional,
 * pero el costo es que es más difícil de leer.
 */
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == Color.RED && c2 == Color.YELLOW) ||
        (c1 == Color.YELLOW && c2 == Color.RED) -> Color.ORANGE
        (c1 == Color.BLUE && c2 == Color.VIOLET) ||
        (c1 == Color.VIOLET && c2 == Color.BLUE) -> Color.ORANGE
        else -> throw Exception("No color")

        //uso: println(mix(Color.ORANGE,Color.ORANGE))
    }

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

//Uso de "in" para comprobar la pertenencia a la colección y al rango
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun inNotDigit(c: Char) = c !in '0'..'9'
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "it is a digit!"
    in 'a'..'z',
    in 'A'..'Z' -> "is a letter!"
    else -> "I don't know"
}


fun main(args: Array<String>) {
    /*
        val (from value):  referencia inmutable. Una variable declarada con val no se puede reasignar después
                           de inicializarla. Corresponde a una variable final en Java.

        var (de variable): referencia mutable. El valor de dicha variable se puede cambiar. Esta declaración
                           corresponde a una variable Java regular (no final).
    */
    //declaraciones de variables
    //si una variable no tiene un inicializador tiene que especificar el tipo, de lo contrario no es necesario
    val question: String = "The ultimate question of life the Universe, and Everything";
    val answer: Int = 42
    val message: String

    val lenguaje: ArrayList<String> = arrayListOf<String>("Java")
    lenguaje.add("Kotlin")

    val persona = listOf(
        Persona("Victor",null,true),
        Persona("Felipe", 3),
    )

    val p2 =  Persona("Felipe",5,false);
    println(p2.setNombre())

    val rectangulo = Rectangulo(10,11)
    println(rectangulo.isSquare)

    //busco la persona con mayor antiguedad de la lista
    val oldest = persona.maxByOrNull { it.antiguedad ?: 0 } //Operador Elvis (?:) retorna 0 si la edad es nula
    println("The oldest is: $oldest")

    println(max(12, 3))

    print(Colorv2.ORANGE.rgb())

    for(i in 1..100){
        println(fizzBuzz(i))
    }


    val binaryRepo = TreeMap<Char,String>()

    for(c in 'A'..'F'){
        val binary = Integer.toBinaryString(c.code)
        binaryRepo[c] = binary
    }

    for((i,binary) in binaryRepo){
        println("letter is equal binary: $i = $binary")
    }

    val list = listOf("10","11","1001")
    for((indice,elemento) in list.withIndex()){
        println("$indice $elemento")
    }

}

/**
 * => en kotlin el if es una expresion con un valor de resultado similar a un ternario
 * En Kotlin, if es una expresión, no una declaración. La diferencia entre una instrucción y una expresión
 * es que una expresión tiene un valor, que se puede usar como parte de otra expresión
 */
fun max(a:Int, b:Int): Int{
    return if(a > b) a else b
}

//Cuerpos de expresion se puede omitir el tipo de retorno del fun
fun max2(a: Int, b: Int): Int = if (a > b) a else b

fun max3(a: Int, b: Int) = if (a > b) a else b

