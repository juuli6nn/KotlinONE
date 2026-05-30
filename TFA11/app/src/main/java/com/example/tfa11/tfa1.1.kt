package com.example.tfa11

fun main () {

    println("====================================")
    println(" BIODATA GENERATOR ")
    println("====================================")

    println("PERSONAL INFO");
    println("--------------------------");
    print("Enter First name: ");
    val firstName : String = readln()

    print("Enter Last name: ");
    val lastName : String = readln()

    print("Enter Middle name: ");
    val middleName : String = readln()

    print("Enter Birthday (YYYY-MM-DD): ");
    val birthDay : String = readln()

    print("Enter Gender: ");
    val gender : String = readln()

    print("Enter Address: ");
    val address : String = readln()

    println("");

    println("NUMBERS");
    println("--------------------------");
    print("Enter TIN (N/A if not applicable): ");
    val tinNumber = readln()

    print("Enter SSS (N/A if not applicable): ");
    val sssNumber = readln()

    print("Enter Philhealth (N/A if not applicable): ");
    val philNumber = readln()

    println("");

    println("SCHOOL");
    println("--------------------------");
    print("School Graduated From: ");
    val schoolGrad : String = readln()

    print("Year Graduated From: ");
    val yearGraduated : String = readln()

    println("");

    println("SKILLS/OTHERINFO");
    println("--------------------------");
    println("Enter 3 Skills" + "\n");
    print("Skill 1: ");
    val skillOne : String = readln()

    print("Skill 2: ");
    val skillTwo : String = readln()

    print("Skill 3: ");
    val skillThree : String = readln()

    println("")

    println("Enter 3 References " + "\n");
    print("Reference 1 (Name - Contact): ");
    val refOne : String = readln()

    print("Reference 1 (Name - Contact): ");
    val refTwo : String = readln()

    print("Reference 1 (Name - Contact): ");
    val refThree : String = readln()



    val parts = birthDay.split("-")

    var age = 0
    if (parts.size == 3) {
        val bY = parts[0].toInt()
        val bM = parts[1].toInt()
        val bD = parts[2].toInt()

        age = 2026 - bY

        if (4 < bM) {
            age--
        }
        else if (4 == bM && 29 < bD) {
            age--
        }
    }

// Biodata Profile Output

    println("")
    println("==================================================")
    println(" BIODATA ")
    println("==================================================")
    println(" NAME : $firstName $middleName $lastName")
    println(" BIRTHDAY : $birthDay")
    println(" AGE : $age years old")
    println(" GENDER : $gender")
    println(" ADDRESS : $address")
    println("--------------------------------------------------")
    println(" TIN : $tinNumber")
    println(" SSS : $sssNumber")
    println(" PhilHealth : $philNumber")
    println("--------------------------------------------------")
    println(" SCHOOL : $schoolGrad")
    println(" YEAR GRAD : $yearGraduated")
    println("--------------------------------------------------")
    println(" SKILLS:")
    println(" 1. $skillOne")
    println(" 2. $skillTwo")
    println(" 3. $skillThree")
    println("--------------------------------------------------")
    println(" REFERENCES:")
    println(" 1. $refOne")
    println(" 2. $refTwo")
    println(" 3. $refThree")
    println("==================================================")

}