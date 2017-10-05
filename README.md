![build status image](https://travis-ci.org/Team0x2B/org.x2b.study.core-service-lib.svg?branch=master)

Right now this is just me messing around with GraphQL and Spring until I find a pattern I like. Eventually this will be
the org.x2b.study.core service library used to start writing a new backend service.


# GraphQL Conventions

GraphQL schemas should ALWAYS be defined in their own file named ```schema.gql``` placed in the root of the main 
resources directory. NEVER define ANY part of a schema in Java.

NEVER create a field that will be accessed through GraphQL that does not have a public getter. Any time this happens,
it will have to be fixed to allow for authorization later.

ALWAYS put functionality in a business logic layer. Anything named ```*Resolver``` or ```*Fetcher``` should not have logic
pertaining to the object being fetched. The one (psuedo) exception to this rule is authorization for objects in the ```types```
package (where individual field access may be restricted using ```RequiresPermission``` annotations.

# General API Conventions

Expensive to retrieve values should be handled properly. If the user doesn't request value, don't fetch it. Currently,
my plan is to use ```Proxy``` and ```CompletableFuture``` to ensure this can happen in the business logic and not the resolver.