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

<img src="/Users/naoufalalaa/Desktop/Screenshot 2022-03-20 at 17.32.34.png" title="http://localhost:8086/h2-console/" width="300"/>
<img src="/Users/naoufalalaa/Desktop/Screenshot 2022-03-20 at 17.32.44.png" title="http://localhost:8086/h2-console/" width="300"/>
<img src="/Users/naoufalalaa/Desktop/Screenshot 2022-03-20 at 17.32.56.png" title="http://localhost:8086/h2-console/" width="300"/>

- WEB :
```spring
@GetMapping("/users")
public List<User> users(){
    return userService.getUSERS();
}
```
<img src="/Users/naoufalalaa/Desktop/Screenshot 2022-03-20 at 17.38.30.png" title="http://localhost:8086/users/" width="300"/>

```
@GetMapping("/users/{username}")
public User userSearch(@PathVariable String username){
    return userService.findUserByUsername(username);
}
```
<img src="/Users/naoufalalaa/Desktop/Screenshot 2022-03-20 at 17.40.03.png" title="http://localhost:8086/users/naoufal_alaa" width="300"/>

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

<img src="/Users/naoufalalaa/Desktop/Screenshot 2022-03-20 at 17.53.12.png" title="mysql database" width="400"/>
<br/>

---
###### [Naoufal ALAA](https://www.linkedin.com/in/naoufal-alaa/)
