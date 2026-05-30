package com.example.tfa11
class Volume {

    val sphere: (Double) -> Double = { r ->
        (4.0 / 3.0) * Math.PI * r * r * r
    }

    val pyramid: (Double, Double, Double) -> Double = { l, w, h ->
        (l * w * h) / 3.0
    }

    val rectangularPrism: (Double, Double, Double) -> Double = { l, w, h ->
        l * w * h
    }

    val cube: (Double) -> Double = { s ->
        s * s * s
    }

    // Compute function with varargs
    fun compute(vararg values: Double, operation: (DoubleArray) -> Double): Double {
        return operation(values)
    }
}

fun main() {
    val volume = Volume()

    println("Choose a shape:")
    println("1. Sphere")
    println("2. Pyramid")
    println("3. Rectangular Prism")
    println("4. Cube")

    print("Enter choice: ")
    val choice = readln().toInt()

    when (choice) {
        1 -> {
            print("Enter radius: ")
            val r = readln().toDouble()

            val result = volume.compute(r) {
                volume.sphere(it[0])
            }
            println("Volume of Sphere = $result")
        }

        2 -> {
            print("Enter length: ")
            val l = readln().toDouble()
            print("Enter width: ")
            val w = readln().toDouble()
            print("Enter height: ")
            val h = readln().toDouble()

            val result = volume.compute(l, w, h) {
                volume.pyramid(it[0], it[1], it[2])
            }
            println("Volume of Pyramid = $result")
        }

        3 -> {
            print("Enter length: ")
            val l = readln().toDouble()
            print("Enter width: ")
            val w = readln().toDouble()
            print("Enter height: ")
            val h = readln().toDouble()

            val result = volume.compute(l, w, h) {
                volume.rectangularPrism(it[0], it[1], it[2])
            }
            println("Volume of Rectangular Prism = $result")
        }

        4 -> {
            print("Enter side: ")
            val s = readln().toDouble()

            val result = volume.compute(s) {
                volume.cube(it[0])
            }
            println("Volume of Cube = $result")
        }

        else -> println("Invalid choice.")
    }
}