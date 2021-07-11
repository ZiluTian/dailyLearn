ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "dailyLearn",
    libraryDependencies += scalaTest % Test, 
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    unmanagedResourceDirectories in Compile += { baseDirectory.value / "src/main/scala/texts" }
    // libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3"
  )
