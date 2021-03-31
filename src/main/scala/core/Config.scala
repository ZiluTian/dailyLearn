package dailyLearn.core

import java.io.File

object config {
  val currentPath = new java.io.File(".").getCanonicalPath

  def fileName(book: String): String =
    s"${currentPath}/src/main/scala/texts/${book}.txt"

  // return a list of availble book titles   
  def availableBookTitles(): List[String] = {
    val d = new File(s"${currentPath}/src/main/scala/texts")
    assert(d.exists())
    d.listFiles().filter(_.isFile).toList.map(x => x.getName().split("\\.").head)
  }
}