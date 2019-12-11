## Spring TDD Example

Sample code to demonstrate test driven development (TDD) based approach to build spring boot application. 

## Annotations

| Annotation                        | Description
|-------------------------------    |--------------
|@SpringBootTest  	                | Bootstrap test with SpringBoot support
|                                   |
|@WebMvcTest   	                    | Load context relevant spring mvc components
|                                   |
|@DataJpaTest   	                | Loads jpa relevant config, uses in-memory db by default, override with @AutoConfigureTestDatabase
|   	                            |
|@AutoConfigureTestDatabase   	    | If you do not want to use auto-configured test database, use this to configure a test db
|  	                                |
|@MockBean                          | Use with SpringRunner class to mock components in test
|                                   |
|@Mock    	                        | Similar to @MockBean but without spring support; use with MockitoJUnitRunner
|  	                                |
|@AutoConfigureMockMvc              | More control of mock-mvc, disable spring security bits etc
|                                   |
|@AutoConfigureRestDocs             | REST documentation for all Http request, responses
|                                   |
|@Sql                               | Run SQL scripts for data setup
|                                   |
