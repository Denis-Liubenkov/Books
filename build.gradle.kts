plugins {
    id("java")
    id ("org.springframework.boot") version "3.3.5"
    id ("io.spring.dependency-management") version "1.1.4"
}

group = "com.tms"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.aspectj:aspectjweaver:1.9.22.1")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.5")
    implementation("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.4.0")
    implementation ("org.springframework.boot:spring-boot-starter-validation:3.4.0")

}

tasks.test {
    useJUnitPlatform()
}
