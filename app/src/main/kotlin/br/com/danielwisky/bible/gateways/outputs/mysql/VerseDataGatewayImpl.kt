package br.com.danielwisky.bible.gateways.outputs.mysql

import br.com.danielwisky.bible.domains.Verse
import br.com.danielwisky.bible.domains.VerseFilter
import br.com.danielwisky.bible.gateways.VerseDataGateway
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.VerseEntity
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.VerseEntityRepository
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications.VerseEntitySpecification.toSpec
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.stereotype.Component

@Component
class VerseDataGatewayImpl(
    private val verseEntityRepository: VerseEntityRepository,
) : VerseDataGateway {

    override fun search(filter: VerseFilter?, first: Int?, after: String?): Page<Verse> {
        return verseEntityRepository.findAll(
            toSpec(filter, after),
            PageRequest.of(0, first ?: 20, ASC, "id")
        ).map(VerseEntity::toDomain)
    }
}
