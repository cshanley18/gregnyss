# gregnyss



## Steps


### 1. Extract text contents from OCR structure

Take raw OCR files in HOCR format, and extract text contents.
The resulting file should be in two columns, with new documents marked by lines beginning "TEXT".  Example:

    amm scripts/textFromOCRScript.scala ocr/*.html >   gregNyssSourceText2.tsv



### 2. Compute unique words

Read from 2-column file in previous step:

    amm scripts/uniqueWords.sc data/gregNyss.tsv > data/uniqueWordList.txt
