private fun checkIsCorrectSequence(sequenceString: String): Boolean {
    val correctSymbols = charArrayOf('[', ']', '{', '}', '(', ')')
    return sequenceString.all { it in correctSymbols }
}

private fun getMatchedBracket(bracket: Char): Char {
    return when (bracket) {
        ']' -> '['
        '}' -> '{'
        ')' -> '('
        else -> error("impossible event has happened")
    }
}

fun validate(sequenceString: String): Boolean {
    require(checkIsCorrectSequence(sequenceString)) { "Sequence contains incorrect characters" }
    val stack = ArrayDeque<Char>()
    val openBrackets = charArrayOf('[', '{', '(')
    for (sym in sequenceString) {
        when {
            sym in openBrackets -> {
                stack.add(sym)
                continue
            }
            stack.isEmpty() || stack.last() != getMatchedBracket(sym) -> return false
            else -> stack.removeLast()
        }
    }
    return stack.isEmpty()
}


fun main(args: Array<String>) {
    val sequenceString: String = checkNotNull(readLine()) {"Line should not be null"}
    println(validate(sequenceString))
}