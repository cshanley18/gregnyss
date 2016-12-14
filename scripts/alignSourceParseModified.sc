
import scala.io.Source

val parses = "posInfo16.tsv"
val sourceText = "gregNyss16.tsv"


// PART 1:  Prepare a vector of Parse objects
// where we can easily look up a form and
// find its ID or part of speech, using objects
// belonging to a case class:
 case class Parse(surface: String, pos: String, lemma: String)

// Assuming you're in the right directory...
val parseLines = Source.fromFile(parses).getLines.toVector

//println(10  ln("Read " + parseLines.size + " lines from file")
// create a Vector of Parse objects:
val parseVector = parseLines.map { case s => val cols = s.split("\t")
   Parse(cols(0), cols(1), cols(2))
}
//println ("Created vector with " + parseVector.size + " parses.")
// PART 2: substitute IDs or PoS for surface form.
val surfaceTsv = Source.fromFile(sourceText).getLines.toVector
val twocols = surfaceTsv.map(_.split("\t")).filter(_.size == 2)
val idlist = twocols.map(_(0))
val surfSentences = twocols.map(_(1))

val surfWords = surfSentences.map (_.split(" ")).filterNot(_.isEmpty)


def swapInParse(s: String, parses: Vector[Parse])  = {
  val parseV = parses.filter(_.surface == s)
  if (parseV.size == 0) {
    // for id/lemmas, we should keep the surface form
    // for applications like topic modelling.
    "unrecognized"
  } else {
    // What should we do if we find more than one parse???
    parseV(0).pos
  }
}

val posWords = surfWords.map ( _.map { s => swapInParse(s,parseVector) } )

val alignedPos = idlist.zip(posWords.map(_.mkString(" ")))
//println(posWords.map(_.mkString(" ")))

for (pos <- alignedPos) {
  println(pos._1 + "\t" + pos._2)
}
