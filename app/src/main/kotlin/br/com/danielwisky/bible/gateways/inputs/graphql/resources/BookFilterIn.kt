package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.BookFilter

data class BookFilterIn(
    var name: String? = null,
    var nameLike: String? = null
) {

    fun toDomain(): BookFilter {
        return BookFilter(
            name = this.name,
            nameLike = this.nameLike
        )
    }
}
