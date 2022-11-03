package com.example.androidproject

class TypeClass {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val child: Child = Child()

            val parent2 = child as Parent2
            if (parent2 is Parent2){
                parent2.walk()
            }
            if (parent2 is Child){
                parent2.sleep()
                parent2.walk()
            }
        }
    }
}

open class Parent2 {

    open fun walk() {
        print("Parent is walking...")
    }


}

class Child : Parent2() {

    override fun walk() {
        print("Child is walking...")
    }

    fun sleep() {
        print("Child is sleeping...")
    }
}