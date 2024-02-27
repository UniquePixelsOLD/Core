plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

group = "net.uniquepixels"
version = "latest"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mongodb:mongodb-driver-sync:4.10.1")

    implementation("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    implementation("org.reflections:reflections:0.10.2")
}

tasks {
    shadowJar {
        dependencies {
            include(dependency("org.mongodb:mongodb-driver-sync:4.10.1"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.uniquepixels"
            artifactId = "core-api"
            version = this.version

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "UniquePixels"
            url = uri("https://repo.uniquepixels.net/repository/minecraft")
            credentials {
                username = "projectwizard"
                password = System.getenv("UP_NEXUS_PASSWORD")
            }
        }
    }
}