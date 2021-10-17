import org.apache.tools.ant.filters.ReplaceTokens
plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
}

group = "net.gigaclub"
version = "1.17.1.1.0.0"

apply(plugin = "java")

group = project.group
version = project.version

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_16
}

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        name = "extendedclip"
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.10")
    compileOnly("org.jetbrains:annotations:22.0.0")
    annotationProcessor("org.jetbrains:annotations:22.0.0")
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
}

tasks {
    // If you open resources/plugins.yml you will see "@version@" as the version this code replaces this
    processResources {
        from(sourceSets["main"].resources) {
            val tokens = mapOf("version" to version)
            filter(ReplaceTokens::class, mapOf("tokens" to tokens))
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }
}