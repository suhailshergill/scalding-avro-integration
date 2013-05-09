package jobs

import java.io.File
import java.util.ArrayList
import scala.collection.JavaConverters._
import cascading.tuple.Tuple
import com.twitter.scalding._
import org.apache.avro.Protocol
import scalding.avro.UnpackedAvroSource
import types._

/** Generates a histogram of opaque colors that appear in a given set of images.
  *
  * This is an example scalding job that runs against avro data files,
  * where the source files for the input record types DO NOT EXIST on the current classpath.
  *
  * The avro protocol/schema may or may not exist on the classpath.
  *
  * The logic of the job is as follows:
  *   - Read through an input set of avro records that represent images.
  *   - For each pixel in an image, extract the color of the given pixel if it is opaque.
  *   - Count the occurences of opaque colors over all the images in our input set.
  *   - Sort by the frequency of occurence.
  */
class GenericExample(args: Args) extends Job(args) {

  private val defaultInputUrl = getClass.getResource("/data/images.avro")

  // optional: if the schema is not present on the classpath, UnpackedAvroSource will read it from the data file
  val colorSchema = {
    val resource = getClass.getResource("/generic_example.avpr")
    val file = new File(resource.toURI)
    Protocol.parse(file).getType("Image")
  }

  /** If no "--input /path/to/datafile" argument is given, use the example datafile on the classpath */
  UnpackedAvroSource(args.getOrElse("input", defaultInputUrl.toString), schema = Some(colorSchema))
    .read
    .flatMapTo('colors -> ('name, 'red, 'green, 'blue)) { colors: ArrayList[Tuple] =>
      for (color <- colors.asScala if color.getInteger(3) == 255) yield (
        color.getInteger(0),
        color.getInteger(1),
        color.getInteger(2),
        color.getString(4)
      )
    }
    .groupBy('name, 'red, 'green, 'blue) {
      _.size('count)
    }
    .groupAll {
      _.sortBy('count)
    }
    .debug
    .write(NullSource)
}
