package com.example.firstkotlinproject

fun main() {
    print("Enter Name: ")
    val Name: String = readln()
    print("Enter Birthyear: ")
    val BirthYear = readln().toInt()

    println("")
    println("NAME: $Name!")
    println("AGE: ${2026 - BirthYear}")
}

