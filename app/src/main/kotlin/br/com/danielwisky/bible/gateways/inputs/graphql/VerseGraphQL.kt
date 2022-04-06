package br.com.danielwisky.bible.gateways.inputs.graphql

import br.com.danielwisky.bible.gateways.VerseDataGateway
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.VerseFilterIn
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.VerseOut
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
class VerseGraphQL(private val verseDataGateway: VerseDataGateway) : GraphQLQueryResolver {

    fun verses(
        filter: VerseFilterIn?,
        first: Int?,
        after: String?,
    ): Connection<VerseOut> {
        val page = verseDataGateway.search(filter?.toDomain(), first, decodeBase64(after))
        val edges = page.content
            .map { verse -> DefaultEdge(VerseOut(verse), createCursorWith(verse.id.toString())) }
        val pageInfo =
            DefaultPageInfo(
                getFirstCursorFrom(edges),
                getLastCursorFrom(edges),
                !after.isNullOrEmpty(),
                page.hasNext()
            )

        return DefaultConnection(edges, pageInfo)
    }
}
