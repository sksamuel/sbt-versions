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
    checkVersions := {
      for ( module <- libraryDependencies.value ) {
        val artifact = new DefaultArtifact(s"${module.organization}:${module.name}:[${module.revision},)")
        streams.value.log.info(s"[sbt-versions] checking $module...")
        val latest = latestRevision(artifact)
        if (latest != module.revision)
          streams.value.log.info(s"[info] [sbt-versions] Updated version available [$latest]")
      }
    }
  )

  private def latestRevision(artifact: Artifact): String = {

    val central = new RemoteRepository.Builder("central", "default", "http://repo1.maven.org/maven2/").build()

    val locator = MavenRepositorySystemUtils.newServiceLocator()
    locator.addService(classOf[RepositoryConnectorFactory], classOf[BasicRepositoryConnectorFactory])
    locator.addService(classOf[TransporterFactory], classOf[HttpTransporterFactory])

    val system: RepositorySystem = locator.getService(classOf[RepositorySystem])

    val session = MavenRepositorySystemUtils.newSession()
    val localRepo = new LocalRepository("target/local-repo")
    session.setLocalRepositoryManager(system.newLocalRepositoryManager(session, localRepo))

    val req = new VersionRangeRequest()
    req.setArtifact(artifact)
    req.addRepository(central)

    val range = system.resolveVersionRange(session, req)
    range.getHighestVersion.toString
  }

}