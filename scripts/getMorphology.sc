/*
Given a list of words, get morpheus analysis
for each word.
*/
import scala.io.Source
import scala.xml._

@main
  def getMorphology(f: String){
    val wordList = Source.fromFile(f).getLines.toVector
    val uniqueWords = wordList.distinct
    val sortedWords = uniqueWords.toVector.sorted
    for (w <- sortedWords) {
      val parseReply = parse(w)
      println(w + "\t" + parseReply)
    }
  }


def  getMorphReply(request: String) : String = {
  var reply : String = ""
  try {
    reply = scala.io.Source.fromURL(request).mkString.replaceAll("\n"," ")
  } catch {
    case _ => reply = "Error from parsing service."
  }
  reply
}


/** Gets a reply from the perseus morphology service
* for a given word.
*/
def parse (s: String): String = {
  val baseUrl = "https://services.perseids.org/bsp/morphologyservice/analysis/word?lang=grc&engine=morpheusgrc&word="

// 52881
//53141
  val cutOff = 681 // code point in IPA character block

  val int1 =  s(0).toInt
  if (s.size < 1) {
    ""
  } else if (int1 < cutOff)  {
    ""
  } else {
    val request = baseUrl + s
    getMorphReply(request)
  }
}
