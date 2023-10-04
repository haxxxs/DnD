fun isNumeric(toCheck: String): Boolean {
    return toCheck.all { char -> char.isDigit() }
}