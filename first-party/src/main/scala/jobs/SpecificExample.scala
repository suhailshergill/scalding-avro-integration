package jobs

import scala.collection.JavaConverters._
import com.twitter.scalding.{Args, Job, Tsv}
import scalding.avro.PackedAvroSource
import types._

class SpecificExample(args: Args) extends Job(args) {

  /** Euclidean distance between two points */
  def distance(a: Coordinates, b: Coordinates): Double = {
    math.sqrt(math.pow(a.getX - b.getX, 2) + math.pow(a.getY - b.getY, 2))
  }

  /** Does the circle of radius at origin contain point a? */
  def contains(origin: Coordinates, radius: Int)(a: Coordinates): Boolean = {
    math.pow(a.getX - origin.getX, 2) + math.pow(a.getY - origin.getY, 2) <= math.pow(radius, 2)
  }

  PackedAvroSource[Target](args("input"))
    .read
    .flatMapTo('Target -> ('targetId, 'sessionId, 'distance)) { target: Target =>
      val origin = target.getOrigin
      val targetContains = contains(origin, target.getRadius)(_)

      for {
        click <- target.getClicks.asScala
        pixel <- click.getPixels.asScala if targetContains(pixel)
      } yield (
        target.getId,
        click.getSessionId,
        distance(origin, pixel)
      )
    }
    .groupAll {
      _.sortBy('distance)
    }
    .debug
    .write(Tsv(args("output")))
}

object SpecificExample {

  import java.io.File
  import java.util.UUID
  import scala.util.Random
  import org.apache.avro.file._
  import org.apache.avro.specific._

  // generate avro data file containing n targets
  def main(args: Array[String]) {
    val file = new File("/tmp/targets.avro")
    val writer = new DataFileWriter[Target](new SpecificDatumWriter[Target](classOf[Target]))
    writer.create(Target.getClassSchema(), file)
    Seq.fill(args(0).toInt)(randomTarget()).foreach(writer.append)
    writer.close()
  }

  def randomTarget(): Target = {
    val target = new Target
    target.setId(UUID.randomUUID().toString)
    target.setOrigin(randomCoordinates())
    target.setRadius(Random.nextInt(10) + 1)
    target.setClicks(Seq.fill(Random.nextInt(50))(randomClickHistory()).asJava)
    target
  }

  def randomClickHistory(): ClickHistory = {
    val history = new ClickHistory
    history.setSessionId(UUID.randomUUID().toString)
    history.setPixels(Seq.fill(Random.nextInt(50))(randomCoordinates()).asJava)
    history
  }

  def randomCoordinates(): Coordinates = {
    val x = Random.nextInt(100)
    val y = Random.nextInt(100)
    val location = new Coordinates
    location.setX(x)
    location.setY(y)
    location
  }
}
