version := "0.1-SNAPSHOT"

name := "scalatitude"

organization := "com.sksamuel.scalatitude"

scalaVersion := "2.10.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

publishMavenStyle := true

publishArtifact in Test := false

parallelExecution in Test := false

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishTo <<= version {
  (v: String) =>
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := {
  <url>https://github.com/sksamuel/scalatitude</url>
    <licenses>
      <license>
        <name>The Apache Software License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:git@github.com:sksamuel/scalatitude.git</connection>
      <developerConnection>scm:git:git@github.com:sksamuel/scalatitude.git</developerConnection>
      <url>git@github.com:sksamuel/scalatitude.git</url>
    </scm>
    <developers>
      <developer>
        <name>Stephen Samuel</name>
        <email>sam@sksamuel.com</email>
        <timezone>GMT</timezone>
      </developer>
    </developers>
}
