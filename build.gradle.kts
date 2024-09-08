plugins {
    alias(libs.plugins.fabric.loom)
}

group = project.property("group") as String
version = project.property("version") as String

loom {
    runtimeOnlyLog4j = true
    serverOnlyMinecraftJar()

    runs {
        remove(runs["client"])
    }
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())

    modImplementation(libs.fabric.loader)
}

java {
    val javaVersion = JavaVersion.toVersion(libs.versions.java.get())
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion

    toolchain.languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
}

tasks.processResources {
    val properties = mapOf(
        "version" to version,
        "fabric_loader_version" to libs.versions.fabric.loader.get(),
        "java_version" to libs.versions.java.get()
    )

    inputs.properties(properties)
    filesMatching(listOf("fabric.mod.json", "correct-universe.mixins.json")) {
        expand(properties)
    }
}
