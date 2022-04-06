# Bible GraphQL

# Example

```
query ($first: Int, $after: String) {
  verses(filter: { verseLike: "Amor" }, first: $first, after: $after) {
    edges {
      cursor
      node {
        id
        number
        book {
          id
          name
        }
        verse
      }
    }
    pageInfo {
      hasPreviousPage
      hasNextPage
      startCursor
      endCursor
    }
  }
}
```