package br.com.danielwisky.bible.gateways.outputs.mysql

import br.com.danielwisky.bible.domains.Book
import br.com.danielwisky.bible.domains.BookFilter
import br.com.danielwisky.bible.gateways.BookDataGateway
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.BookEntity
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.BookEntityRepository
import br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications.BookEntitySpecification.toSpec
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction.ASC
import org.springframework.stereotype.Component

@Component
class BookDataGatewayImpl(
    private val bookEntityRepository: BookEntityRepository
) : BookDataGateway {

    override fun search(filter: BookFilter?, first: Int?, after: String?): Page<Book> {
        return bookEntityRepository.findAll(
            toSpec(filter, after),
            PageRequest.of(0, first ?: 20, ASC, "id")
        ).map(BookEntity::toDomain)
    }
}
