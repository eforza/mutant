# Mutant API

### Instrucciones para ejecutar la aplicación en un ambiente local

Clonar el repositorio:
        
    git clone git@github.com:eforza/mutant.git

Para levantar la aplicación situarse en le directorio raíz del proyecto y ejecutar el siguiente comando:

    ./gradlew bootRun

### Deploy 

La aplicación se deployó en AWS Elastic Beanstalk utilizando una BD Mysql en RDS:

Url de los servicios:

http://melimutant-env.eba-b6fqtzyu.us-east-2.elasticbeanstalk.com/mutant

http://melimutant-env.eba-b6fqtzyu.us-east-2.elasticbeanstalk.com/stats

Se agregó un Proyecto de Postman ya configurado para ejecutar los servicios en este ambiente y con alguno casos de prueba:

    mutant/postman/Mutant.postman_collection.json

### Test

Para ejecutar los test:

    ./gradlew test

Se utiliza jacoco para analizar la cobertura de los test, está configurado para que falle el build si no pasa el humbral
de 80% de code coverrage. Los resultados de jacoco se pueden ver luego de ejecutar los teste en:

    mutant/build/reports/jacoco/test/html/index.html

### Stack usado
- Java 11
- Gradle
- Spring Boot 2.1
- H2
- MySQL 8
- Jacoco

### Documentación de referencia

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [JaCoCo Java Code Coverage Library](https://www.eclemma.org/jacoco/)
* [Deploying a Spring Boot Application on AWS Using AWS Elastic Beanstalk](https://aws.amazon.com/blogs/devops/deploying-a-spring-boot-application-on-aws-using-aws-elastic-beanstalk/)
