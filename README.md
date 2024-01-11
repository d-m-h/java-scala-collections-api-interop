# Consuming Scala Collections from Java in Scala 2.12 and Scala 2.13

## Abstract

This project delves into the complexities of Java's interoperability with Scala collections, particularly in the context of Scala versions 2.12 and 2.13. It focuses on the `Seq` type and related issues in `IndexedSeq`, aiming to clarify the challenges in the Scala Collections API for Java developers, with comprehensive analysis, practical examples, and proposed solutions.

## Summary

Java-Scala interoperability challenges stem from changes in Scala's Collections API between versions 2.12 and 2.13. The primary issue is the difference in the `Seq` type: `scala.collection.Seq` in Scala 2.12 and `scala.collection.immutable.Seq` in Scala 2.13. These disparities can cause significant compatibility issues in Java applications, necessitating careful management of Scala versions.

## Deeper Explanation

### Historical Context

Previously, Java developers could interact with Scala collections (versions 2.10, 2.11, 2.12) with less difficulty. Method signatures remained consistent across these versions, insulating Java code from deeper runtime errors in Scala. Problems were minimized as long as Scala libraries in the classpath were version-consistent.

### The Shift in Scala 2.13

With Scala 2.13's alterations in the Collections API, it becomes crucial to be mindful of the Scala versions used in dependencies. Java code compiled against Scala 2.12 libraries might fail at runtime in an environment with Scala 2.13 libraries, often resulting in `NoSuchMethodError`.

### Example

Scala Code:
```scala
object Thing {
  def items(): Seq[String] = Seq("a", "b", "c")
}
```

Java's perspective:
- Scala 2.12: `scala.collection.Seq`
- Scala 2.13: `scala.collection.immutable.Seq`

Java code consuming `Thing`:

- Compiled against Scala 2.12, but running with Scala 2.13:
  ```java
  scala.collection.Seq<String> items = Thing.items(); // Fails at runtime with NoSuchMethodError
  ```

- Compiled against Scala 2.13, but running with Scala 2.12:
  ```java
  scala.collection.immutable.Seq<String> items = Thing.items(); // Fails at runtime with NoSuchMethodError
  ```

In both scenarios, the Java code encounters runtime errors due to the version mismatch between the compiled and runtime environments.

## Recommendations

1. **Version Consistency:** Ensure the Scala version during compilation matches the runtime environment. Code compiled with Scala 2.12 should use Scala 2.12 libraries, and the same applies for Scala 2.13.

2. **Immediate Java Conversion:** Convert Scala `Seq` to Java collections immediately upon receipt. Use `JavaConverters.seqAsJavaList` for this purpose. Avoid writing Scala collection types (`scala.collection.Seq` or `scala.collection.immutable.Seq`) directly into Java class files to prevent compatibility issues.

```java
public class ThingConsumer {
  public static void main(String[] args) {
      // Correct approach
      java.util.List<String> items = Converter.convert(Thing.items());
      
      // Avoid this method
      scala.collection.Seq<String> items = Thing.items();
      java.util.List<String> items2 = Converter.convert(items);
  }
}
```

## Execution Guide

This guide provides instructions on how to execute the different tasks defined in our Gradle build script. These tasks are designed to run the application with various configurations, particularly focusing on different versions of Scala and their interoperability with Java.

### Prerequisites

Before executing any tasks, ensure you have the following prerequisites:

- Java JDK installed and configured
- Gradle installed and configured
- All project dependencies resolved via Gradle

### Task Execution

The project defines several tasks to run the application under different configurations. Below are the commands to execute each task.

#### Run with Scala 2.12

To execute the application with Scala version 2.12, use the following command:

```bash
gradle runWithThing212
```

This task runs the application using the `thing_2.12` module and Scala library version 2.12.12.

#### Run with Scala 2.13

To execute the application with Scala version 2.13, use the following command:

```bash
gradle runWithThing213
```

This task runs the application using the `thing_2.13` module and Scala library version 2.13.10.

#### Run with Scala 2.12 and Scala 2.13 Library

To execute the application with the `thing_2.12` module but using Scala library version 2.13.10, use the command:

```bash
gradle runWithThing212Scala213
```

This configuration demonstrates the interoperability challenges when using a mismatched Scala library version.

#### Run with Scala 2.13 and Scala 2.12 Library

To execute the application with the `thing_2.13` module but using Scala library version 2.12.12, use the command:

```bash
gradle runWithThing213Scala212
```

This configuration also highlights the potential runtime issues due to version incompatibilities between Scala libraries.
