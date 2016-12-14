# gregnyss



## Steps


### 1. Extract text contents from OCR structure

Take raw OCR files in HOCR format, and extract text contents.
The resulting file should be in two columns, with new documents marked by lines beginning "TEXT".  Example:

    amm scripts/textFromOCRScript.sc ocr/*.html >   gregNyss.tsv



### 2. Compute unique words

Read from 2-column file in previous step:

    amm scripts/uniqueWords.sc data/gregNyss.tsv > data/uniqueWordList.txt



### 3. Collect morphology results

Work on unique word list, and create a two-column output with word + list of analyses.

    amm scripts/getMorphology.sc data/uniqueWordList.text > morphologyResults.tsv



### 4. Extract part of speech info

    amm scripts/extractMorpheusPoS.sc morphologyResults.tsv > data/posInfo16.tsv

### 5. Align Parts of Speech with Source text

    alignSourceParseModified.sc > alignSourcePos16.tsv

### 6. Produce sliding pairs for aligned text

    amm scripts/slidingScript.sc data/alignSourcePos16.tsv > posPairs16.tsv
