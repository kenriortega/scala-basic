package fs.commands

import fs.files.{DirEntry, File}
import fs.filesystem.State

class Touch(name: String) extends CreateEntry(name) {
  override def doCreateSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
