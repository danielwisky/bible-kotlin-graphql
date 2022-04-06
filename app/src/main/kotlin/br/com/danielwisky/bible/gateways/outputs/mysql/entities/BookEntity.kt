package br.com.danielwisky.bible.gateways.outputs.mysql.entities

import br.com.danielwisky.bible.domains.Book
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "books")
data class BookEntity(
    @Id
    var id: String? = null,
    var name: String? = null,
) {

    fun toDomain(): Book {
        return Book(
            id = this.id,
            name = this.name
        )
    }
}
