organization := "com.sksamuel"

name := "sbt-versions"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

sbtPlugin := true

publishMavenStyle := true

publishArtifact in Test := false

parallelExecution in Test := false