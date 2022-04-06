package br.com.danielwisky.bible.domains

data class VerseFilter(
    var version: String? = null,
    var book: String? = null,
    var chapter: Int? = null,
    var verseLike: String? = null,
)
