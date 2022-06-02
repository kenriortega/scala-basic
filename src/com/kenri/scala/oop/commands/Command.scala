package com.kenri.scala.oop.commands

import com.kenri.scala.oop.filesystem.State

trait Command {

  def apply(state: State): State
}

object Command {
  def from(input: String): Command =
    new UnknownCommand
}
