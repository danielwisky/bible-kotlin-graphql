package br.com.danielwisky.bible.gateways.outputs.mysql.entities

import br.com.danielwisky.bible.domains.Version
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "versions")
data class VersionEntity(
    @Id
    var id: String? = null,
    var name: String? = null,
) {

    fun toDomain(): Version {
        return Version(
            id = this.id,
            name = this.name
        )
    }
}
