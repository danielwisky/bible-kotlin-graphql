package br.com.danielwisky.bible.gateways.outputs.mysql.repositories

import br.com.danielwisky.bible.gateways.outputs.mysql.entities.BookEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository

interface BookEntityRepository :
    CrudRepository<BookEntity, String>,
    JpaSpecificationExecutor<BookEntity>
