package dailyLearn.core

import java.io.File

object config {
  val currentPath = new java.io.File(".").getCanonicalPath

  def fileName(book: String): String =
    s"${currentPath}/src/main/scala/texts/${book}.txt"
}