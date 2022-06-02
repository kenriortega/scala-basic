package fs.commands

import fs.files.DirEntry
import fs.filesystem.State

class Ls extends Command {

  def createNiceOutPut(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      entry.name + "[" + entry.getType + "]"+ "\n" + createNiceOutPut(contents.tail)
    }
  }

  override def apply(state: State): State = {

    val contents = state.wd.contents
    val niceOutput = createNiceOutPut(contents)
    state.setMessage(niceOutput)
  }
}
