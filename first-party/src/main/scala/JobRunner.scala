import com.twitter.scalding.Tool
import org.apache.hadoop.util.ToolRunner
import org.apache.hadoop.conf.Configuration

object JobRunner {
  def main(args: Array[String]) {
    ToolRunner.run(new Configuration, new Tool, args)
  }
}
