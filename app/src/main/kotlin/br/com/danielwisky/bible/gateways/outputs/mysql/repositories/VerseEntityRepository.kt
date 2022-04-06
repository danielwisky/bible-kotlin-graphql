package br.com.danielwisky.bible.gateways.outputs.mysql.repositories

import br.com.danielwisky.bible.gateways.outputs.mysql.entities.VerseEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository

interface VerseEntityRepository :
    CrudRepository<VerseEntity, String>,
    JpaSpecificationExecutor<VerseEntity>
