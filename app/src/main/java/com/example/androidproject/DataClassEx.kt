package com.example.androidproject

class DataClassEx {
    lateinit var wallet: Wallet

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
          //  val country1 = Country("Minsk", "Smolachkova")
          //  val country2 = Country2("Brest", "Don' t know")
          //  println(country1)
          //  println(country2)

            val dataClassEx = DataClassEx()

            repeat(10){
                val raandom = (1..2).random()
                if(raandom==2 ){
                    dataClassEx.wallet= Wallet.Dollar(10,1)
                }else{
                    dataClassEx.wallet = Wallet.Euro(20,2)
                }
                when(dataClassEx.wallet){
                    is Wallet.Dollar->{
                        println((dataClassEx.wallet as Wallet.Dollar).buy.toString()+"dollar")
                    }
                    is Wallet.Euro->{
                        println((dataClassEx.wallet as Wallet.Euro).buy.toString()+"euro")
                    }
                }
                val wallet2: Wallet = Wallet.Dollar(10,1)

                when(wallet2){
                    is Wallet.Dollar -> {}
                    is Wallet.Euro -> {}
                }
            }
        }
    }
}

data class Country(val city: String, val address: String)
class Country2(val city: String, val address: String)

sealed class Wallet {
    data class Euro(val buy: Int, val sell: Int) : Wallet()

    data class Dollar(val buy: Int, val sell: Int) : Wallet()

}