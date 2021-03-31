package dailyLearn.core
import scala.util.Random 

class Controller() {
  import Controller._ 

  private var sentences: List[String] = List()
  private var title: String = ""

  // If has been initialized with , skip
  def init(bookname: String): Unit = {
    if (title != bookname) {
      val p: parser = new parser(config.fileName(bookname))
      this.title = bookname
      this.sentences = p.run()
    }
  }

  def getTitle(): String = this.title

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
  def getSentence(): String = {
      this.randomElement(this.sentences) match {
        case None => "无可预览内容!"
        case Some(x) => x 
      }
    }
}

object Controller {
  def apply(filename: String): Controller = {
    val c: Controller = new Controller()
    c.init(filename)
    c
  }
}