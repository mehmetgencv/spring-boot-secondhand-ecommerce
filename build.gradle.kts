plugins {
	java
	kotlin("jvm") version "1.9.20"
	id("org.springframework.boot") version "3.1.7"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.mehmetgenc"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation(project(":user"))
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.20")
	implementation(kotlin("reflect", "1.9.20"))
	implementation("org.springframework.security:spring-security-config:6.2.1")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
kotlin {
	jvmToolchain(17)
}
