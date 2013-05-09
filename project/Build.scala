import sbt._
import Keys._

import sbtassembly.Plugin._
import AssemblyKeys._

object AggregateBuild extends Build {

  val ScalaVersion = "2.9.2"

  val HadoopVersion = "1.0.3"

  val CascadingVersion = "2.1.5"

  val ScaldingVersion = "0.8.2"

  val AvroVersion = "1.7.4"

  val commonSettings = Defaults.defaultSettings ++ Seq(
    scalaVersion := ScalaVersion,
    resolvers ++= Seq(
      "Concurrent Maven Repo" at "http://conjars.org/repo",
      "Maven Central Repo"    at "http://repo1.maven.org/maven2",
      "Sonatype Snapshots"    at "http://oss.sonatype.org/content/repositories/snapshots",
      "Sonatype Releases"     at "http://oss.sonatype.org/content/repositories/releases"
    )
  )

  val commonDependencies = Seq(
    "org.apache.hadoop"   % "hadoop-core" % HadoopVersion,
    "org.apache.avro"     % "avro"        % AvroVersion,
    "org.apache.avro"     % "avro-mapred" % AvroVersion,

    "cascading" % "cascading-core"    % CascadingVersion,
    "cascading" % "cascading-hadoop"  % CascadingVersion,
    "cascading" % "cascading-local"   % CascadingVersion,
    "cascading" % "cascading-xml"     % CascadingVersion
  )

  val commonExcludedJars = Set(
    "jsp-api-2.1-6.1.14.jar",
    "jsp-2.1-6.1.14.jar",
    "jasper-compiler-5.5.12.jar",
    "janino-2.5.16.jar",
    "avro-tools-1.7.4.jar"
  )

  val commonMergeStrategy: PartialFunction[String, MergeStrategy] = {
    case fp if fp.endsWith(".class") => MergeStrategy.last
    case fp if fp.endsWith("project.clj") => MergeStrategy.concat
    case fp if fp.endsWith(".html") => MergeStrategy.last
  }

  val generateSpecifics = TaskKey[Unit]("generate-specifics", "Generate Java sources from avro schemas")

  val generateSpecificsTask = generateSpecifics <<= (managedClasspath in Compile).map { cp =>
    cp.files.find(_.getName.contains("avro-tools")).foreach { file =>
      ("bin/generate_specifics.sh " + file.getAbsolutePath) !
    }
  }

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = commonSettings ++ Seq(
      generateSpecificsTask,
      libraryDependencies ++= Seq(
        "org.apache.avro" % "avro-tools" % AvroVersion
      )
    )
  ) aggregate(scalding, cascadingAvro, scaldingAvro, firstParty)

  lazy val firstParty = Project(
    id = "first-party",
    base = file("first-party"),
    settings = commonSettings ++ assemblySettings ++ Seq(
      libraryDependencies ++= Seq(
        "org.xerial.snappy" % "snappy-java" % "1.0.5-M3"
      ),
      mainClass in (Compile, run) := Some("com.twitter.scalding.Tool"),
      mainClass in (Compile, assembly) := Some("com.twitter.scalding.Tool"),
      excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
        cp.filter(jar => commonExcludedJars.apply(jar.data.getName))
      },
      mergeStrategy in assembly <<= (mergeStrategy in assembly) { dedup =>
        commonMergeStrategy orElse {
          case fp => dedup(fp)
        }
      }
    )
  ) dependsOn (scaldingAvro)

  lazy val scaldingAvro = Project(
    id = "scalding-avro",
    base = file("scalding.avro")
  ) dependsOn (cascadingAvro, scalding)

  lazy val cascadingAvro = Project(
    id = "cascading-avro",
    base = file("cascading.avro/scheme"),
    settings = commonSettings ++ Seq(
      libraryDependencies ++= commonDependencies ++ Seq(
        "junit"         % "junit"        % "4.8" % "test",
        "org.hamcrest"  % "hamcrest-all" % "1.3" % "test"
      )
    )
  )

  lazy val scalding = Project(
    id = "scalding",
    base = file("scalding"),
    settings = commonSettings ++ assemblySettings ++ Seq(
      libraryDependencies ++= commonDependencies ++ Seq(
        "commons-lang"        % "commons-lang"    % "2.4",
        "cascading.kryo"      % "cascading.kryo"  % "0.4.6",
        "com.joestelmach"     % "natty"           % "0.7",
        "log4j"               % "log4j"           % "1.2.17",
        "io.backchat.jerkson" % "jerkson_2.9.2"   % "0.7.0",

        "com.twitter" % "maple"           % "0.2.5",
        "com.twitter" % "chill_2.9.2"     % "0.1.2",
        "com.twitter" % "algebird_2.9.2"  % "0.1.6",

        "org.scala-tools.testing"  % "specs_2.9.0-1"  % "1.6.8"   % "test",
        "org.scalacheck"          %% "scalacheck"     % "1.10.0"  % "test"
      ),
      parallelExecution in Test := false,
      test in assembly := {},
      jarName in assembly := "scalding-assembly-" + ScaldingVersion + ".jar",
      excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
        cp.filter(jar => commonExcludedJars.apply(jar.data.getName))
      },
      mergeStrategy in assembly <<= (mergeStrategy in assembly) { dedup =>
        commonMergeStrategy orElse {
          case fp => dedup(fp)
        }
      }
    )
  )
}
