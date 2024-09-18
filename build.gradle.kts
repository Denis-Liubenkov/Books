plugins {
    id("java")
}

group = "com.tms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework:spring-context:6.1.12")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.17.2")
    implementation("org.slf4j:slf4j-log4j12:2.1.0-alpha1")
    implementation("org.slf4j:slf4j-api:2.1.0-alpha1")
    implementation("org.aspectj:aspectjweaver:1.9.22.1")
}

tasks.test {
    useJUnitPlatform()
}