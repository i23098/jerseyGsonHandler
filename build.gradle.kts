plugins {
    java
    war

    id("org.gretty") version "4.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.glassfish.jersey.core:jersey-server:3.1.3")

    runtimeOnly("org.glassfish.jersey.containers:jersey-container-servlet:3.1.3")
    runtimeOnly("org.glassfish.jersey.inject:jersey-hk2:3.1.3")
}
