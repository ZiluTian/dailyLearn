package dailyLearn.core

import java.io.File

object config {
  private val currentPath = new java.io.File(".").getCanonicalPath

  val filename = s"${currentPath}/src/main/scala/texts/论语.txt"
//   val filename = s"${currentPath}/src/main/scala/texts/菜根谭.txt"

  val chapterIdentifier: String = " 卷"
}