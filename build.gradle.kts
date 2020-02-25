plugins {
    kotlin("jvm") version "1.3.61"
    maven
}

group = "com.dt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.querydsl:querydsl-apt:4.2.1")
    implementation("org.springframework.data:spring-data-mongodb:2.2.4.RELEASE")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}