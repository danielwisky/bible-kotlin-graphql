package br.com.danielwisky.bible.gateways

import br.com.danielwisky.bible.domains.Verse
import br.com.danielwisky.bible.domains.VerseFilter
import org.springframework.data.domain.Page

interface VerseDataGateway {

    fun search(filter: VerseFilter?, first: Int?, after: String?): Page<Verse>
}
