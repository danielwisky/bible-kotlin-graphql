package br.com.danielwisky.bible.gateways

import br.com.danielwisky.bible.domains.Version
import br.com.danielwisky.bible.domains.VersionFilter
import org.springframework.data.domain.Page

interface VersionDataGateway {

    fun search(filter: VersionFilter?, first: Int?, after: String?): Page<Version>
}
