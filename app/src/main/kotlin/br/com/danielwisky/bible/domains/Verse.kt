package br.com.danielwisky.bible.domains

data class Verse(
    var id: Int? = null,
    var version: Version? = null,
    var book: Book? = null,
    var chapter: Int? = null,
    var number: Int? = null,
    var verse: String? = null,
)
