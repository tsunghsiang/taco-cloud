# Spring in Action Note

# Ch 1. Getting Started with Spring

## Definition of Spring
> The Spring itself is actually a container of an application context that creates or manages beans applied by an application. 

> Dependency Injection (DI) mechanism is used to take beans into application context without considering dependency issues.

## Supportive Tools
 - [STS (Spring Tool Suite)](https://spring.io/tools) : Initialize a SpringBoot project quickly
 - [SpringBoot DevTools](https://www.baeldung.com/spring-boot-devtools)
 -- automatic application restarts when code changes
 -- automatic browser refresh when resources change (ex: stylesheets, Javascript, templates, etc)
 -- automatic disable of template caches
 -- built-in H2 console if it is in use
 - [Lombok](https://projectlombok.org/) : automatically inject missing members for domain class, which makes codes slim and trim. 
 
# Ch 2. Developing Web Applications

## Definition of a domain
> The application domain is the subject area it addresses. For example, data structures, view designs, objects, etc.