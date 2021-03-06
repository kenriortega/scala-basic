package fs.filesystem

import fs.files.Directory
import fs.commands.Command

object Filesystem extends App {
  val root = Directory.ROOT
  io.Source.stdin.getLines()
    .foldLeft(State(root, root))((currentState, newLine) => {
      currentState.show
      Command.from(newLine).apply(currentState)
    })
}
