package br.com.danielwisky.bible.gateways.inputs.graphql

import br.com.danielwisky.bible.gateways.ChapterDataGateway
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.ChapterFilterIn
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.ChapterOut
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
class ChapterGraphQL(private val bookDataGateway: ChapterDataGateway) {

    @QueryMapping
    fun chapters(
        @Argument filter: ChapterFilterIn?,
        @Argument first: Int?,
        @Argument after: String?,
    ): Connection<ChapterOut> {
        val page = bookDataGateway.search(filter?.toDomain(), first, decodeBase64(after))
        val edges = page.content
            .map { chapter ->
                DefaultEdge(ChapterOut(chapter), createCursorWith(chapter.id.toString()))
            }
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
