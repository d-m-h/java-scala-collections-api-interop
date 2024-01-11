package com.github.dmh

import java.util
import scala.collection.JavaConverters.seqAsJavaListConverter
import scala.util.Properties

class Demonstration212 extends Demonstration {
  override def getItems: util.List[String] = provideItems().asJava

  override def isCorrectScalaVersion: Boolean = Properties.versionNumberString startsWith getApplicableScalaVersion

  override def getApplicableScalaVersion: String = "2.12"

  private def provideItems(): Seq[String] = Seq("a", "b", "c")
}
