sbt-versions
===========

SBT plugin for checking for the availability of updated versions of your project's dependencies.

### This plugin is not maintained anymore, there is a better version here: https://github.com/rtimush/sbt-updates

### How to use

Add the plugin to your SBT by adding this to project/plugins.sbt

```
addSbtPlugin("com.sksamuel.sbt-versions" % "sbt-versions" % "0.2.0")
```

Note that this is an auto plugin so only works with SBT 0.13.5 or higher.

Then simply running `sbt checkVersions` will cause SBT to check all your declared dependencies against maven central. Any deps that have updated versions available will be highlighted in the console.

### Example

Running this plugin on a project of mine gives:

```
[info] [sbt-versions] 35 dependencies to check for [myproject]
[info] --------------------------------------------------------------
[info] [sbt-versions] Update available org.scala-lang:scala-library:2.11.1 [latest: 2.11.2]
[info] [sbt-versions] Update available org.scala-lang:scala-reflect:2.11.1 [latest: 2.11.2]
[info] [sbt-versions] Update available org.scala-lang:scala-compiler:2.11.1 [latest: 2.11.2]
[info] [sbt-versions] Update available net.sf.uadetector:uadetector-resources:2014.04 [latest: 2014.06]
[info] [sbt-versions] Update available com.fasterxml.jackson.core:jackson-core:2.4.1 [latest: 2.4.1.1]
[info] [sbt-versions] Update available com.fasterxml.jackson.core:jackson-databind:2.4.1 [latest: 2.4.1.3]
[info] [sbt-versions] Update available org.codehaus.woodstox:woodstox-core-asl:4.3.0 [latest: 4.4.0]
[info] [sbt-versions] Update available com.amazonaws:aws-java-sdk:1.7.10 [latest: 1.8.6]
[info] [sbt-versions] Update available joda-time:joda-time:2.3 [latest: 2.4]
[info] [sbt-versions] Update available com.sendgrid:sendgrid-java:1.0.0 [latest: 1.1.0]
[info] [sbt-versions] Update available com.sendgrid:smtpapi-java:0.0.2 [latest: 0.1.1]
[info] [sbt-versions] Update available com.stripe:stripe-java:1.14.0 [latest: 1.15.1]
[info] [sbt-versions] Update available org.apache.commons:commons-email:1.3.2 [latest: 1.3.3]
```
