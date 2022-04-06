package br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications

import br.com.danielwisky.bible.domains.ChapterFilter
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.ChapterEntity
import br.com.danielwisky.bible.utils.CriteriaUtils.addEqualConditionIfNotNull
import br.com.danielwisky.bible.utils.CriteriaUtils.addGreaterThanConditionIfNotNull
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

object ChapterEntitySpecification {

    fun toSpec(filter: ChapterFilter?, after: String?): Specification<ChapterEntity> {
        return Specification { root: Root<ChapterEntity>, _, builder: CriteriaBuilder ->
            val predicates: MutableList<Predicate> = ArrayList()
            addGreaterThanConditionIfNotNull(builder, predicates, after, root.get("id"))
            addEqualConditionIfNotNull(builder, predicates, filter?.book, root.get<String?>("book").get("id"))
            builder.and(*predicates.toTypedArray())
        }
    }
}
