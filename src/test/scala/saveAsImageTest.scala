package dailyLearn.addons
package test 

import org.scalatest.flatspec.AnyFlatSpec

class saveAsImageTest extends AnyFlatSpec {
  "Export image" should "create a new image with the text in the center" in {
    val foo = new saveAsImage("天道酬勤", "test")
    foo.run()
  }
}

class getSourceTitlesTest extends AnyFlatSpec {
  "Config" should "return the list of book titles available" in {
    println(dailyLearn.core.config.availableBookTitles())
  }
}