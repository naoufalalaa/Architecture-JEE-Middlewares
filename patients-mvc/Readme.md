# SpringMVC

> This work is about using Mysql database and project structure of entities / repositories / services and web
### Technologies :

- ***SpringMVC***
- ***MySQL***
- ***ThymeLeaf*** template engine

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

<img src="https://user-images.githubusercontent.com/61352259/160201222-a3bdf1ac-855b-4b8c-bb2f-e8a79a76ee82.png" title="database" width="300"/>



---

### Application :

#### Entity : 

```java
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;
}
```
***PATIENT*** will be our only entity for this example; with the *lombok* we can easily generate constructors.

#### Repositories : 
```java
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String kw, Pageable pageable);
    Patient findByNom(String nom);
}
```
For our JPA Repository we'll be having to deal with only two functions in addition to the standard functions :
````java
List<Patient> findAll(args []);
Patient findBy();
Patient save(Patient patient);
void delete(Patient patient)
````
#### Execution : 

this application will display the rows saved in the database using this part of code : 
- for starters let's define a controller for our view :

- we can easily access all doncuments by this url ``https://localhost:8089/index/``
```java
@GetMapping(path = "/index")
    public String patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword
    ) throws Exception{
        if(page<0) {
            return "redirect:/index?page=" + 0 + "&keyword=" + keyword;
        }
        Page<Patient> pagePatients=patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        if(page>pagePatients.getTotalPages()){
            return "redirect:/index?page="+(pagePatients.getTotalPages()-1)+"&keyword="+keyword;
        }
        model.addAttribute("patients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
```

- after that we need a redirection path from `*/` to automatication `*/index`
```java
@GetMapping("/")
    public String home() {
        return "redirect:/index";
    }
```
<img src="https://user-images.githubusercontent.com/61352259/160206881-12eab864-9316-4380-89b8-4a2a3f56db4f.png" title="/index" width="600"/>

- pagination :
<img src="https://user-images.githubusercontent.com/61352259/160207060-36f47de7-97e9-4cf4-acbe-4046c162aad2.png" title="pagination" width="600"/>

- search by keyword :
<img src="https://user-images.githubusercontent.com/61352259/160207299-f46c5e5d-6fb9-455c-869f-90a53006de28.png" title="keyword" width="600"/>





---
###### [Naoufal ALAA](https://www.linkedin.com/in/naoufal-alaa/)
