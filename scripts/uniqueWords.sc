/*
Given a .tsv file in two columns like the
one that textFromOCRScript outputs, generate a
list of unique words.
*/
import scala.io.Source
import scala.xml._

@main
  def uniqueWords(f: String){
    val xmlText = scala.io.Source.fromFile(f).getLines.mkString

    val wds = xmlText.split(" ")

    val filteredWords = wds.filterNot(_.matches("[A-Za-z0-9]+"))
    val uniqueWords = filteredWords.distinct
    val sortedWords = uniqueWords.toVector.sorted

    for (p <- sortedWords) {
      println(p)
    }
  }
