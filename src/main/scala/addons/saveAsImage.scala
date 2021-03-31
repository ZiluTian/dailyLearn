package dailyLearn.addons 

// import scala.swing.Image

import java.awt.image.{ImageObserver, BufferedImage};
import java.awt._
import java.io.{File, InputStream};
import javax.imageio.ImageIO;
import java.io.IOException;

import dailyLearn.core.config 
import scala.swing.TextArea

/**
  * This class corresponds to the action "save the quote as an image"
  *
  * @param text: the content of the image
  * @param width: the size of the output image 
  * @param height: the size of the output image 
  */
class saveAsImage(text: String, 
                  imgName: String) {

  val genImgPath: String = s"${config.currentPath}/src/main/scala/img"
  
  val screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  val width: Int = screenSize.getWidth().toInt
  val height: Int = screenSize.getHeight().toInt

  // Due to the copyright requirement, only use the common ones 
  // val font_file: InputStream = canvas.getClass.getResourceAsStream(s"${genImgPath}/*.ttf")
  // val fontBase: Font = Font.createFont(Font.TRUETYPE_FONT, font_file)
  // val font = fontBase.deriveFont(Font.PLAIN, 24)

  def run(): String = {
    // Create an image to write on directly
    val canvas: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    val g: Graphics2D = canvas.createGraphics()
    g.setColor(Color.WHITE)
    g.fillRect(0, 0, canvas.getWidth, canvas.getHeight)
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON)

    g.setFont(new Font("宋体", Font.PLAIN, 35))
    val fm = g.getFontMetrics()
  
    // center text 
    val r = fm.getStringBounds(text, g)
    val x: Int = (width - r.getWidth().toInt) / 2
    val y: Int = (height - r.getHeight().toInt) / 2 + fm.getAscent()

    g.setColor(Color.BLACK)
    g.drawString(text, x, y)
    g.dispose()
    
    val genImageAbsPath: String = s"${genImgPath}/saved"
    ImageIO.write(canvas, "png", new File(s"${genImageAbsPath}/${imgName}.png"))

    genImageAbsPath
  }
}
