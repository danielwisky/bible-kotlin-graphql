package br.com.danielwisky.bible.domains

data class Chapter(
    var id: Int? = null,
    var book: Book? = null,
    var number: Int? = null,
)
