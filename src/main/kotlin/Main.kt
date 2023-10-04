fun main() {
    val hero = Hero.createHero()
    val monster = Monster.createMonster()
    hero.showInfo()
    monster.showInfo()
    hero.roundsAttack(monster)
}





