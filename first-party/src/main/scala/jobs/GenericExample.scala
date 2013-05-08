package jobs

import java.util.ArrayList
import scala.collection.JavaConverters._
import cascading.tuple.Tuple
import com.twitter.scalding.{Args, Job, Tsv}
import org.apache.avro.generic.GenericData.Record
import scalding.avro.UnpackedAvroSource
import types._

class GenericExample(args: Args) extends Job(args) {

  UnpackedAvroSource(args("input"), schema = None)
    .read
    .flatMapTo('colors -> ('red, 'green, 'blue, 'alpha)) { colors: ArrayList[Tuple] =>
      for (color <- colors.asScala) yield (
        color.getInteger(0),
        color.getInteger(1),
        color.getInteger(2),
        color.getInteger(3)
      )
    }
    .groupBy('red, 'green, 'blue) {
      _.size('count)
    }
    .groupAll {
      _.sortBy('count)
    }
    .debug
    .write(Tsv(args("output")))
}

/** For data generation only, this should remain commented out as long as Image and Color sources do not exist on the classpath. */
/*
object GenericExample {

  import java.io.File
  import java.util.UUID
  import scala.util.Random
  import org.apache.avro.file._
  import org.apache.avro.specific._

  // generate avro data file containing n images
  def main(args: Array[String]) {
    val file = new File("/tmp/images.avro")
    val writer = new DataFileWriter[Image](new SpecificDatumWriter[Image](classOf[Image]))
    writer.create(Image.getClassSchema(), file)
    Seq.fill(args(0).toInt)(randomImage()).foreach(writer.append)
    writer.close()
  }

  def randomImage(): Image = {
    val image = new Image
    val width = Random.nextInt(64) + 1
    val height = Random.nextInt(64) + 1
    image.setWidth(width)
    image.setHeight(height)
    image.setColors(Seq.fill(Random.nextInt(width * height) + 1)(randomColor()).asJava)
    image
  }

  def randomColor(): Color = {
    val color = new Color
    color.setRed(Random.nextInt(256))
    color.setGreen(Random.nextInt(256))
    color.setBlue(Random.nextInt(256))
    color.setAlpha(Random.nextInt(256))
    color
  }
}
*/
