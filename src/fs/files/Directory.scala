package fs.files

import fs.filesystem.FilesystemException

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {
  def removeEntry(entryName: String): Directory = {
    if (!hashEntry(entryName)) this
    else new Directory(parentPath, name, contents.filter(x => !x.name.equals(entryName)))
  }

  override def getType: String = "Directory"

  def replaceEntry(entryName: String, newEntry: Directory): Directory = {
    new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)) :+ newEntry)
  }

  def findEntry(entryName: String): DirEntry = {
    @tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
      if (contentList.isEmpty) null
      else if (contentList.head.name equals name) contentList.head
      else findEntryHelper(name, contentList.tail)
    }

    findEntryHelper(entryName, contents)
  }

  def addEntry(newEntry: DirEntry): Directory = {
    new Directory(parentPath, name, contents :+ newEntry)
  }

  def hashEntry(name: String): Boolean = {
    findEntry(name) != null
  }

  def getAllFoldersInPath: List[String] = {
    path.substring(1).split(Directory.SEPARATOR).toList.filter(x => !x.isEmpty)
  }

  def findDescendant(path: List[String]): Directory = {
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)
  }

  def findDescendant(relativePath: String): Directory = {
    if (relativePath.isEmpty) this
    else findDescendant(relativePath.split(Directory.SEPARATOR).toList)
  }

  def asDirectory: Directory = this

  def asFile: File =
    throw new FilesystemException("A directory cannot be converted to a file")

  def isRoot: Boolean = parentPath.isEmpty

  override def isDirectory: Boolean = true

  override def isFile: Boolean = false
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath = parentPath, name = name, contents = List())
}