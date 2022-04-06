package br.com.danielwisky.bible.gateways.outputs.mysql.entities

import br.com.danielwisky.bible.domains.Verse
import javax.persistence.*

@Entity(name = "verses")
data class VerseEntity(
    @Id
    var id: Int? = null,
    @ManyToOne
    @JoinColumn(name = "version_id")
    var version: VersionEntity? = null,
    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: BookEntity? = null,
    var chapter: Int? = null,
    var number: Int? = null,
    @Lob
    var verse: String? = null,
) {

    fun toDomain(): Verse {
        return Verse(
            id = this.id,
            version = this.version?.toDomain(),
            book = this.book?.toDomain(),
            chapter = this.chapter,
            number = this.number,
            verse = this.verse
        )
    }
}
