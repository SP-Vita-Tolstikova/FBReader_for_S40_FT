package org.albite.book.model.book;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import org.albite.book.model.parser.TextParser;
import org.albite.util.archive.Archive;
import org.fbreader.Main;


/**
 *
 * @author albus
 */

/*
 Copyright 2013 Sole Proprietorship Vita Tolstikova
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

* Changes and additions to the author's code:
* --------------------------------------------
* 1.The module FB2Book.java base on FileBook.java.
*   But considering the specifics of processing FB2 format.
*   In particular, used following methods:
*   1.1 parseFB2ForChaptersPrepareSAX();
*   1.2 splitFB2ChapterIntoPieces();
* 
* 2. Small changes in the method close()
*   
* 
* 
* support website: http://software.avt.dn.ua
*
* support email address: support@software.avt.dn.ua
*  
*/

public class FB2Book extends Book {

    /*
     * Book file
     */
    private final FileConnection bookFile;
    private final Archive archive;
    private final boolean processHtmlEntities;

    public FB2Book(
            final String filename,
            final Archive archive,
            final TextParser parser,
            final boolean processHhtmlEntities)
            throws IOException, BookException {

        this.bookURL = filename;
        this.archive = archive;
        this.parser = parser;
        this.processHtmlEntities = processHhtmlEntities;

        bookFile = (FileConnection) Connector.open(filename, Connector.READ);
        language = null;
        
        try {
            
//--- Prepare FB2 Book for split chapters into Pieces
            parseFB2ForChaptersPrepareSAX(bookFile);

            /*
             * load chapters info (filename + title)
             */
            chapters = loadChaptersDescriptor();

           
            linkChapters();

        } catch (IOException ioe) {
        } catch (BookException be) {
            close();
            throw be;
        }

       
        
        
    }

    final Chapter[] loadChaptersDescriptor()
            throws BookException, IOException {
        
        Vector chaps = new Vector();
        
        splitFB2ChapterIntoPieces(
                bookFile,
                (int) bookFile.fileSize(),
                getArchive(),
                (processHtmlEntities
                ? MAXIMUM_HTML_FILESIZE
                : MAXIMUM_TXT_FILESIZE),
                0, processHtmlEntities, chaps);

        Chapter[] res = new Chapter[chaps.size()];
        chaps.copyInto(res);
        
        return res;
    }

    public final void close() throws IOException {
        bookFile.close();
    }

    public Archive getArchive() {
        return archive;
    }
    
  
}