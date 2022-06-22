package br.com.danielwisky.bible.gateways.inputs.graphql

import br.com.danielwisky.bible.gateways.BookDataGateway
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.BookFilterIn
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.BookOut
import br.com.danielwisky.bible.utils.CursorUtils.createCursorWith
import br.com.danielwisky.bible.utils.CursorUtils.decodeBase64
import br.com.danielwisky.bible.utils.CursorUtils.getFirstCursorFrom
import br.com.danielwisky.bible.utils.CursorUtils.getLastCursorFrom
import graphql.relay.Connection
import graphql.relay.DefaultConnection
import graphql.relay.DefaultEdge
import graphql.relay.DefaultPageInfo
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class BookGraphQL(private val bookDataGateway: BookDataGateway) {

    @QueryMapping
    fun books(
        @Argument("filter") filter: BookFilterIn?,
        @Argument("first") first: Int?,
        @Argument("after") after: String?,
    ): Connection<BookOut> {
        val page = bookDataGateway.search(filter?.toDomain(), first, decodeBase64(after))
        val edges = page.content
            .map { book -> DefaultEdge(BookOut(book), createCursorWith(book.id)) }
        val pageInfo =
            DefaultPageInfo(
                getFirstCursorFrom(edges),
                getLastCursorFrom(edges),
                after.isNullOrEmpty(),
                page.hasNext()
            )

        return DefaultConnection(edges, pageInfo)
    }
}
