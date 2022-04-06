package br.com.danielwisky.bible.gateways.outputs.mysql.repositories

import br.com.danielwisky.bible.gateways.outputs.mysql.entities.VersionEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository

interface VersionEntityRepository :
    CrudRepository<VersionEntity, String>,
    JpaSpecificationExecutor<VersionEntity>
