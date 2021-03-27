package dailyLearn.core
import scala.util.Random 

object backend {
  import config._

  private val p: parser = new parser(filename)

  private val sentences: List[String] = p.run()

  private def randomElement(l: List[String]): Option[String] = {
    l match {
      case Nil => None
      case _ => {
        val foo: Int = l.size 
        Some(l(Random.nextInt(foo)))
      }
    }
  }

  // API for the GUI. Return a random sentence. If chapter specified, then select from the chapter 
  def randomSentence(): String = {
      randomElement(sentences) match {
        case None => "No text available!"
        case Some(x) => x 
      }
    }
}