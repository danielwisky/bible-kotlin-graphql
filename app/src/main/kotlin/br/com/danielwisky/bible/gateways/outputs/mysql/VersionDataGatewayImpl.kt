package br.com.danielwisky.bible.gateways.outputs.mysql

import br.com.danielwisky.bible.domains.Version
import br.com.danielwisky.bible.domains.VersionFilter
import br.com.danielwisky.bible.gateways.VersionDataGateway
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.VersionEntity
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.VersionEntityRepository
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications.VersionEntitySpecification.toSpec
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.stereotype.Component

@Component
class VersionDataGatewayImpl(
    private val versionEntityRepository: VersionEntityRepository,
) : VersionDataGateway {

    override fun search(filter: VersionFilter?, first: Int?, after: String?): Page<Version> {
        return versionEntityRepository.findAll(
            toSpec(filter, after),
            PageRequest.of(0, first ?: 20, ASC, "id")
        ).map(VersionEntity::toDomain)
    }
}
