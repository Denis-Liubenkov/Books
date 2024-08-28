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
}

tasks.test {
    useJUnitPlatform()
}