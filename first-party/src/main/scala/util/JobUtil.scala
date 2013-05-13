package util

import com.twitter.scalding.Job

trait JobUtil {
  this: Job =>

  /** The default local filesystem location to use for input data */
  lazy val defaultLocalFsInput: String = null

  /** The default local filesystem location to use as the output directory */
  lazy val defaultLocalFsOutput: String = null

  /** Parse input and output locations from CLI arguments.
    *
    * Since <code>cascading.avro</code> and <code>scalding.avro</code> do not support <code>--local</code> mode,
    * add logic to our named argument parsing to decide whether or not to search the local filesystem for our jobs.
    *
    * Specifically, when Hadoop is configured to use the local filesystem:
    *   - if no <code>--input</code> argument is given, use the example avro datafile on the classpath
    *   - if no <code>--output</code> argument is given, no-op; else write results to output
    */
  val (input, output) = {
    if (config.get("fs.default.name").exists(_.toString.startsWith("file://"))) {
      if (defaultLocalFsInput == null && !args.boolean("input")) args.required("input")
      args.getOrElse("input", defaultLocalFsInput) -> args.getOrElse("output", defaultLocalFsOutput)
    } else {
      args("input") -> args("output")
    }
  }
}
