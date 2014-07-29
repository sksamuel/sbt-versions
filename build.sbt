organization := "com.sksamuel"

name := "sbt-versions"

version := "0.1.0"

scalaVersion := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

libraryDependencies += "org.eclipse.aether" % "aether" % "1.0.0.v20140518"

libraryDependencies += "org.eclipse.aether" % "aether-api" % "1.0.0.v20140518"

libraryDependencies += "org.eclipse.aether" % "aether-impl" % "1.0.0.v20140518"

libraryDependencies += "org.eclipse.aether" % "aether-util" % "1.0.0.v20140518"

libraryDependencies += "org.eclipse.aether" % "aether-connector-basic" % "1.0.0.v20140518"

libraryDependencies += "org.eclipse.aether" % "aether-transport-http" % "1.0.0.v20140518"

libraryDependencies += "org.apache.maven" % "maven-aether-provider" % "3.2.2"

sbtPlugin := true

publishTo := Some(Resolver.url("sbt-plugin-releases",
  new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns))

publishMavenStyle := false

publishArtifact in Test := false

parallelExecution in Test := false