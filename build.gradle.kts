import org.cyclonedx.model.Component

group = "com.validation"
version = "1.0.0"

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("org.cyclonedx.bom") version "3.2.4"
}

allprojects {
    tasks.cyclonedxDirectBom {
        // Opción recomendada: Usar listOf() en lugar de [...]
        includeConfigs.set(listOf("releaseRuntimeClasspath"))
        skipConfigs.set(listOf("androidTestRuntimeClasspath", "testRuntimeClasspath"))

        projectType = Component.Type.APPLICATION
        xmlOutput.unsetConvention()
    }
}

tasks.cyclonedxBom {
    projectType = Component.Type.APPLICATION
    componentName = "Test Project"
    componentVersion = "1.0.0"
    includeBomSerialNumber = true
    xmlOutput.unsetConvention()
    val runNumber = System.getenv("GITHUB_RUN_NUMBER")?.toIntOrNull() ?: 1
    // Asignar ese número a la versión del documento SBOM
    version = runNumber
}