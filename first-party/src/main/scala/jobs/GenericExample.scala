package jobs

import java.io.File
import java.util.ArrayList
import scala.collection.JavaConverters._
import cascading.tuple.Tuple
import com.twitter.scalding.{Args, Job, Tsv}
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

  // optional: if the schema is not present on the classpath, UnpackedAvroSource will read it from the data file
  val colorSchema = {
    val resource = getClass.getResource("/generic_example.avpr")
    val file = new File(resource.toURI)
    Protocol.parse(file).getType("Image")
  }

  UnpackedAvroSource(args("input"), schema = Some(colorSchema))
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
    .write(Tsv(args("output")))
}

/** For data generation only, this should remain commented out as long as Image and Color sources do NOT exist on the classpath. */
/*
object GenericExample {

  import java.lang.reflect.Modifier
  import java.util.UUID
  import java.util.regex.Pattern
  import scala.util.Random
  import org.apache.avro.file._
  import org.apache.avro.specific._

  // an array of available java.awt.Color instances
  private val Colors = classOf[java.awt.Color].getDeclaredFields.filter { field =>
    val modifiers = field.getModifiers
    val classType = field.getType
    val name = field.getName
    Modifier.isStatic(modifiers) && classType == classOf[java.awt.Color] && Pattern.matches("[A-Z]+", name)
  }.map { field =>
    (field.getName, field.get(null).asInstanceOf[java.awt.Color])
  }

  // generate avro data file containing n images
  def main(args: Array[String]) {
    val file = new File("/tmp/images.avro")
    val writer = new DataFileWriter[Image](new SpecificDatumWriter[Image](classOf[Image]))
    writer.create(Image.getClassSchema(), file)
    Seq.fill(args(0).toInt)(randomImage).foreach(writer.append)
    writer.close()
  }

  def randomImage: Image = {
    val image = new Image
    val width = Random.nextInt(64) + 1
    val height = Random.nextInt(64) + 1
    image.setWidth(width)
    image.setHeight(height)
    image.setColors(Seq.fill(Random.nextInt(width * height) + 1)(randomColor).asJava)
    image
  }

  def randomColor: Color = {
    val (name, instance) = Colors(Random.nextInt(Colors.size))
    val color = new Color
    color.setRed(instance.getRed)
    color.setGreen(instance.getGreen)
    color.setBlue(instance.getBlue)
    color.setAlpha(Random.nextInt(256))
    color.setName(name)
    color
  }
}
*/
