package dailyLearn.core

import scala.io.Source
import scala.collection.mutable.{Map => MutMap, ListBuffer}
import java.io.BufferedReader

// Parse the text of LunYu into per-line
class parser(filename: String, chapterIdentifier: String) {

  // chapter title, list of sentences 
  private val chapterSentences: MutMap[String, ListBuffer[String]] = MutMap()

  // val buffer: BufferedReader = Source.fromFile(filename).bufferedReader()

  // Remove the preface intro
  private var chapterName: String = ""

  // Add a chapter to the map 
  def addChapter(chapterName: String): Unit = {
    chapterSentences += (chapterName -> ListBuffer[String]())
  }

  // Append a sentence to the corresponding chapter 
  def addSentence(sentence: String): Unit = {
    chapterSentences(chapterName).append(sentence)
  }

  // var sentenceCounter: Int = 1 
  def run(): Map[String, List[String]] = {
    for (line <- Source.fromFile(filename).getLines()){
        if (line.startsWith(chapterIdentifier)) {
          chapterName = line.split("\\s+").mkString
          addChapter(chapterName)
        } else if (!chapterName.isEmpty && !line.isEmpty) {
          // println(s"${line.split("\\s+").mkString}")
          // sentenceCounter += 1
          addSentence(line.split("\\s+").mkString)
        }
    }
    chapterSentences.mapValues(x => x.toList).toMap[String, List[String]]
  }
}

