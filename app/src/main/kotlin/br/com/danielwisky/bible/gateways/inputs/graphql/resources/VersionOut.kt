package br.com.danielwisky.bible.gateways.inputs.graphql.resources

import br.com.danielwisky.bible.domains.Version

data class VersionOut(
    var id: String? = null,
    var name: String? = null,
) {

    constructor(version: Version) : this(
        id = version.id,
        name = version.name
    )
}
