plugins {
    checkstyle
    alias(libs.plugins.fabric.loom)
}

group = "com.github.amyavi"
version = "1.1.0"

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())

    modImplementation(libs.fabric.loader)
}

loom {
    runtimeOnlyLog4j = true
    serverOnlyMinecraftJar()

    runs {
        remove(runs["client"])
    }
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
        "java_version" to libs.versions.java.get(),
        "minecraft_version" to libs.versions.minecraft.get(),
        "fabric_loader_version" to libs.versions.fabric.loader.get()
    )

    inputs.properties(properties)
    filesMatching(listOf("fabric.mod.json", "correct-universe.mixins.json")) {
        expand(properties)
    }
}
