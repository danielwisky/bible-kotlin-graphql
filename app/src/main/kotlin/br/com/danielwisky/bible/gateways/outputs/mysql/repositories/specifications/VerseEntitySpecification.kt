package br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications

import br.com.danielwisky.bible.domains.VerseFilter
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.VerseEntity
import br.com.danielwisky.bible.utils.CriteriaUtils.addEqualConditionIfNotNull
import br.com.danielwisky.bible.utils.CriteriaUtils.addGreaterThanConditionIfNotNull
import br.com.danielwisky.bible.utils.CriteriaUtils.addLikeConditionIfNotBlank
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate

object VerseEntitySpecification {

    fun toSpec(filter: VerseFilter?, after: String?): Specification<VerseEntity> {
        return Specification<VerseEntity> { root, _, builder: CriteriaBuilder ->
            val predicates: MutableList<Predicate> = ArrayList()
            addGreaterThanConditionIfNotNull(builder, predicates, after, root.get("id"))
            addEqualConditionIfNotNull(
                builder,
                predicates,
                filter?.book,
                root.get<String?>("book").get("id")
            )
            addEqualConditionIfNotNull(
                builder,
                predicates,
                filter?.version,
                root.get<String?>("version").get("id")
            )
            addEqualConditionIfNotNull(builder, predicates, filter?.chapter, root.get("chapter"))
            addLikeConditionIfNotBlank(builder, predicates, filter?.verseLike, root.get("verse"))

            builder.and(*predicates.toTypedArray())
        }
    }
}
