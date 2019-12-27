package async

import scala.concurrent.{Future, Promise}
import scala.util.Try

object Async {

  /**
    * Turns a callback-based API into a Future-based API
    * @return A `FutureBasedApi` that forwards calls to `computeIntAsync` to the `callbackBasedApi`
    *         and returns its result in a `Future` value
    *
    * Hint: Use a `Promise`
    */
  def futurize(callbackBasedApi: CallbackBasedApi): FutureBasedApi = {

    val promise = Promise[Int]
    callbackBasedApi.computeIntAsync(result => promise.complete(result))

    new FutureBasedApi {
      override def computeIntAsync(): Future[Int] = promise.future
    }
  }

}

/**
  * Dummy example of a callback-based API
  */
trait CallbackBasedApi {
  def computeIntAsync(continuation: Try[Int] => Unit): Unit
}

/**
  * API similar to [[CallbackBasedApi]], but based on `Future` instead
  */
trait FutureBasedApi {
  def computeIntAsync(): Future[Int]
}
