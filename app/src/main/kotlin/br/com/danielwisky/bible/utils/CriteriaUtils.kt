package br.com.danielwisky.bible.utils

import java.util.Objects.nonNull
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate

object CriteriaUtils {

    fun addLikeConditionIfNotBlank(
        builder: CriteriaBuilder,
        predicates: MutableList<Predicate>,
        value: String?,
        field: Path<String>,
    ) {
        if (!value.isNullOrEmpty()) {
            predicates.add(builder.like(field, String.format("%%%s%%", value)))
        }
    }

    fun addEqualConditionIfNotNull(
        builder: CriteriaBuilder,
        predicates: MutableList<Predicate>,
        value: String?,
        field: Path<String>,
    ) {
        if (!value.isNullOrEmpty()) {
            predicates.add(builder.equal(field, value))
        }
    }

    fun addEqualConditionIfNotNull(
        builder: CriteriaBuilder,
        predicates: MutableList<Predicate>,
        value: Int?,
        field: Path<Int>,
    ) {
        if (nonNull(value)) {
            predicates.add(builder.equal(field, value))
        }
    }

    fun addGreaterThanConditionIfNotNull(
        builder: CriteriaBuilder,
        predicates: MutableList<Predicate>,
        value: String?,
        field: Path<String>,
    ) {
        if (!value.isNullOrEmpty()) {
            predicates.add(builder.greaterThan(field, value))
        }
    }
}
