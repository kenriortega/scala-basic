package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllPatterns extends App {

  // 1 constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "Number"
    case "Scala" => "THe scala"
    case true => "THe truth"
    case AllPatterns => "Singleton"
  }

  // 2 - match anything
  // 2.1 - wildcard

  val matchAnyThing = x match {
    case _ => println("ds") //any
  }

  // 2.2 variable
  val matchVariable = x match {
    case something => s"$something"
  }

  // 3- tuples
  val aTuple = (1, 2)
  val matchTuple = aTuple match {
    case (1, 2) => ""
    case (i, i1) => s"$i,$i1"
    case (i, 2) => s"$i"
  }

  val nestedTuple = (2, (1, 2))
  val matchNestedTuple = nestedTuple match {
    case (_, (1, v)) => ""
  }
  // PM can be nested

  // 4- case classes - constructor pattern
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => "Empty"
    case Cons(h, t) => s"$h,$t"
    case Cons(h, Cons(sh, st)) => s"$h,$sh,$st"
    case _ => "wildcard"
  }

  // 5 - list pattern
  val aStdList = List(1, 2, 3, 42)
  val aStdListMatch = aStdList match {
    case List(1, _, _, _) => //extractor - advanced
    case List(1, _*) => // list of arbitrary length - advance
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case List: List[Int] => // explicit type specifier
    case _ => //
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList@Cons(_, _) => // name binding => use the name later here
    case Cons(1, rest@Cons(2, _)) =>
  }

  // 8 - multi - patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => // compound pattern (multi-pattern)

  }

  // 9 - if guards
  val secondElement = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => //
  }

  // all
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "list of string"
    case listOfNumbers: List[Int] => "List of numbers"
    case _ => ""
  }
  // JVM trick questions


}
