plugins {
    id("java")
    id("application")
}

group = "org.github.zkkv"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.github.zkkv.Main")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("fr.inria.gforge.spoon:spoon-core:11.0.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    args = listOf("src/main/resources/Sample.java")
}