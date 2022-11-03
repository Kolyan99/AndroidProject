package com.example.androidproject

private const val Zero_Two = 0

fun outerFun(){
    println("I'm an outer fun")
}
 open class Lesson15 {

     val country: String = "Belarus"
     var city = "Minsk"
     lateinit var adress: String

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val kotlinClass = KotlinClass("Aryna" , 22)
            val kotlinClass2 = KotlinClass("country", "city", "adress")
            println("${kotlinClass2.city} ${kotlinClass2.coutry}${kotlinClass2.address}")

           val string = kotlinClass.getNameAndAge()
            println(string)

            val lesson15 = Lesson15()
            lesson15.city = "Brest"
            lesson15.adress = "Smolechkova 16"
            println("${lesson15.adress}")

            lesson15.returnBoolean()
            outerFun()// статическая функция
        }
    }
    // fun emptyFun(): Unit = println("I'm an empty fun")
     fun emptyFun(): Unit{
         println("I'm an empty fun")
     }

     fun returnBoolean(): Boolean{
         return true
     }
}
// Свойство класса
class KotlinClass(val name: String, var age: Int): Parent(), Behavior{

    public var coutry: String =""
    public var city: String = ""
    public var address: String = ""

   constructor(): this("", 1)
    constructor(country: String, city: String, address: String) : this(){
        this.coutry = coutry
        this.city = city
        this.address = address
    }
// Параметры класса
    fun getNameAndAge(): String{
        return "$name $age"
    }

    override fun getHairColor() {
        super.getHairColor()
    }

    override fun getEyeColor() {
        TODO("Not yet implemented")
    }
}


open class Parent{

  open  fun getHairColor(){

    }

}

interface Behavior{

    fun getEyeColor()

}