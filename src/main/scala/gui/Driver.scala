package dailyLearn.gui

import scala.swing._

import scala.swing.event._
import scala.swing.Orientation._
import scala.swing.Color
import java.awt.Color._

import dailyLearn.core.backend 

object driver extends SwingApplication {

  override def startup(args: Array[String]): Unit = {
    val window = new MainFrame {
      title = "Daily Learn"
      contents = new BoxPanel(Orientation.Horizontal) {
        
        val button = new Button("next")
        // button.foreground = BLACK

        val sentenceArea = new TextArea(5, 20) {
          text = backend.randomSentence()
        }

        sentenceArea.lineWrap = true

        // sentenceArea.background = BLACK
        // sentenceArea.foreground = WHITE

        // Place button to the right of the sentence area 
        contents += sentenceArea
        contents += button

        listenTo(button)
        reactions += {
          case ButtonClicked(_) =>
            sentenceArea.text = backend.randomSentence()
        }
      }
    }
    window.open()
  }
}