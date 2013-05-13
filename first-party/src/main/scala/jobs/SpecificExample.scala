package jobs

import scala.collection.JavaConverters._
import com.twitter.scalding._
import scalding.avro.PackedAvroSource
import types._
import util.JobUtil

/** Enumerates a list of "clicks" that are nearest a given document's "targets".
  *
  * This is an example scalding job that runs against avro data files,
  * where the source files for the input record types EXIST on the current classpath.
  *
  * The logic of the job is as follows:
  *   - Read through an input set of avro records that represent documents.
  *   - For each document, filter out any clicks outside of its target.
  *   - Calculate the distance between "target clicks" and the target's center point.
  *   - Sort the resulting list of "target clicks" by their distance to the center point.
  */
class SpecificExample(args: Args) extends Job(args) with JobUtil {

  override lazy val defaultLocalFsInput = getClass.getResource("/data/documents.avro").toString

  /** Euclidean distance between two points */
  def distance(a: Coordinates, b: Coordinates): Double = {
    math.sqrt(math.pow(a.getX - b.getX, 2) + math.pow(a.getY - b.getY, 2))
  }

  /** Does the circle of radius at origin contain point a? */
  def contains(origin: Coordinates, radius: Int)(a: Coordinates): Boolean = {
    math.pow(a.getX - origin.getX, 2) + math.pow(a.getY - origin.getY, 2) <= math.pow(radius, 2)
  }

  PackedAvroSource[Document](input)
    .read
    .flatMapTo('Document -> ('documentId, 'targetId, 'sessionId, 'bullseye, 'click, 'distance)) { doc: Document =>
      doc.getTargets.asScala.flatMap { target =>
        val bullseye = target.getOrigin 
        val targetContains = contains(bullseye, target.getRadius) _

        for {
          session <- doc.getSessions.asScala
          click <- session.getClicks.asScala if targetContains(click)
        } yield (
          doc.getId,
          target.getId,
          session.getId,
          "%s,%s".format(bullseye.getX, bullseye.getY),
          "%s,%s".format(click.getX, click.getY),
          distance(bullseye, click)
        )
      }
    }
    .groupAll {
      _.sortBy('distance)
    }
    .debug
    .write(Option(output).map(Tsv(_)).getOrElse(NullSource))
}

object SpecificExample {

  import java.io.File
  import java.util.UUID
  import scala.util.Random
  import org.apache.avro.file._
  import org.apache.avro.specific._

  // generate avro datafile containing n targets
  def main(args: Array[String]) {
    val file = new File(args(1))
    val writer = new DataFileWriter[Document](new SpecificDatumWriter[Document](classOf[Document]))
    writer.create(Document.getClassSchema(), file)
    Seq.fill(args(0).toInt)(randomDocument).foreach(writer.append)
    writer.close()
  }

  def randomDocument: Document = {
    val document = new Document
    document.setId(UUID.randomUUID.toString)
    document.setTargets(Seq.fill(Random.nextInt(3) + 1)(randomTarget).asJava)
    document.setSessions(Seq.fill(Random.nextInt(64) + 1)(randomSession).asJava)
    document
  }

  def randomTarget: Target = {
    val target = new Target
    target.setId(UUID.randomUUID.toString)
    target.setOrigin(randomCoordinates)
    target.setRadius(Random.nextInt(10) + 1)
    target
  }

  def randomSession: Session = {
    val session = new Session
    session.setId(UUID.randomUUID.toString)
    session.setClicks(Seq.fill(Random.nextInt(50))(randomCoordinates).asJava)
    session
  }

  def randomCoordinates: Coordinates = {
    val x = Random.nextInt(100)
    val y = Random.nextInt(100)
    val coords = new Coordinates
    coords.setX(x)
    coords.setY(y)
    coords
  }
}
