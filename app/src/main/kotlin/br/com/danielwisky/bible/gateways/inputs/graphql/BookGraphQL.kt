package br.com.danielwisky.bible.gateways.inputs.graphql

import br.com.danielwisky.bible.gateways.BookDataGateway
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.BookFilterIn
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.BookOut
import br.com.danielwisky.bible.utils.CursorUtils.createCursorWith
import br.com.danielwisky.bible.utils.CursorUtils.decodeBase64
import br.com.danielwisky.bible.utils.CursorUtils.getFirstCursorFrom
import br.com.danielwisky.bible.utils.CursorUtils.getLastCursorFrom
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.relay.Connection
import graphql.relay.DefaultConnection
import graphql.relay.DefaultEdge
import graphql.relay.DefaultPageInfo
import org.springframework.stereotype.Component

@Component
class BookGraphQL(private val bookDataGateway: BookDataGateway) : GraphQLQueryResolver {

    fun books(
        filter: BookFilterIn?,
        first: Int?,
        after: String?,
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
