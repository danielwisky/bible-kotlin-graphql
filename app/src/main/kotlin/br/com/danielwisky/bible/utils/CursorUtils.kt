package br.com.danielwisky.bible.utils

import graphql.relay.ConnectionCursor
import graphql.relay.DefaultConnectionCursor
import graphql.relay.Edge
import java.nio.charset.StandardCharsets.UTF_8
import java.util.*

object CursorUtils {

    fun createCursorWith(id: String?): ConnectionCursor {
        return DefaultConnectionCursor(encodeBase64(id.orEmpty()))
    }

    fun <T> getFirstCursorFrom(edges: List<Edge<T>>): ConnectionCursor? {
        return if (edges.isEmpty()) return null else return edges.first().cursor
    }

    fun <T> getLastCursorFrom(edges: List<Edge<T>>): ConnectionCursor? {
        return if (edges.isEmpty()) return null else return edges.last().cursor
    }

    fun encodeBase64(id: String) = Base64.getEncoder().encodeToString(id.toByteArray(UTF_8))

    fun decodeBase64(id: String?): String? =
        if (id.isNullOrEmpty()) null else String(Base64.getDecoder().decode(id))
}
