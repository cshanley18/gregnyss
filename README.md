# gregnyss



## Steps


### 1. Extract text contents from OCR structure

Take raw OCR files in HOCR format, and extract text contents.
The resulting file should be in two columns, with new documents marked by lines beginning "TEXT".  Example:

    amm scripts/textFromOCRScript.scala ocr/*.html >   gregNyssSourceText2.tsv



### 2. Compute unique words

Read from 2-column file in previous step:

    amm scripts/uniqueWords.sc data/gregNyss.tsv > data/uniqueWordList.txt



### 3. Collect morphology results

Work on unique word list, and create a two-column output with word + list of analyses.

    amm scripts/getMorphology.sc data/uniqueWordList.text > morphologyResults.tsv



### 4. Extract part of speech info

    amm scripts/extractMorpheusPoS.sc morphologyResults.tsv > data/posInfo.tsv
