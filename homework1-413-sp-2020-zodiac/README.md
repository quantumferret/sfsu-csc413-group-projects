# *Homework* 1

This is a very basic, framework-less Java server that more or less faithfully impersonates a RESTful API.

If a query contains all valid key-value pairs needed to create a particular object,
that object gets created and stored in our pseudo-database. The query can contain additional
invalid key-value pairs, which is reflected in the JSON returned in the response, but those
don't make it into the database. Queries not containing all necessary parameters won't affect
the database at all.

Already made objects will persist until the program is ended, so refreshing the webpage also
won't affect the data stored during the connection's lifespan. Creating a transaction requires
both an existing code for an item and a payment. Both of those are generated internally and are
somewhat human-unfriendly at this time, but guaranteed to be unique.
