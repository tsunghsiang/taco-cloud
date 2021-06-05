# Spring in Action Note

# Chapter 1 - Foundamental Spring
## Part 1. Getting Started with Spring

### Definition of Spring
> The Spring itself is actually a container of an application context that creates or manages beans applied by an application. 

> Dependency Injection (DI) mechanism is used to take beans into application context without considering dependency issues.

### Supportive Tools
 - [STS (Spring Tool Suite)](https://spring.io/tools) : Initialize a SpringBoot project quickly
 - [SpringBoot DevTools](https://www.baeldung.com/spring-boot-devtools)
 -- automatic application restarts when code changes
 -- automatic browser refresh when resources change (ex: stylesheets, Javascript, templates, etc)
 -- automatic disable of template caches
 -- built-in H2 console if it is in use
 - [Lombok](https://projectlombok.org/) : automatically inject missing members for domain class, which makes codes slim and trim. 
 
## Part 2. Developing Web Applications

### Definition of a domain
> The application domain is the subject area it addresses. For example, data structures, view designs, objects, etc.

### A Controller's job
> To handle HTTP requests, either hand a request off to a view for rendering HTML (browser-displayed) or write data directly to response body (RESTful).

### HTTP annotations
| Annotation | Description | Comment |
| :------------ |:-------------|:-----|
| @RequestMapping | General Purpose of Request Handling | Applied at a <b>class level</b> to specify the base path |
| @GetMapping | Handle HTTP GET Request | Applied on handler methods |
| @PostMapping | Handle HTTP POST Request | Applied on handler methods |
| @PutMapping | Handle HTTP PUT Request | Applied on handler methods |
| @DeleteMapping | Handle HTTP DELETE Request | Applied on handler methods |
| @PatchMapping | Handle HTTP PATCH Request | Applied on handler methods ||

### View Template Libraries
| Template | Dependency | Cache Templates | Remark |
| :------------ |:----------|:-----|:--------|
| [Thymeleaf](https://www.thymeleaf.org/)|spring-boot-starter-thymeleaf|spring.thymeleaf.cache||
|[FreeMarker](https://freemarker.apache.org/)|spring-boot-starter-freemarker|spring.freemarker.cache||
|[Mustache](https://mustache.github.io/)|spring-boot-starter-mustache|spring.mustache.cache||
|[Groovy](https://docs.groovy-lang.org/docs/next/html/documentation/template-engines.html#_introduction)|spring-boot-starter-groovy-templates|spring.groovy.template.cache||
|[JavaServer Pages, JSP](https://www.oracle.com/java/technologies/jspt.html)|N/A|N/A||

### View Presentation Principles
1. <b>View libraries are designed to be decoupled from any web frameworks</b>. So they are unware of Spring's model abstraction and unable to work with data that controller places in <b>Model</b>.
2. <b>View libraries can work with servlet request attributes</b>. Therefore before Spring hands requests over to views for rendering HTML, it copies them into servlet request attributes that other view template options are ready access to.

### View Controller
Conventionally, we define a controller class to handle requests from views through model attributes. Therefore, there is usually a mapping between an URL and a view. Suppposed that it is unnecessary to cope with requests via model attributes and directly map to corresponding views, the [WebMvcConfigurer](https://docs.spring.io/spring-framework/docs/3.1.x/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html) interface could be applied as below to simplify view mapping.
```java
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

## Part 3. Working with Data

### H2 Database
> In order to set up a simple database for unit testing or POC, we'd like it to be created when a server turns on as well as to be destroyed when a server turns off. [H2 Databse](https://howtodoinjava.com/spring-boot2/h2-database-example/) is an embedded, in-memory database that could be applied for functional validation in the original development life cycle.

To apply it in a SpringBoot application, we should add the maven dependency in a pom.xml file as below
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
As pom.xml file described, the scope of the bean is used at runtime. That is to say, the database starts when an application runs; release itself when an application terminates. To learn more regarding it, please refer to [H2 Engine](https://www.h2database.com/html/main.html).

### Spring JDBC
> To interact with database, Spring provides a framework, Spring JDBC, for developers to simplify redundant data access operations. In this section, the auditor introduced the uses of both [JdbcTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html) and [SimpleJdbcInsert](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/simple/SimpleJdbcInsert.html) for inserting data into specific tables.

To sum up, [Spring JDBC modules](https://www.baeldung.com/spring-jdbc-jdbctemplate) are to be separated into 4 packages:
* Core: The core of JDBC container which contains basic components for data access, inclusive of JdbcTemplate and SimpleJdbcInsert.
* Datasource: The utility class that provides capability for developers to access data sources (ie: database)
* Object: Data access is realized in an object-oriented manner. The package allows developers to query columns or properties; return results as domain/businese objects.
* Support: It provides additional functionality under core and object packages.

Learn more from [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/index.html).

### Customization of JPA Repositories
> **When generating the repository implementations, Spring Data examines any methods in the repository interface, parses the method name, and attempts to understand the method's purpose in the context of the persisted object**.

Essentially, Spring Data defines a miniature of DSL ([Domain Specific Language](https://en.wikipedia.org/wiki/Domain-specific_language)) where persistence details are expressed in repository method signatures. In short, a repository method consists of a verb, an optional subject, the word '**By**', and a predicate, as described below.
```
{verb}(optional subject){predicate}
```
For example, **find(Orders)ByDeliveryZip(String zip)** and **readOrdersByDeliveryZipAndPlacedAtBetween(String zip, Date start, Date end)**

There are 3 points needs being emphasized with JPA customization:
* The verb indicates the intention of the method to the repository.
* Spring data ignores most subject words as that is the type that **CrudRepository** is parameterized.
* The predicate follows by word **By** in the method name with query conditions.
.

## Part 4. Securing Spring

To provide basic secure configuration for SpringBoot applications, the only thing a developer needs to do is to include the maven dependency as below in the pom.xml file.
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
When the application starts, auto-configuration will detect that Spring Security is in the class-path and will set up some basic security configuration. That is to say, when you fire up an applications, a login page would appear, requiring basic authentication like username & password as depicted below. Usually, the username is **user** by default while password is randomly generated as the console shows.

![LoginPage](https://github.com/tsunghsiang/taco-cloud/blob/master/src/main/resources/static/images/loginpage.png)
![PWD](https://github.com/tsunghsiang/taco-cloud/blob/master/src/main/resources/static/images/pwd.png)

Spring Security provides several options for configuring a user store. No matter which option below you choose, you can configure it by overriding ```configure(AuthenticationManagerBuilder auth)``` method in class [```WebSecurityConfigurerAdapter```](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.html#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)).
**[1] An in-memory user store**: usually applied for testing purposes or for very simple applications. However, if you'd like to develope functionalities of user registration, account removal, change password, etc, the in-memory user store is not enough.
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected void configure​(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("{noop}inifnity")
			.authorities("ROLE_USER");
	}
}
```
Note: If you'd like to know the reason why there is a wording ```{noop}``` concatenated with password ```infinity```, please refer to the [link](https://mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/).

**[2] A JDBC-based user store**: The JDBC-based user store is applied when there is a relational database storing users'information inclusive of their user names, passwords and authorities. 

Unlike in-memory user store, developers need to refer to a certain datasource for accessing users' information. Same, we should prepare corresponding data in a database. In this example, tables ```User``` and ```UserAuthorities``` should be created and inserted with values as needed.
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;
	private final String query_user_by_username = "SELECT username, '{noop}' || password, enabled FROM Users WHERE username = ?";
	private final String query_auth_by_username = "SELECT username, authority FROM UserAuthorities WHERE username = ?";
	
	@Autowired
	protected void configure​(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(this.ds)
			.usersByUsernameQuery(query_user_by_username)
			.authoritiesByUsernameQuery(query_auth_by_username);
	}
	
}
```
In ```schema.sql```, we specify tables ```Users``` and ```UserAuthorities``` with required columns.
```sql
CREATE TABLE IF NOT EXISTS Users (
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS UserAuthorities (
	username VARCHAR(255) NOT NULL,
	authority VARCHAR(255) NOT NULL,
	FOREIGN KEY (username) REFERENCES Users(username)
);
```
And edit ```import.sql```, the data would be loaded into database before a web application is fired up.
```sql
/* Insert Users */
INSERT INTO Users(username, password, enabled) VALUES ('admin', 'sysadmin', true);
INSERT INTO Users(username, password, enabled) VALUES ('user', 'test', true);
/* Insert UserAuthorities */
INSERT INTO UserAuthorities(username, authority) VALUES ('admin', 'admin');
INSERT INTO UserAuthorities(username, authority) VALUES ('user', 'customer');
```
**[3] An [LDAP](https://en.wikipedia.org/wiki/Lightweight_Directory_Access_Protocol#Protocol_overview)-backed user store**: To enable an embedded LDAP server, we have to add 4 maven dependencies to **pom.xml** file: ```spring-boot-starter-security```, ```spring-ldap-core```,  ```spring-security-ldap``` and ```unboundid-ldapsdk```. An embedded LDAP server would be loaded in when a web application is fired up
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.ldap</groupId>
	<artifactId>spring-ldap-core</artifactId>
</dependency>
		
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-ldap</artifactId>
</dependency>
		
<dependency>
	<groupId>com.unboundid</groupId>
	<artifactId>unboundid-ldapsdk</artifactId>
</dependency>
```
To configure settings of embedded LDAP server, we added associative parameters to ```application.properties``` file, as indicated below.
```properties
# LDAP configuration
spring.ldap.embedded.ldif=users.ldif
spring.ldap.embedded.base-dn=ou=groups,dc=tacocloud,dc=com
spring.ldap.embedded.port=6666
```
As you see, field ```spring.ldap.embedded.ldif``` specifies a [LDIF](http://www.zytrax.com/books/ldap/ch3/#overview) file that pre-configures users login information. Usually we put the corresponding [LDIF](http://www.zytrax.com/books/ldap/ch3/#overview) file under path ```src/main/resources```; Spring would automatically scan the resources; load data into the LDAP server. Furthermore, I refer the embedded LDAP server port as 6666, or the port number 33389 is applied by default.

Likewise, the class extends class ```WebSecurityConfigurerAdapter``` to override the ```configure``` method for implementing LDAP-based user store. Note that ```AuthenticationManagerBuilder``` provides a method, ```ldapAuthentication()```, to validate username and password.
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configure​(AuthenticationManagerBuilder auth) throws Exception {
	    LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuth 
	    = auth.ldapAuthentication();
		ldapAuth.userSearchBase("ou=people")
		 		.userSearchFilter("uid={0}")
		 		.groupSearchBase("ou=groups")
		 		.groupSearchFilter("member={0}")
				.passwordCompare()
				.passwordAttribute("userPassword");
		ldapAuth.contextSource()
				.root("dc=tacocloud,dc=com");
	}
}
```
There are two points we should notice when it comes to LDAP implementation.
(1) Now our case is for embedded LDAP server, if users want to configure a remote LDAP server where data resides, remember to use the ```contextSource()``` method to configure the location.
```java
ldapAuth.contextSource()
        .url("ldap://tacocloud.com:6666/dc=tacocloud,dc=com")
        .root("dc=tacocloud,dc=com");
```
(2) As we have specified field ```spring.ldap.embedded.ldif``` in ```application.properties``` so that Spring engine would scan resource files to find its path. However, if you don't do that, remember to specify the path by ```ldif(String path)``` method. Otherwise errors would occur when an application fires up.
```java
ldapAuth.contextSource()
        .ldif("classpath:users.ldif")
        .root("dc=tacocloud,dc=com");
```
Lastly, we edit ```users.ldif``` to specify users information. Usually the file is put under the ```src/main/resources``` folder.
```ldif
dn: ou=groups,dc=tacocloud,dc=com
objectclass: top
objectclass: organizationalUnit
ou: groups

dn: ou=people,dc=tacocloud,dc=com
objectclass: top
objectclass: organizationalUnit
ou: people

dn: uid=buzz,ou=people,dc=tacocloud,dc=com
objectclass: top
objectclass: person
objectclass: organizationalPerson
objectclass: inetOrgPerson
cn: Buzz Lightyear
sn: Lightyear
uid: buzz
userPassword: password

dn: cn=tacocloud,ou=groups,dc=tacocloud,dc=com
objectclass: top
objectclass: groupOfNames
cn: tacocloud
member: uid=buzz,ou=people,dc=tacocloud,dc=com
```

**Note**: To know more about LDAP and LDIF, please refer to links below.
* [LDAP Overview](http://www.zytrax.com/books/ldap/ch3/#overview)
* [LDIF Attributes](http://www.kouti.com/tables/baseattributes.htm)

**[4] A custom user details service**