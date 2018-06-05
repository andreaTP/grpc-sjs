import utest._

import io.grpc._

import scala.scalajs.js

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

object GrpcTest extends TestSuite {

  val tests = Tests {

    'runAWorkingServer - {

      val result = Promise[Boolean]

      val hello = Grpc.load("./src/test/resources/hello.proto")

      val server = new Grpc.Server()

      val answerFn = js.Dynamic.literal(
        "sayHello" -> { (call: js.Dynamic, callback: js.Dynamic) =>
          {
            callback(
              null,
              js.Dynamic.literal("message" -> s"hello ${call.request.name}")
            )
          }
        }
      )

      server.addService(hello.com.example.protos.Greeter.service, answerFn)
      server.bind("0.0.0.0:50151", Grpc.ServerCredentials.createInsecure())
      server.start()

      val client = js.Dynamic.newInstance(
        hello.com.example.protos.Greeter
      )("localhost:50151", Grpc.Credentials.createInsecure())

      client
        .asInstanceOf[Grpc.Client]
        .waitForReady(
          // looks like passing a number here have a bug upstream
          new js.Date().getTime() + 2000,
          (err: js.Dynamic) => {
            if (!js.isUndefined(err))
              result.failure(new Exception(err.toString))

            client.sayHello(
              js.Dynamic.literal("name" -> "world"),
              (err: js.Dynamic, resp: js.Dynamic) => {
                if (err == null && !js.isUndefined(resp)) {

                  if (resp.message.toString == "hello world")
                    result.success(true)
                  else
                    result.failure(new Exception("FALSE"))
                } else {
                  result.failure(new Exception(err.toString))
                }
              }
            )
          }
        )

      result.future.onComplete { _ =>
        server.forceShutdown()
      }

      result.future
    }

  }

}
