package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.VersionFilter

data class VersionFilterIn(
    var name: String? = null,
    var nameLike: String? = null,
) {

    fun toDomain(): VersionFilter {
        return VersionFilter(
            name = this.name,
            nameLike = this.nameLike
        )
    }
}
