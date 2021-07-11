package dailyLearn.gui

import scala.swing._

import scala.swing.event._
import scala.swing.Orientation._
import scala.swing.{Color, Font}
import java.awt.Color._
import javax.swing.JFrame
import dailyLearn.core.{Controller, config}
import dailyLearn.addons._

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

import java.awt.event.ActionEvent
import java.awt.event.ActionListener

object view extends SwingApplication {

  override def startup(args: Array[String]): Unit = {
    val window = new MainFrame {
      title = "乐读经典"

      reactions += {
        case WindowClosing(e) => showCloseDialog()
      }

      private def showCloseDialog() {
        Dialog.showConfirmation(parent = null,
          title = "Exit",
          message = "确认要退出么?"
        ) match {
          case Dialog.Result.Ok => JFrame.EXIT_ON_CLOSE
          case _ => ()
        }
      }


      class greyButton(val text0: String) extends Button(text0) {
        background = LIGHT_GRAY
      }

      var controller: Controller = Controller("菜根谭")

      case object bAutoplay extends greyButton("连续")
      case object bPause extends greyButton("暂停")
      case object bNext extends greyButton("下一句")
      case object bSaveAsImage extends greyButton("保存图片")

      // buttons that adjust the font size
      class fontAdjust(val text0: String) extends Button(text0){
        // preferredSize = new Dimension(5, 5)
      }
      case object incButton extends fontAdjust("放大") 
      case object decButton extends fontAdjust("缩小")

      val bookSelection = new ComboBox(config.availableBookTitles())

      case object confirmationLabel extends Label {
        name = "确认保存"
        text = "图片已保存!"
        visible = false
        
        def show(): Unit = { visible = true }
        def clear(): Unit = { visible = false }
      }

      case object sentenceArea extends TextArea(5, 40) {
        text = "天道酬勤"
        lineWrap = true
        font = new Font("Arial", 0, 20)
        editable = false

        def reload(): Unit = {
          controller.init(bookSelection.selection.item)
          text = controller.getSentence()
        }
      }

      val timer = new Timer(1000, true, bNext.doClick())

      contents = new BoxPanel(Orientation.Vertical){
        val metaConfig = new BoxPanel(Orientation.Horizontal) {
          contents += bookSelection
          contents += incButton
          contents += decButton
        }
        
        contents += metaConfig
        val boxpanel = new BoxPanel(Orientation.Horizontal) {

        // the panel of buttons on the right 
        val buttonPanels = new GridPanel(3, 1)

        buttonPanels.contents ++= Seq(bNext, bSaveAsImage, confirmationLabel)

        confirmationLabel.listenTo(bSaveAsImage)
        confirmationLabel.reactions += {
          case ButtonClicked(_) =>
            val dtf = DateTimeFormatter.ofPattern("MMdd_HHmm")
            val now = LocalDateTime.now()
            
            val imgName: String = Dialog.showInput[String](message = "请输入图片名", title = "保存图片为", initial = s"${controller.getTitle}_${dtf.format(now)}").get
            val savedPath: String = new saveAsImage(sentenceArea.text, imgName).run()
            
            confirmationLabel.show()
        }

        val scrollPane = new ScrollPane(sentenceArea)
        // scrollPane.verticalScrollBar = new ScrollBar()

        sentenceArea.listenTo(bNext, bAutoplay, bPause, incButton, decButton)
        sentenceArea.reactions += {
          case ButtonClicked(b) =>
            b match {
              case `bNext` => 
                confirmationLabel.clear()
                sentenceArea.reload()
              case `bAutoplay` => 
                bAutoplay.foreground = RED
                timer.start()
              case `bPause` =>
                bAutoplay.foreground = BLACK
                timer.stop()
              case `incButton` =>
                val currentFont = sentenceArea.font
                sentenceArea.peer.setFont( currentFont.deriveFont(currentFont.getSize()+1.0f))
              case `decButton` =>
                val currentFont = sentenceArea.font
                sentenceArea.peer.setFont( currentFont.deriveFont(currentFont.getSize()-1.0f))
            }
        }

        val lButtonPanels = new GridPanel(3, 2)

        lButtonPanels.contents ++= Seq(bAutoplay, bPause)

        // Place button to the right of the sentence area 
        contents ++= Seq(lButtonPanels, scrollPane, buttonPanels)
      }
      contents += boxpanel
      }
    }
    
    
    window.centerOnScreen()
    window.open()
  }
}

class Timer(interval: Int, repeats: Boolean, op: => Unit) {

  private val timeOut = new javax.swing.AbstractAction() {
      def actionPerformed(e : java.awt.event.ActionEvent) = op
    }
  private val t = new javax.swing.Timer(interval, timeOut)

  def start(): Unit = {
    t.setRepeats(repeats)
    t.start()
  }

  def stop(): Unit = {
    t.stop()
  }
}
