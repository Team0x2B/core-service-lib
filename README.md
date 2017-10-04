Right now this is just me messing around with GraphQL and Spring until I find a pattern I like. Eventually this will be
the core service library used to start writing a new backend service.



# GraphQL Conventions

GraphQL schemas should ALWAYS be defined in their own file named ```schema.gql``` placed in the root of the main 
resources directory. NEVER define ANY part of a schema in Java.

Resolvers should be rooted in a ```resolvers``` while types should be placed in a ```types``` package as per the GraphQL
conventions. A type and its resolver should be placed in the same relative package.  


For example:
The type FooBar with qualified name:  
```org.x2b.study.core.types.foo.bar.FooBar```  
Should have the following resolver:  
```org.x2b.study.core.resolvers.foo.bar.FooBarResolver```