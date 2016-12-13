import scala.io.Source

// Get a vector of lines from a file:
val lns = Source.fromFile("alignedSourcePoS.txt").getLines.toVector


// Create lists of words
val wds = lns.map(_.split(" "))
// Make pairs of words using sliding:
val pairs = wds.map(_.sliding(2).toVector)

// Turn pairs into single strings:
val pairStrings = pairs.map (v => v.map { ar => ar.mkString("_") } )


// Filter sentences to get only those with string you want ("verb_noun", eg)
val stringIWant = "verb_noun"
val goodSentences = pairStrings.filter(_.contains(stringIWant))


// We want to find any text before this containing an article
val article = "article" // probalby "article"

goodSentences.map { svect => val index = svect.indexOf(stringIWant);
  val preceding = svect.slice(0,index);
  val articles = preceding.filter(_.contains(article))
  if (articles.size == 0) {
  ""
  } else {
  val articleIndex = preceding.indexOf(articles(articles.size - 1) );
  svect.slice(articleIndex,index).mkString(" ")
  
  }
}
