package br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications

import br.com.danielwisky.bible.domains.BookFilter
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.BookEntity
import br.com.danielwisky.bible.utils.CriteriaUtils.addEqualConditionIfNotNull
import br.com.danielwisky.bible.utils.CriteriaUtils.addGreaterThanConditionIfNotNull
import br.com.danielwisky.bible.utils.CriteriaUtils.addLikeConditionIfNotBlank
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

object BookEntitySpecification {

    fun toSpec(filter: BookFilter?, after: String?): Specification<BookEntity> {
        return Specification { root: Root<BookEntity>, _, builder: CriteriaBuilder ->
            val predicates: MutableList<Predicate> = ArrayList()

            addGreaterThanConditionIfNotNull(builder, predicates, after, root.get("id"))
            addEqualConditionIfNotNull(builder, predicates, filter?.name, root.get("name"))
            addLikeConditionIfNotBlank(builder, predicates, filter?.nameLike, root.get("name"))

            builder.and(*predicates.toTypedArray())
        }
    }
}
