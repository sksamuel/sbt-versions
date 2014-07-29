package com.sksamuel.sbt.versions

import sbt._
import sbt.Keys._

import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.artifact.{Artifact, DefaultArtifact}
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.repository.{LocalRepository, RemoteRepository}
import org.eclipse.aether.resolution.VersionRangeRequest
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.http.HttpTransporterFactory

/** @author Stephen Samuel */
object SbtVersionsPlugin extends AutoPlugin {

  object autoImport {
    lazy val checkVersions = taskKey[Unit]("check for updated project dependencies")
  }

  import autoImport._

  override def trigger = allRequirements
  override lazy val projectSettings = Seq(
    parallelExecution in GlobalScope := false,
    checkVersions := {
      val deps = libraryDependencies.value
      val projectName = name.value
      streams.value.log.info(s"[sbt-versions] ${deps.size} dependencies to check for [$projectName]")
      streams.value.log.info("--------------------------------------------------------------")
      for ( module <- deps ) {
        val latest = latestRevision(module)
        if (latest != module.revision)
          streams.value.log.info(s"[sbt-versions] Update available $module -> $latest")
      }
    }
  )

  private def latestRevision(module: ModuleID): String = {

    val artifact = new DefaultArtifact(s"${module.organization}:${module.name}:[${module.revision},)")
    val central = new RemoteRepository.Builder("central", "default", "http://repo1.maven.org/maven2/").build()

    val locator = MavenRepositorySystemUtils.newServiceLocator()
    locator.addService(classOf[RepositoryConnectorFactory], classOf[BasicRepositoryConnectorFactory])
    locator.addService(classOf[TransporterFactory], classOf[HttpTransporterFactory])

    val system = locator.getService(classOf[RepositorySystem])

    val session = MavenRepositorySystemUtils.newSession()
    val localRepo = new LocalRepository("target/local-repo")
    session.setLocalRepositoryManager(system.newLocalRepositoryManager(session, localRepo))

    val req = new VersionRangeRequest()
    req.setArtifact(artifact)
    req.addRepository(central)

    val range = system.resolveVersionRange(session, req)
    Option(range).flatMap(arg => Option(arg.getHighestVersion)).map(_.toString).getOrElse(module.revision)
  }

}