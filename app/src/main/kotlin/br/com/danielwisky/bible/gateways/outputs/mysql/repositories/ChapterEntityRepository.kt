package br.com.danielwisky.bible.gateways.outputs.mysql.repositories

import br.com.danielwisky.bible.gateways.outputs.mysql.entities.ChapterEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository

interface ChapterEntityRepository :
    CrudRepository<ChapterEntity, String>,
    JpaSpecificationExecutor<ChapterEntity>
