# Digital Banking API
> swagger ui for documentation

## Technologies & Tools

- [x] Mysql
- [x] Spring Boot
- [x] DTOs
- [x] Spring Security
- [x] JWT
## Project Structure 

![Screenshot 2022-05-30 at 02 31 50](https://user-images.githubusercontent.com/61352259/170901550-31643924-60fc-4156-ab9b-0c97cc68ce7a.png)

## Entities 

![Screenshot 2022-05-30 at 02 31 05](https://user-images.githubusercontent.com/61352259/170901489-ce4547f2-bdd9-49e5-b6c7-9a52a54208ec.png)

## Dtos 

![Screenshot 2022-05-30 at 02 32 34](https://user-images.githubusercontent.com/61352259/170901598-1266b84f-704f-4b06-8fa3-5858798ca0df.png)

## Repositories 

![Screenshot 2022-05-30 at 02 33 33](https://user-images.githubusercontent.com/61352259/170901683-6baaba6c-c17c-4061-9f52-e4d2c4a837ef.png)

## Security layer

![Screenshot 2022-05-30 at 02 34 32](https://user-images.githubusercontent.com/61352259/170901753-2b4655e7-66c5-4b87-99f8-f1f5154253ef.png)
![Screenshot 2022-05-30 at 02 35 04](https://user-images.githubusercontent.com/61352259/170901786-60ffd6b6-5c8a-43d8-a552-54c1c88bf27e.png)

in *SecurityConfig.java*
``` java
public class JwtConfig {
    public static String SECRET_PHRASE = "SECRET_PHRASE";
    public static int ACCESS_TOKEN_EXPIRATION = 24*60*60*1000; // 24hrs
    public static String AUTHORIZATION_HEADER = "Authorization";
    public static String TOKEN_HEADER_PREFIX = "Bearer ";
    public static String REFRESH_PATH = "/V1/refresh-token";
    public static int REFRESH_TOKEN_EXPIRATION = 48*60*60*1000; // 48hrs
}
```

We need to disable the csrf protection (Cross-Site Request Forgery)
``` java
http.csrf().disable();// csrf protection disabled
```
We will eventually face issues with the CORS so : 
``` java
http.formLogin().disable().cors(httpSecurityCorsConfigurer -> {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues(); // allow all
            corsConfiguration.addAllowedMethod("GET"); // add other methods as per your requirement
            corsConfiguration.addAllowedMethod("POST");
            corsConfiguration.addAllowedMethod("PUT");
            corsConfiguration.addAllowedMethod("DELETE");
            source.registerCorsConfiguration("/**",  corsConfiguration);
            httpSecurityCorsConfigurer.configurationSource(source);
        });        
        // disabled cors issues
```
After that we authorize requests for casual ressources : 
``` java
http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// session management disabled
        http.authorizeHttpRequests().antMatchers("/login/**",
                "/swagger-ui**",
                "/swagger-ui/**",
                "/v3/**",
                "/v1/**",
                JwtConfig.REFRESH_PATH).permitAll();
        http.addFilter( new JwtAuthenticationFilter( authenticationManagerBean() ) );
        http.addFilterBefore( new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);//@formatter:on
    }
```

## Security in OpenAPI

we now need the Authorize button on the swagger ui : 
dans le fichier main:
``` java
@SecurityScheme(name = "digitalBankApi", description = "Digital Bank API", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
```

![Screenshot 2022-05-30 at 02 21 43](https://user-images.githubusercontent.com/61352259/170902934-59a1c25f-d1e5-4451-8aaf-57bc4b6feb04.png)
![Screenshot 2022-05-30 at 02 22 02](https://user-images.githubusercontent.com/61352259/170903005-b7ea451b-1b5f-4421-8666-6e48262fc7ae.png)
![Screenshot 2022-05-30 at 02 22 23](https://user-images.githubusercontent.com/61352259/170903025-ae4bbb72-2b4d-48e8-83f2-9056720c5062.png)


![Screenshot 2022-05-30 at 02 22 23](https://user-images.githubusercontent.com/61352259/170903398-ca88bbd7-cb32-4a07-a754-7658ce1a752e.png)



##### Ressources : 
JWT  :  https://github.com/ubmagh/spring-ng-digital-banking/tree/main/Backend
