import java.util.Scanner
import kotlin.math.round

fun main() {
    val hero = Hero()
    val monster = Monster()
    println(hero.printInfo())
    println(monster.printInfo())
    println(hero.showInfo())
    println(monster.showInfo())
    hero.roundsHeroAttackMonster(hero, monster)
}

open class Entity (
    var name:String?,
    var HP: Double?,
    var Attack: Int?,
    var Defense: Int?,
    var cntOfPot: Int?
){
    constructor() : this(null,null, null, null,4)

    open fun printInfo() {
        val input = Scanner(System.`in`)
        name = input.nextLine()
        println("Enter a HP of $name")
        HP = input.nextDouble()
        println("Enter a Attack of $name (a digit from 1 to 30)")
        Attack = input.nextInt()

        if (Attack!! < 1 || Attack!! > 30) {
            println("Error, enter a digit from 1 to 30")
            Attack = input.nextInt()
        }
        println("Enter a Defense of $name (a digit form 1 to 30)")
        Defense = input.nextInt()
        if (Defense!! < 1 || Defense!! > 30) {
            println("Error, enter a digit from 1 to 30")
            Defense = input.nextInt()
        }
    }


    fun roundsHeroAttackMonster(hero: Hero,monster: Monster){
        println("Enter a count of rounds that ${hero.name} attack ${monster.name}")
        val input = Scanner(System.`in`)
        val x = input.nextInt()
        val maxValue = hero.HP
        var cnt1 = 0
        for (i in 1..x){
            if (hero.HP!! > 0 && monster.HP!! > 0){
                println("round $i")
                println()
                if (hero.HP!! > 0){
                    println("${hero.name} attacks ${monster.name}")
                    println()
                    hero.attack(monster)
                }
                if (monster.HP!! > 0){
                    println()
                    println("${monster.name} attacks ${hero.name}")
                    println()
                    monster.attack(hero)
                }
                if (cntOfPot!! > 0){
                    println("Do u want to heal? Y or N")
                }
                if (cntOfPot!! <= 0 && cnt1 < 1){
                    println("Potions is gone, good luck")
                    cnt1 += 1
                }
                val input = Scanner(System.`in`)
                if (input.nextLine() == "Y"){
                    hero.HP = hero.HP?.plus(
                        round(hero.HP!! / 100 * 30))
                    if (hero.HP!! > maxValue!!){
                        hero.HP = maxValue
                    }
                    cntOfPot = cntOfPot?.minus(1)
                    println("Now u have ${hero.HP} HP and u have $cntOfPot more potions for heal")
                }
            }
        }
        if (hero.HP!! > 0 && monster.HP!! > 0){
            println("No one died during this battle")
        }
    }


    fun attack(target: Entity){
        val modAttack = (Attack ?: 0 ) - (target.Defense ?: 0) + 1
        val cnt = if (modAttack < 1) 1 else modAttack
        println("Rolling a chance to attack...")
        val chance = (5..6).random()
        val chance1 = (1..4).random()
        val confirmAttack = roll(cnt)
        if (confirmAttack){
            println("Got a $chance - successful attack")
            val damage = (1..6).random()
            target.takeDamage(target.name, damage)
        }
        else{
            println("Got a $chance1 - miss attack")
            println()
        }

    }

    private fun roll(cnt: Int): Boolean{
        for (i in 1..cnt){
            val res = (1..6).random()
            if (res == 5 || res == 6){
                return true
            }
        }
        return false
    }

    private fun takeDamage(name: String?, damage: Int){
        HP = (HP ?: 0.0) - damage
        if (HP!! <= 0.0){
            HP = 0.0
            println("$name is dead")
        }
        else{
            println("$name take $damage. Now he is have $HP HP")
        }
    }
}

class Monster : Entity() {

    override fun printInfo() {
        println("Enter an all attribute of a Monster: ")
        println("Enter a name of Monster")
        super.printInfo()
    }
    fun showInfo() {
        println("HP of $name equals to $HP")
        println("Attack of $name equals to $Attack")
        println("Defense of $name equals to $Defense")
    }
}

class Hero : Entity(){
    override fun printInfo() {
        println("Enter an all attribute of a Hero: ")
        println("Enter a name of Hero")
        super.printInfo()
    }

    fun showInfo() {
        println("HP of $name equals to $HP")
        println("Attack of $name equals to $Attack")
        println("Defense of $name equals to $Defense")
    }
}





