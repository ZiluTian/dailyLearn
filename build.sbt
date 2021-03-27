import Dependencies._

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "dailyLearn",
    libraryDependencies += scalaTest % Test, 
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
    // libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.3"
  )
