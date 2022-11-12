package com.example.androidproject.Kotlin

open class Lesson15 {

    companion object{
        @JvmStatic fun main(args: Array<String>) {
            val first = First()
            val first2 = First()

            val second = Second
            val second2 = Second
            print("${first.hashCode()} ${first2.hashCode()} \n")
            print("${second.hashCode()} ${second2.hashCode()}")
            print("")

            val first1 = First1().navigate1()
            val second1 = First1.walk()



        }
    }
}

class First{

    fun navigate(){
        print("navigating ...")
    }

}

object Second{

}

open class First1{

    fun navigate1(){
        print("navigate")
    }

    companion object{
        fun walk(){
            print("I'm walking")
        }
    }
}

