package dailyLearn.core

import scala.io.Source

// Parse per-line 
class parser(filename: String) {

  // Possible to return chapter title rather than meaningful text. Rely on users to press next instead
  def run(): List[String] = {
    var sentences: List[String] = List()
    for (line <- Source.fromFile(filename).getLines()){
        if (!line.isEmpty) {
          sentences = line.split("\\s+").mkString :: sentences
        }
    }
    sentences
  }
}

