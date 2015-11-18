package org.jetbrains.plugins.scala.conversion.ast

import org.jetbrains.plugins.scala.conversion.PrettyPrinter

/**
  * Created by Kate Ustyuzhanina
  * on 10/27/15
  */
case class AnnotaionConstruction(inAnnotation: Boolean, attributes: Seq[(Option[String], Option[IntermediateNode])],
                                 name: Option[String]) extends IntermediateNode {
  override def print(printer: PrettyPrinter): PrettyPrinter = {
    if (inAnnotation) {
      printer.append("new ")
    } else {
      printer.append("@")
    }

    if (name.isDefined) {
      name.get match {
        case "Deprecated" => printer.append("deprecated")
        case otherName => printer.append(otherName)
      }
    }

    if (attributes.nonEmpty) {
      printer.append("(")

      for ((name, value) <- attributes) {
        if (name.isDefined) {
          printer.append(name.get)
          printer.append(" = ")
        }

        if (value.isDefined) {
          value.get.print(printer)
          printer.append(", ")
        }
      }

      printer.delete(2)
      printer.append(")")
    }
    printer.space()
  }
}


