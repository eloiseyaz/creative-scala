import cats.effect.unsafe.implicits.global
import doodle.core.*
import doodle.image.*
import doodle.syntax.all.*
import doodle.image.syntax.all.*
import doodle.java2d.*

object FractalFun {
  def chessboard(count: Int): Image = {
    val pinkSquare: Image = Image.rectangle(30, 30).fillColor(Color.lightPink)
    val blueSquare: Image = Image.rectangle(30, 30).fillColor(Color.lightBlue)
    val base: Image = pinkSquare.beside(blueSquare).above(blueSquare.beside(pinkSquare))

    count match {
      case 0 => base
      case n =>
        val smaller: Image = chessboard(n - 1)
        smaller.beside(smaller).above(smaller.beside(smaller))
    }
  }

  @main def drawChessboard(): Unit = {
    chessboard(2).draw()
  }
}
