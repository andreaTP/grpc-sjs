package io.grpc

import scala.scalajs.js
import scala.scalajs.js.annotation._

import js.|

@js.native
@JSImport("grpc", JSImport.Namespace)
object Grpc extends js.Object {

  @js.native
  @JSName("credentials")
  object Credentials extends js.Object {

    @js.native
    sealed trait ChannelCredentials extends js.Any {}

    def createInsecure(): ChannelCredentials = js.native

    def createSsl(
        root_certs: String,
        private_key: String,
        cert_chain: String
    ): ChannelCredentials = js.native
  }

  type Deadline = Double | js.Date

  @js.native
  class Client(
      address: String,
      credentials: Credentials.ChannelCredentials,
      options: js.Dynamic*
  ) extends js.Object {

    @js.native
    def close(): Unit = js.native

    @js.native
    def getChannel(): js.Dynamic = js.native

    @js.native
    def waitForReady(deadline: Deadline, callback: js.Function): Unit =
      js.native
  }

  @js.native
  class ServerCredentials extends js.Object {}

  @js.native
  object ServerCredentials extends js.Object {
    type KeyCertPair = {
      val privateKey: String
      val certChain: String
    }

    @js.native
    def createInsecure(): ServerCredentials = js.native

    @js.native
    def createSsl(
        rootCerts: String,
        keyCertsPairs: js.Array[KeyCertPair],
        checkClientCertificate: Boolean
    ): ServerCredentials = js.native

  }

  @js.native
  class Metadata() extends js.Object {

    @js.native
    def add(key: String, value: String): Unit = js.native

    @js.native
    def get(key: String): String = js.native

    @js.native
    def remove(key: String): String = js.native

    @js.native
    def set(key: String, value: String): String = js.native

    @js.native
    override def clone(): Metadata = js.native
  }

  type ServiceError = {
    val code: Int
    val metadata: Metadata
  }

  @js.native
  class Server(
      options: js.Dynamic = js.Dynamic.literal()
  ) extends js.Object {

    @js.native
    def addService(service: js.Dynamic, implementation: js.Dynamic): Unit =
      js.native

    @js.native
    def bind(port: String, credentials: ServerCredentials): Unit = js.native

    @js.native
    def forceShutdown(): Unit = js.native

    @js.native
    def start(): Unit = js.native

    @js.native
    def tryShutdown(callback: js.Function): Unit = js.native

    @js.native
    def register(
        name: String,
        handler: js.Dynamic,
        serialize: Function[js.Any, String],
        deserialize: Function[String, js.Any],
        `type`: String
    ): Boolean = js.native
  }

  @js.native
  def closeClient(client_obj: Client): Unit = js.native

  @js.native
  def load(
      filename: String,
      format: String = "proto",
      options: js.Dynamic = js.Dynamic.literal()
  ): js.Dynamic =
    js.native

}
