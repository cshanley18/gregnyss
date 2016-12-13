# gregnyss



## Steps


### 1. Extract text contents from OCR structure

Take raw OCR files in HOCR format, and extract text contents.
The resulting file should be in two columns, with new documents marked by lines beginning "TEXT".  Example:

    scala scripts/textFromOCRScript.scala ocr/*.html >   gregNyssSourceText2.tsv
