package dailyLearn.core

import java.io.File
import java.util.jar.JarFile

import scala.io.Source

import scala.collection.JavaConverters.enumerationAsScalaIteratorConverter

object config {
  // return a list of availble book titles   
  def availableBookTitles(): List[String] = {
    List("道德经", "论语", "菜根谭", "增广贤文")
  }
}