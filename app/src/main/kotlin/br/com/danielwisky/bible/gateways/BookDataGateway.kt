package br.com.danielwisky.bible.gateways

import br.com.danielwisky.bible.domains.Book
import br.com.danielwisky.bible.domains.BookFilter
import org.springframework.data.domain.Page

interface BookDataGateway {

    fun search(filter: BookFilter?, first: Int?, after: String?): Page<Book>
}
