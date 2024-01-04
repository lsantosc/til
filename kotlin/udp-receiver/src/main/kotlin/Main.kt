import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

data class Settings(
  val host: String = "127.0.0.1",
  val port: Int = 1177
)

fun receivePacket() {
  var socket: DatagramSocket? =  null
  val settings = Settings()
  val buffer = ByteArray(2048)

  try {
    // Keep a socket open to listen to all the UDP traffic that is destined for this port
    socket = DatagramSocket(settings.port, InetAddress.getByName(settings.host))
    socket.broadcast = true
    val packet = DatagramPacket(buffer, buffer.size)
    socket.receive(packet)
    val converted = String(packet.data, packet.offset, packet.length)
    println("open fun receiveUDP packet received = $converted")

  } catch (e: Exception) {
    println("open fun receiveUDP catch exception. $e")
    e.printStackTrace()
  } finally {
    socket?.close()
  }
}

fun loop(action: () -> Unit) {
//  while(true)
//    action()
  var index = 0
  for (i in generateSequence(0) { it }) {
    action()
    ++index
    println("Packet $index\r\n")
  }
}
suspend fun main() {
  coroutineScope {
    loop {
      val packet = async { receivePacket() }
      runBlocking { packet.await() }
    }
  }
}
