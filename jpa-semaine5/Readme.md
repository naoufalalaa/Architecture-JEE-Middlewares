# JPA — Hibernate MySQL

---
> This work is about using Mysql database and project structure of entities / repositories / services and web  
### Technologies : 

- ***Hibernate***
- ***H2***
- ***MySQL***

### Strategie :
#### using H2 DataBase :
- configuration :
```properties
spring.datasource.url=jdbc:h2:mem:db-exemple
spring.h2.console.enabled=true
server.port=8086
```
- execution : 

<img src="https://user-images.githubusercontent.com/61352259/159221020-88b029fb-6091-441f-9e7f-06ff9d79b0b5.png" title="http://localhost:8086/h2-console/" width="300"/>
<img src="https://user-images.githubusercontent.com/61352259/159221084-7f46bd59-1502-45b9-ab8e-cfd8429e43b2.png" title="http://localhost:8086/h2-console/" width="300"/>
<img src="https://user-images.githubusercontent.com/61352259/159221117-3b178b0c-43e9-4226-a14f-22c9ca7988b6.png" title="http://localhost:8086/h2-console/" width="300"/>



- WEB :
```java
@GetMapping("/users")
public List<User> users(){
    return userService.getUSERS();
}
```
<img src="https://user-images.githubusercontent.com/61352259/159221166-ce450c05-4f05-4799-be9d-0d3c9a34623b.png" title="http://localhost:8086/users/" width="300"/>

```java
@GetMapping("/users/{username}") 
public User userSearch(@PathVariable String username){
    return userService.findUserByUsername(username);
}
```
<img src="https://user-images.githubusercontent.com/61352259/159221223-4dd021e8-a79d-474a-b878-8e2fe9cc6f61.png" title="http://localhost:8086/users/naoufal_alaa" width="300"/>


#### Using MySQL
- configuration `resources/application.properties`:
```properties
server.port=8086
spring.datasource.url=jdbc:mysql://localhost:3306/db-exemple?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```
- execution :

<img src="https://user-images.githubusercontent.com/61352259/159221275-c374f308-6e36-4c16-b1ee-9f30815674f1.png" title="mysql database" width="400"/>
<br/>

---
###### [Naoufal ALAA](https://www.linkedin.com/in/naoufal-alaa/)
