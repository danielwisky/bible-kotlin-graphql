package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.Chapter

data class ChapterOut(
    var id: Int? = null,
    var book: BookOut? = null,
    var number: Int? = null,
) {

    constructor(chapter: Chapter) : this(
        id = chapter.id,
        book = chapter.book?.let(::BookOut),
        number = chapter.number
    )
}
