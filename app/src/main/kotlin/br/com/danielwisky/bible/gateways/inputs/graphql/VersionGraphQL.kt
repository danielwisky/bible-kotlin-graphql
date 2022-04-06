package br.com.danielwisky.bible.gateways.inputs.graphql

import br.com.danielwisky.bible.gateways.VersionDataGateway
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.VersionFilterIn
import br.com.danielwisky.bible.gateways.inputs.graphql.resources.VersionOut
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
class VersionGraphQL(private val versionDataGateway: VersionDataGateway) : GraphQLQueryResolver {

    fun versions(
        filter: VersionFilterIn?,
        first: Int?,
        after: String?,
    ): Connection<VersionOut> {
        val page = versionDataGateway.search(filter?.toDomain(), first, decodeBase64(after))
        val edges = page.content
            .map { version -> DefaultEdge(VersionOut(version), createCursorWith(version.id)) }
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
