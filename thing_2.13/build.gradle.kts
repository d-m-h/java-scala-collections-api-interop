plugins {
    id("scala")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.scala-lang:scala-library:2.13.10")
    compileOnly(project(":core"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
