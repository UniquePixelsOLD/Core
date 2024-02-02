plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version ("8.1.1")
}

group = "net.uniquepixels"
version = "latest"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}


dependencies {
    implementation(project(":core-api"))

    compileOnly("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")

    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")


    // Javalin
    implementation("io.javalin:javalin:5.6.3")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.uniquepixels"
            artifactId = "core"
            version = this.version

            from(components["java"])
        }
    }
}

tasks.create("generateTemplate") {

}

tasks.create("ready") {
    dependsOn("build", "publishToMavenLocal")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }

   shadowJar {
       dependencies {
           include(dependency("net.uniquepixels:core-api:latest"))
       }
   }

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }


    reobfJar {
        // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
        // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
        outputJar.set(layout.buildDirectory.file("dist/Core-${project.version}.jar"))
    }
}