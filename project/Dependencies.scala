import sbt._

object Test {
  def apply() = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
}

object App {
  def apply() = Seq(
  )
}

object IntTest {
  def apply() = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "it"
  )
}
