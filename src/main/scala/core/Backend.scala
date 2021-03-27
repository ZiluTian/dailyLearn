package dailyLearn.core
import scala.util.Random 

object backend {
  import config._

  val p: parser = new parser(filename, chapterIdentifier)

  private val chapterSentences: Map[String, List[String]] = p.run()

  private val chapterTitles: List[String] = chapterSentences.keySet.toList 

  def randomElement(l: List[String]): Option[String] = {
    l match {
      case Nil => None
      case _ => {
        val foo: Int = l.size 
        Some(l(Random.nextInt(foo)))
      }
    }
  }

  // Return a random sentence. If chapter specified, then select from the chapter 
  def randomSentence(): String = {
    var fromChapter: String = ""
    var res: String = ""
    do {
      fromChapter = randomElement(chapterTitles).get
      randomElement(chapterSentences(fromChapter)) match {
        case None => 
        case Some(x) => res = x 
      }
    } while (res.isEmpty)
    res 
  }
}