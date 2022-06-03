package fs.commands
import fs.files.{DirEntry, Directory}
import fs.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {
  override def doCreateSpecificEntry(state: State): DirEntry =
    Directory.empty(state.wd.path,name)
}
