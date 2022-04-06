package br.com.danielwisky.bible.gateways.outputs.mysql.repositories.specifications

import br.com.danielwisky.bible.domains.VersionFilter
import br.com.danielwisky.bible.gateways.outputs.mysql.entities.VersionEntity
import br.com.danielwisky.bible.utils.CriteriaUtils.addEqualConditionIfNotNull
import br.com.danielwisky.bible.utils.CriteriaUtils.addGreaterThanConditionIfNotNull
import br.com.danielwisky.bible.utils.CriteriaUtils.addLikeConditionIfNotBlank
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

object VersionEntitySpecification {

    fun toSpec(filter: VersionFilter?, after: String?): Specification<VersionEntity> {
        return Specification { root: Root<VersionEntity>, _, builder: CriteriaBuilder ->
            val predicates: MutableList<Predicate> = ArrayList()

            addGreaterThanConditionIfNotNull(builder, predicates, after, root.get("id"))
            addEqualConditionIfNotNull(builder, predicates, filter?.name, root.get("name"))
            addLikeConditionIfNotBlank(builder, predicates, filter?.nameLike, root.get("name"))

            builder.and(*predicates.toTypedArray())
        }
    }
}
