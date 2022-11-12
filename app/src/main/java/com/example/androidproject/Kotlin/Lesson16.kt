package com.example.androidproject.Kotlin

class Lesson16 {

    val nullable: Int? = null

  //  val number: Int = null
    val name: String? = null
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val values = Values(0,)
            print(values.name)

            val lesson16 = Lesson16()
            var nonNullableValue: Int = 0 // не нал переменная

            val nullableValue: Int? = null// это нал переменная

            if (nullableValue != null){ // проверяю что не нал наша нал переменная
                nonNullableValue = nullableValue
        }else {

                //      nonNullableValue = nullableValue // не могу присвоить нал не нал переменной
            }
            //2 способ проверка на null
            nullableValue?.let{
                nonNullableValue = it
            }
            // 3 способ проверки
            nonNullableValue = nullableValue ?: 0
            println(nonNullableValue)
                // 4 способ
            nonNullableValue = nullableValue !!

            // 5 способ
            val outer = Outer(Inner("value in inner"))
            val value:String = outer.inner?.value?:" "
        }
    }
}

data class Values(
    val number: Int,
    val name: String? = "have no string"
)
data class Outer(val inner: Inner?)
data class Inner(val value: String)