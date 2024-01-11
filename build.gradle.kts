plugins {
    id("java")
}

configurations {
    create("thing212")
    create("thing213")
    create("thing212Scala213")
    create("thing213Scala212")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    "thing212"(project(":app"))
    "thing212"(project(":thing_2.12"))
    "thing212"("org.scala-lang:scala-library:2.12.12")

    "thing213"(project(":app"))
    "thing213"(project(":thing_2.13"))
    "thing213"("org.scala-lang:scala-library:2.13.10")

    "thing212Scala213"(project(":app"))
    "thing212Scala213"(project(":thing_2.12"))
    "thing212Scala213"("org.scala-lang:scala-library:2.13.10")

    "thing213Scala212"(project(":app"))
    "thing213Scala212"(project(":thing_2.13"))
    "thing213Scala212"("org.scala-lang:scala-library:2.12.12")
}

tasks.register<JavaExec>("runWithThing212") {
    group = "execution"
    description = "Executes app with thing_2.12"
    classpath = configurations["thing212"]
    mainClass = "com.github.dmh.Application"
}

tasks.register<JavaExec>("runWithThing213") {
    group = "execution"
    description = "Executes app with thing_2.13"
    classpath = configurations["thing213"]
    mainClass = "com.github.dmh.Application"
}

tasks.register<JavaExec>("runWithThing212Scala213") {
    group = "execution"
    description = "Executes app with thing_2.12 and scala 2.13"
    classpath = configurations["thing212Scala213"]
    mainClass = "com.github.dmh.Application"
}

tasks.register<JavaExec>("runWithThing213Scala212") {
    group = "execution"
    description = "Executes app with thing_2.13 and scala 2.12"
    classpath = configurations["thing213Scala212"]
    mainClass = "com.github.dmh.Application"
}
