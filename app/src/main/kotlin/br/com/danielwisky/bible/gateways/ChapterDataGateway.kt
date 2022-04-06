package br.com.danielwisky.bible.gateways

import br.com.danielwisky.bible.domains.Chapter
import br.com.danielwisky.bible.domains.ChapterFilter
import org.springframework.data.domain.Page

interface ChapterDataGateway {

    fun search(filter: ChapterFilter?, first: Int?, after: String?): Page<Chapter>
}
