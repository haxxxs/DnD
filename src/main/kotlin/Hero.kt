import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round
import kotlin.system.exitProcess

class Hero(
    name:String,
    maxHp: Double,
    hp: Double = maxHp,
    attack: Int,
    defense: Int,
    minDamage: Int,
    maxDamage: Int,
    var cntOfPot: Int = 4
) : Creature(name, maxHp, hp, attack, defense, minDamage, maxDamage){

    companion object{
        fun createHero(): Hero{
            val input = Scanner(System.`in`)
            println("Enter a name of hero: ")
            var name = input.nextLine()
            while (isNumeric(name)){
                println("Error, enter a string")
                name = input.nextLine()
            }

            println("Enter a HP of $name(a digit greater than 0)")
            var maxHp = input.nextLine()
            while (!isNumeric(maxHp)){
                println("Error, enter a digit greater than 0")
                maxHp = input.nextLine()
            }

            println("Enter a Attack of $name (a digit from 1 to 30)")
            var attack = input.nextLine()
            while (!isNumeric(attack) || attack.toInt() < 1 || attack.toInt() > 30){
                println("Error, enter a digit from 1 to 30")
                attack = input.nextLine()
            }

            println("Enter a Defense of $name (a digit form 1 to 30)")
            var defense = input.nextLine()
            while (!isNumeric(defense) || defense.toInt() < 1 || defense.toInt() > 30) {
                println("Error, enter a digit from 1 to 30")
                defense = input.nextLine()
            }

            println("Enter a min damage of $name")
            var minDamage = input.nextLine()
            while (!isNumeric(minDamage) || minDamage.toInt() <= 0) {
                println("Error, enter a digit that greater than 0")
                minDamage = input.nextLine()
            }

            println("Enter a max damage of $name")
            var maxDamage = input.nextLine()
            while (!isNumeric(maxDamage) || maxDamage.toInt() <= 0 || maxDamage.toInt() <= minDamage.toInt()) {
                println("Error, enter a digit that greater than 0 and at least 1 more than min damage")
                maxDamage = input.nextLine()
            }

            return Hero(
                name = name,
                maxHp = maxHp.toDouble(),
                attack = attack.toInt(),
                defense = defense.toInt(),
                minDamage = minDamage.toInt(),
                maxDamage = maxDamage.toInt()
            )
        }
    }

    fun showInfo() {
        println("Here are all the attributes of the hero: ")
        println("$name`s hp is $hp")
        println("$name`s attack is $attack")
        println("$name`s defense is $defense")
        println("$name`s damage is $minDamage - $maxDamage")
        println("Count of potions $cntOfPot\n")
    }

    fun roundsAttack(monster: Monster){
        println("Enter a count of rounds that $name attack ${monster.name}")
        val input = Scanner(System.`in`)
        val x = input.nextInt()
        for (i in 1..x){
            if (hp > 0 && monster.hp > 0){
                println("round $i")
                println("\n$name attacks ${monster.name}\n")
                attack(monster)
                if (monster.hp > 0){
                    println("\n${monster.name} attacks $name\n")
                    monster.attack(this)
                }
                if (cntOfPot > 0 && monster.hp > 0 && hp < maxHp){
                    println("Do you want to heal? Y or N")
                    val input = Scanner(System.`in`)
                    if (input.nextLine() == "Y"){
                        hp += round(maxHp / 100 * 30)
                        if (hp > maxHp){
                            hp = maxHp
                        }
                        cntOfPot -= 1
                        println("Now you have $hp HP and u have $cntOfPot more potions for heal")

                    }
                }
                if (cntOfPot <= 0) {
                    println("Potions is gone")
                    val input = input.nextLine()
                }
            }

        }
        if (hp > 0 && monster.hp > 0){
            println("No one died during this battle")
            println("Do you want to continue the fight? (Y or N)")
            val input = Scanner(System.`in`)
            if (input.nextLine() == "Y"){
                this.roundsAttack(monster)
            }
            else {
                return
            }
        }

        if (hp == 0.0 || monster.hp == 0.0){
            println("Would you like to do the fight again with the same attributes? (Y or N)")
            val input = Scanner(System.`in`)
            if (input.nextLine() == "Y"){
                hp = maxHp
                monster.hp = maxHp
                cntOfPot = 4
                this.roundsAttack(monster)
            }
            else{
                println("Don't you want to start from the beginning? (Y or N)")
                if (input.nextLine() == "Y"){
                    val hero = createHero()
                    val monster = Monster.createMonster()
                    hero.showInfo()
                    monster.showInfo()
                    hero.roundsAttack(monster)
                }
                else{
                    return
                }
            }
        }
    }
}