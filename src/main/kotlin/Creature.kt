open class Creature(
    var name:String,
    val maxHp: Double,
    var hp: Double = maxHp,
    var attack: Int,
    var defense: Int,
    val minDamage: Int,
    val maxDamage: Int
){

    fun attack(target: Creature){
        val modAttack = attack - target.defense + 1
        val cnt = if (modAttack < 1) 1 else modAttack
        println("Rolling a chance to attack...")
        val attackRolls = rollNTimes(cnt)
        val successfulAttack = attackRolls.firstOrNull { it in listOf(5,6) }
        val missAttack = attackRolls.last()
        if (successfulAttack != null){
            println("Got a $successfulAttack - successful attack")
            val damage = (minDamage..maxDamage).random()
            target.takeDamage(damage)
        }
        else{
            println("Got a $missAttack - miss attack")
            println()
        }
    }

    private fun rollNTimes(cnt: Int): MutableList<Int>{
        val list = mutableListOf<Int>()
        for (i in 1..cnt) {
            val res = (1..6).random()
            list.add(res)
        }
        return list
    }

    private fun takeDamage(damage: Int){
        hp -= damage
        if (hp <= 0.0){
            hp = 0.0
            println("$name take $damage and he is dead")
        }
        else{
            println("$name take $damage damage. Now he is have $hp HP")
        }
    }
}