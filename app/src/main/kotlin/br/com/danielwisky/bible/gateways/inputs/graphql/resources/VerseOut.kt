package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.Verse

data class VerseOut(
    var id: Int? = null,
    var version: VersionOut? = null,
    var book: BookOut? = null,
    var chapter: Int? = null,
    var number: Int? = null,
    var verse: String? = null,
) {

    constructor(verse: Verse) : this(
        id = verse.id,
        version = verse.version?.let(::VersionOut),
        book = verse.book?.let(::BookOut),
        chapter = verse.chapter,
        number = verse.number,
        verse = verse.verse
    )
}
