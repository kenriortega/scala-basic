package com.kenri.scala.oop.filesystem

import com.kenri.scala.oop.commands.Command
import com.kenri.scala.oop.files.Directory

import java.util.Scanner

object Filesystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)
  while (true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }

}
