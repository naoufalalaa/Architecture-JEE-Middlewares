# SpringMVC

---
> This work is about using Mysql database and project structure of entities / repositories / services and web
### Technologies :

- ***SpringMVC***
- ***MySQL***

### Strategie :
#### using *MYSQL* DataBase :
- configuration :
```properties
server.port=8089
spring.datasource.url=jdbc:mysql://localhost:3306/db-patients?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```
#### using *Thymeleaf* template :
- configuration :
```properties
spring.thymeleaf.cache=false
```
> So that the rendering is restored and not saved in a cache that we'll have to delete each time we'd like to preview our changes.

- execution : 

<img src="https://user-images.githubusercontent.com/61352259/159221117-3b178b0c-43e9-4226-a14f-22c9ca7988b6.png" title="http://localhost:8086/h2-console/" width="300"/>


---

---
###### [Naoufal ALAA](https://www.linkedin.com/in/naoufal-alaa/)
