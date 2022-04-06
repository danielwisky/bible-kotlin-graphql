package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.ChapterFilter

data class ChapterFilterIn(
    var book: String? = null,
) {

    fun toDomain(): ChapterFilter {
        return ChapterFilter(
            book = this.book
        )
    }
}
