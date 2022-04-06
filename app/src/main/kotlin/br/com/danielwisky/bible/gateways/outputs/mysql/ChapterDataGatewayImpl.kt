package br.com.danielwisky.bible.gateways.outputs.mysql

import br.com.danielwisky.bible.domains.Chapter
import br.com.danielwisky.bible.domains.ChapterFilter
import br.com.danielwisky.bible.gateways.ChapterDataGateway
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.ChapterEntity
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.ChapterEntityRepository
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications.ChapterEntitySpecification.toSpec
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.stereotype.Component

@Component
class ChapterDataGatewayImpl(
    private val chapterEntityRepository: ChapterEntityRepository,
) : ChapterDataGateway {

    override fun search(filter: ChapterFilter?, first: Int?, after: String?): Page<Chapter> {

        return chapterEntityRepository.findAll(
            toSpec(filter, after),
            PageRequest.of(0, first ?: 20, ASC, "id")
        ).map(ChapterEntity::toDomain)
    }
}
