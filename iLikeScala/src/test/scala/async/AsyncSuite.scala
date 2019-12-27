package async

import org.junit.Assert._
import org.junit.Test

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Random, Success, Try}

class AsyncSuite {

  import Async._

  @Test def `futurize should handle successful computation results (3pts)`(): Unit = {
    val success = Success(Random.nextInt())
    val succeeding = new CallbackBasedApi {
      def computeIntAsync(continuation: Try[Int] => Unit): Unit = continuation(success)
    }
    val eventuallyInt = futurize(succeeding).computeIntAsync()
    Await.ready(eventuallyInt, 200.milliseconds)
    assertEquals(success, eventuallyInt.value.get)
  }

  @Test def `futurize should handle failed computation results (3pts)`(): Unit = {
    val failure = Failure(new Exception("Oops"))
    val failing = new CallbackBasedApi {
      def computeIntAsync(continuation: Try[Int] => Unit): Unit = continuation(failure)
    }
    val eventuallyInt = futurize(failing).computeIntAsync()
    Await.ready(eventuallyInt, 200.milliseconds)
    assertEquals(failure, eventuallyInt.value.get)
  }

}
