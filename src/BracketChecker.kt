class BracketChecker {
    private fun checkIsCorrectSequence(sequenceString: String) {
        val correctSymbols = charArrayOf('[',']','{','}','(',')')
        for (sym in sequenceString) {
            if (sym !in correctSymbols) {
                throw IllegalArgumentException("Sequence contains not correct sym")
            }
        }
    }

    companion object {
        fun check(sequenceString: String): Boolean {
            try {
                BracketChecker().checkIsCorrectSequence(sequenceString)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                return false
            }
            val stack = ArrayDeque<Char>()
            val openBrackets = charArrayOf('[', '{', '(')
            val linksOpenWithClosedBrackets = mapOf(']' to '[', '}' to '[', ')' to '(')
            for (sym in sequenceString) {
                if (sym in openBrackets) {
                    stack.add(sym)
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.last() != linksOpenWithClosedBrackets[sym]) {
                        return false;
                    }
                    stack.removeLast()
                }
            }
            return true
        }
    }

}

fun main(args: Array<String>) {
    val sequenceString : String? = readLine()
    if (sequenceString.equals(null)) {
        throw IllegalArgumentException("line should not be null")
    }
    println(BracketChecker.check(sequenceString!!))
}