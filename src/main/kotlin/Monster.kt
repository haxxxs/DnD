import java.util.*

class Monster(
    name: String,
    maxHp: Double,
    hp: Double = maxHp,
    attack: Int,
    defense: Int,
    minDamage: Int,
    maxDamage: Int
) : Creature(name, maxHp, hp, attack, defense, minDamage, maxDamage) {

    companion object{
        fun createMonster(): Monster{
            val input = Scanner(System.`in`)
            println("Enter a name of monster: ")
            var name = input.nextLine()
            while (isNumeric(name)){
                println("Error, enter a string")
                name = input.nextLine()
            }

            println("Enter a HP of $name")
            var maxHp = input.nextLine()
            while (!isNumeric(maxHp)){
                println("Error, enter a digit")
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

            return Monster(
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
        println("Here are all the attributes of the monster: ")
        println("$name`s hp is $hp")
        println("$name`s attack is $attack")
        println("$name`s defense is $defense")
        println("$name`s min damage is $minDamage")
        println("$name`s min damage is $maxDamage\n")
    }

}
