package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.VerseFilter

data class VerseFilterIn(
    var version: String? = null,
    var book: String? = null,
    var chapter: Int? = null,
    var verseLike: String? = null,
) {

    fun toDomain(): VerseFilter {
        return VerseFilter(
            version = this.version,
            book = this.book,
            chapter = this.chapter,
            verseLike = this.verseLike
        )
    }
}
