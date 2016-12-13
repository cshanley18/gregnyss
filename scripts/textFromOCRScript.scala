
/*
Scala script to extract text elements from OCR, throw away non-Greek strings, and dehyphenate
remaining Greek text.

One required parameter: a String identifying one file name.

Output is a two-column representaiton of each text.

Texts are separated by lines beginning TEXT.
The 2 columns are a numeric identifier and a text chunk.

*/

import scala.io.Source
import scala.xml._


def textFromOcr(f: String) = {
  println("TEXT: " + f)
  val xmlText = scala.io.Source.fromFile(f).getLines.mkString
  try {
    val root = XML.loadString(xmlText)
    // This is the path to word spans in the OCR output:
    val wordSpans = root\"body"\"div"\"span"\"span"
    val textContents = wordSpans.map(_.text).mkString(" ")
    val wds = textContents.split("[ ]")
    val filteredWords = wds.filterNot(_.matches("[,\\.:A-Za-z0-9�?]+\\-?")).filterNot(_.isEmpty)

    // dehyphenate:
    val filteredText = filteredWords.mkString(" ").replaceAll("- ", "")

    val sentences = filteredText.split("\\.")

    val numberedSentences = sentences.zipWithIndex
    for (s <- numberedSentences) {
      println(s._2 + "\t" + s._1 + ".\n")
    }
  } catch  {
    case bad: org.xml.sax.SAXParseException => println("ERROR: Could not parse XML in file " + f + ".  Check for the BOM.")
    case _ : Throwable => println("ERROR: Some other problem happened.")

  }
}

@main
  def ocr(f: String) = {
textFromOcr(f)
  }

//crReader.main(args)
