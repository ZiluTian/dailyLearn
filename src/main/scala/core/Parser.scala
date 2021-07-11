package dailyLearn.core

import scala.io.Source

// Parse per-line 
class parser(filename: String) {

  // Possible to return chapter title rather than meaningful text. Rely on users to press next instead
  def run(): List[String] = {
    var sentences: List[String] = List()
    var bufferSentence: String = ""

    // hardcoded to Chinese symbol 
    val terminationSym: List[Char] = List('。', '”', '？', '!','」')
    
    val continueSym: List[Char] = List('，', '；')

    for (line <- Source.fromResource(s"texts/${filename}.txt").getLines()){
        if (!line.isEmpty) {
          val tmp = line.split("\\s+").mkString

          if (terminationSym.contains(tmp.last)) {
            sentences = (bufferSentence + tmp) :: sentences
            bufferSentence = ""
          } else if (continueSym.contains(tmp.last)) {
            bufferSentence = bufferSentence + tmp
          }
        }
    }
    sentences
  }
}

