package br.com.danielwisky.bible.gateways.outputs.mysql.entities

import br.com.danielwisky.bible.domains.Chapter
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "chapters")
data class ChapterEntity(
    @Id
    var id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: BookEntity? = null,
    var number: Int? = null,
) {

    fun toDomain(): Chapter {
        return Chapter(
            id = this.id,
            book = this.book?.toDomain(),
            number = this.number
        )
    }
}
