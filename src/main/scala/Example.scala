import doodle.core.*
import doodle.image.*
import doodle.image.syntax.all.*
import doodle.image.syntax.core.*
import doodle.java2d.*
import doodle.reactor.*
import scala.concurrent.duration.*
import cats.effect.unsafe.implicits.global

// To use this example:
//
// 1. run `sbt`
// 2. run the `run` command within `sbt`
object Example {
  val image: Image =
    Image
      .circle(100)
      .fillColor(Color.dodgerBlue)
      .on(Image.circle(200).fillColor(Color.hotpink))
      .on(Image.circle(300).fillColor(Color.paleGoldenrod))

  val animation: Reactor[Angle] =
    Reactor
      .init(0.degrees)
      .withOnTick(a => a + 0.1.degrees)
      //.withStop(a => a > 360.degrees)
      .withTickRate(2.millis)
      .withRender { a =>
        val location = Point(200, a)
        val planet = Image.circle(40.0).noStroke.fillColor(Color.fuchsia)
        val moon = Image
          .circle(10.0)
          .noStroke
          .fillColor(Color.lightPink)
          .at(Point(60, a * 5))

        moon.on(planet).at(location)
      }

  val frame: Frame = Frame.default.withSize(600, 600).withCenterAtOrigin

  @main def go(): Unit = {
    //image.draw()

    // Comment out the above and uncomment the below to display the animation
    animation.run(frame)
  }
}
