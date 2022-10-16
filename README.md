## I want to cover next topics in this project
1. DGS
2. gRPC and Field Masks
3. GraphQL Federation
4. Monorepo
5. CI/CD setup
6. GraphQL testing
7. ...

## Project idea
This should be a website or at least an API that provide information about a selected city, like name, location,
country (and information about the country), weather, population, etc.
As data sources, I'll use open APIs.

Some basic security checks should be implemented. The idea - some fields and operations should not be available for 
some roles. 
I want to try to hide restricted fields on the gRPC API level using protobuf FieldMask.

### Approximate architecture
```
                                  -> domain-specific-graphql-sub-graph-1 -> domain-specific-gRPC-api-1 -> datasource-1
                                /
UI -> federated-graphql-gateway -> domain-specific-graphql-sub-graph-2   -> domain-specific-gRPC-api-2 -> datasource-2
                                \
                                  -> domain-specific-graphql-sub-graph-3 -> domain-specific-gRPC-api-3 -> datasource-3
```

