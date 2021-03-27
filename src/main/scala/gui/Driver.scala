package dailyLearn.gui

import scala.swing._

import scala.swing.event._
import scala.swing.Orientation._
import scala.swing.{Color, Font}
import java.awt.Color._
import javax.swing.JFrame
import dailyLearn.core.{backend, config}

object driver extends SwingApplication {

  override def startup(args: Array[String]): Unit = {
    val window = new MainFrame {
      title = config.book 

      contents = new BoxPanel(Orientation.Horizontal) {  

        val button = new Button("下一句")
        button.background = LIGHT_GRAY

        val sentenceArea = new TextArea(5, 20) {
          text = backend.randomSentence()
        }

        sentenceArea.lineWrap = true
        sentenceArea.font = new Font("Arial", 0, 20)
        sentenceArea.editable = false
        // sentenceArea.background = BLACK
        // sentenceArea.foreground = WHITE

        val scrollPane = new ScrollPane(sentenceArea)
        // scrollPane.
        // scrollPane.verticalScrollBar = new ScrollBar()

        // Place button to the right of the sentence area 
        contents += scrollPane
        contents += button

        listenTo(button)
        reactions += {
          case ButtonClicked(_) =>
            sentenceArea.text = backend.randomSentence()
        }
      }
    }
    
    window.centerOnScreen()
    window.open()
  }
}