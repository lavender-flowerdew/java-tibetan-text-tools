import NativePackagerHelper._
import de.heikoseeberger.sbtheader.HeaderPattern
import de.heikoseeberger.sbtheader.license.Apache2_0
import de.heikoseeberger.sbtheader.CommentStyleMapping._

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(AutomateHeaderPlugin)
  .configs( IntegrationTest, jacoco.Config )
  .settings( Defaults.itSettings : _* )
  .settings(jacoco.settings: _*)
  .settings(
    inThisBuild(List(
      organization := "org.flowerdew.lavender",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "java-tibetan-text-tools",
    libraryDependencies ++= App() ++ Test() ++ IntTest(),
    licenses += ("Apache-2.0", url("http://apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-feature",
      "-unchecked",
      "-deprecation"
    ),
    javacOptions ++= Seq("-source", "1.5"),
    compileOrder in Compile := CompileOrder.JavaThenScala,
    //compileOrder in Test := CompileOrder.Mixed,
    unmanagedSourceDirectories in Compile := (javaSource in Compile).value :: Nil,
    Defaults.itSettings,
    parallelExecution in IntegrationTest := false,
    parallelExecution in jacoco.Config := false,
    jacoco.reportFormats in jacoco.Config := Seq(
      de.johoop.jacoco4sbt.XMLReport(encoding = "utf-8"),
      de.johoop.jacoco4sbt.HTMLReport(encoding = "utf-8"),
      de.johoop.jacoco4sbt.ScalaHTMLReport(withBranchCoverage = true)),
    testOptions in IntegrationTest += Tests.Argument(TestFrameworks.ScalaTest, "-n", "org.scalatest.tags.Slow"),
    headers := createFrom(Apache2_0, "2017", "Lavender Flowerdew"),
    test in assembly := {},
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
  )

HeaderPlugin.settingsFor(IntegrationTest)
AutomateHeaderPlugin.automateFor(IntegrationTest)
