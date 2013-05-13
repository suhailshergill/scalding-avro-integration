scalding-avro-integration
=========================
A project skeleton for running scalding jobs against avro datafiles.

Setup
-----
Clone the repository and initialize the required submodules:

```sh
git clone git@github.com:Tapad/scalding-avro-integration.git
git submodule update --init
```

Project structure
-----------------
This project consists of three submodules which are tuned specifically for `scalding-avro-integration`.

The skeleton's example jobs&mdash;and any scalding jobs you plan on scripting&mdash;are to be placed under the `first-party` directory.

Specific vs. Generic
--------------------
Two different ways to work with avro datafiles are covered by this project's examples.

The `SpecificExample` job requires an avro datafile's `SpecificRecord` sources to be present on the classpath,
while the `GenericExample` job works with avro datafiles wherein the contained avro records' compiled sources are **not** on the classpath.

As you'll see in the examples, users of the *generic-style* sacrifice type-safety for versatility.

Running jobs
------------
Jobs can be run interactively through `sbt` or from an assembled jar file.

To run a job via `sbt`, issue the following commands from the project's root directory:

```sh
sbt
> project first-party
> run jobs.YourJob --hdfs --input /path/to/your/datafile.avro
```

Note that, by default, we are running this job against the local filesystem.

[cascading.avro](cascading.avro) and [scalding.avro](scalding.avro) currently do **not** support the `--local` command line argument.

When running locally, if no `--input` argument is passed to the example jobs, the jobs will load example data from the project classpath.

To run a job against your Hadoop cluster, first assemble the fat jar of your jobs and their dependencies:

```sh
sbt
> project first-party
> assembly
```

The `sbt assembly` task will place the jar file in the project's `target` directory.

Proceed to run the job against your cluster:

```sh
hadoop jar /path/to/YourJob.jar jobs.YourJob --hdfs --input /path/to/your/datafile.avro --output /path/to/desired/output/dir
```

When running against your cluster, `--input` and `--output` arguments are required for the example jobs.

Data for the example jobs can be found in the [first-party/src/main/resources/data](first-party/src/main/resources/data) directory.

Examples
--------

### SpecificExample
The `SpecificExample` job that ships with this repository is an example of a scalding job that reads avro datafiles using `SpecificRecord` semantics.

It requires that the Java source files for the type of record to be processed exist on the job's classpath.

The type contained in the `SpecificExample`'s avro datafiles is a `Document`.

A `Document` is comprised of a set of circular `Target`s and a set of `Session`s. A `Session` contains a history of "clicks", which are represented as Cartesian coordinates.

Refer to the [first-party/src/main/resources](first-party/src/main/resources) directory for the avro IDL of the `Document` type.

Refer to the [first-party/src/main/java](first-party/src/main/java) directory for the generated source files.

The `SpecificExample` enumerates the listing of session "clicks" that occur within a `Document`'s targets, sorted by the nearest distance to the target.

While the nature of the example is contrived, it is solely meant to illustrate the usefulness of using `SpecificRecord`s to analyze and process complex, nested avro structures.

### GenericExample
The `GenericExample` reads from avro datafiles and implements its logic using the `org.apache.avro.generic` API, wherein the parsed `GenericRecord`s are encapsulated by `cascading.tuple.Tuple`s.

The `GenericExample` requires that the Java sources for its datafiles must **not** appear on the job's classpath.

While the schema for the `GenericExample` exists on the classpath, it is not required to run the job.

We demonstrate the **generic-style** API with the following example:

> Given a series of `Image`s, calculate the frequency of opaque colors that appear across all `Image`s

Refer to the [first-party/src/main/resources](first-party/src/main/resources) directory for the avro IDL of the `Image` type.

Source Code Generation
----------------------
An `sbt` task exists to generate Java sources for any avro schemas you place in [first-party/src/main/resources](first-party/src/main/resources).

The task key is `generate-specifics` and is available from the `root` project.

It takes the basenames of the avro schema or IDL files you want to generate Java sources for as its CLI arguments.

As an example, the Java sources for the `SpecificExample` would be compiled from the following interactive session:

```sh
sbt
> generate_specifics specific_example.avdl
```

The `generate-specifics` task will execute the `generate_specifics.sh` script located in the [bin](bin) directory.

Refer to [bin/generate_specifics.sh](bin/generate_specifics.sh) for more information.
