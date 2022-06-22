package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.Book

data class BookOut(
    var id: String? = null,
    var name: String? = null,
) {

    constructor(book: Book) : this(
        id = book.id,
        name = book.name
    )
}
