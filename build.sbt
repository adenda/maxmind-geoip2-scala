lazy val packageInfo = Seq(
  organization := "com.sanoma.cda",
  name := "maxmind-geoip2-scala",
  version := "1.5.5"
)

lazy val scalaVersions = Seq(
  scalaVersion := "2.12.2",
  crossScalaVersions := Seq("2.11.8", "2.12.2")
)

val commonBuildLibs = Seq(
  "com.maxmind.geoip2"  % "geoip2"          % "2.9.0",
  "com.twitter"        %% "util-collection" % "6.45.0"
)
val commonTestLibs = Seq(
  "org.scalacheck" %% "scalacheck" % "1.13.5",
  "org.scalatest"  %% "scalatest"  % "3.0.3"
).map(_ % Test)

val scalaLangLibs = Seq("org.scala-lang.modules" %% "scala-xml" % "1.0.6")


lazy val root = (project in file("."))
  .settings(packageInfo: _*)
  .settings(scalaVersions: _*)
  .settings(libraryDependencies ++= (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 => commonBuildLibs ++ scalaLangLibs
    case _ => commonBuildLibs
  })
  )
  .settings(libraryDependencies ++= commonTestLibs)
  .settings(scalacOptions ++= Seq("-feature", "-deprecation"))
