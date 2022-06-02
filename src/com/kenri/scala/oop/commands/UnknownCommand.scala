package com.kenri.scala.oop.commands

import com.kenri.scala.oop.filesystem.State

class UnknownCommand extends Command {
  override def apply(state: State): State =
    state.setMessage("Command not found!")
}
