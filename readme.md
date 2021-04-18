# Spring in Action Note

# Part 1. Getting Started with Spring

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
 
# Part 2. Developing Web Applications

## Definition of a domain
> The application domain is the subject area it addresses. For example, data structures, view designs, objects, etc.

## A Controller's job
> To handle HTTP requests, either hand a request off to a view for rendering HTML (browser-displayed) or write data directly to response body (RESTful).

## HTTP annotations
| Annotation | Description | Comment |
| :------------ |:-------------|:-----|
| @RequestMapping | General Purpose of Request Handling | Applied at a <b>class level</b> to specify the base path |
| @GetMapping | Handle HTTP GET Request | Applied on handler methods |
| @PostMapping | Handle HTTP POST Request | Applied on handler methods |
| @PutMapping | Handle HTTP PUT Request | Applied on handler methods |
| @DeleteMapping | Handle HTTP DELETE Request | Applied on handler methods |
| @PatchMapping | Handle HTTP PATCH Request | Applied on handler methods ||

## View Template Libraries
| Template | Dependency | Cache Templates | Remark |
| :------------ |:----------|:-----|:--------|
| [Thymeleaf](https://www.thymeleaf.org/)|spring-boot-starter-thymeleaf|spring.thymeleaf.cache||
|[FreeMarker](https://freemarker.apache.org/)|spring-boot-starter-freemarker|spring.freemarker.cache||
|[Mustache](https://mustache.github.io/)|spring-boot-starter-mustache|spring.mustache.cache||
|[Groovy](https://docs.groovy-lang.org/docs/next/html/documentation/template-engines.html#_introduction)|spring-boot-starter-groovy-templates|spring.groovy.template.cache||
|[JavaServer Pages, JSP](https://www.oracle.com/java/technologies/jspt.html)|N/A|N/A||

## View Presentation Principles
1. <b>View libraries are designed to be decoupled from any web frameworks</b>. So they are unware of Spring's model abstraction and unable to work with data that controller places in <b>Model</b>.
2. <b>View libraries can work with servlet request attributes</b>. Therefore before Spring hands requests over to views for rendering HTML, it copies them into servlet request attributes that other view template options are ready access to.

## View Controller
Conventionally, we define a controller class to handle requests from views through model attributes. Therefore, there is usually a mapping between an URL and a view. Suppposed that it is unnecessary to cope with requests via model attributes and directly map to corresponding views, the [WebMvcConfigurer](https://docs.spring.io/spring-framework/docs/3.1.x/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html) interface could be applied as below to simplify view mapping.
```
@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
	
}
```
As code snippet above, the class `TacoCloudApplication` implements interface `WebMvcConfigurer`, overriding the method `addViewControllers` to **create a direct mapping between an URL ("/") and a view ("home")**. 

**As a result, a developer could remove corresponding controller classes from sources**.

# Part 3. Working with Data

## H2 Database
In order to set up a simple database for unit testing or POC, we'd like it to be created when a server turns on as well as to be destroyed when a server turns off. [H2 Databse](https://howtodoinjava.com/spring-boot2/h2-database-example/) is an embedded, in-memory database that could be applied for functional validation in the original development life cycle.

To apply it in a SpringBoot application, we should add the maven dependency in a pom.xml file as below
```
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
As pom.xml file described, the scope of the bean is used at runtime. That is to say, the database starts when an application runs; release itself when an application terminates. To learn more regarding it, please refer to [H2 Engine](https://www.h2database.com/html/main.html).