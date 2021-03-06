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

* 
* support website: http://software.avt.dn.ua
*
* support email address: support@software.avt.dn.ua
*  
*/

package org.fbreader.views;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import org.fbreader.Main;
import org.fbreader.utils.ImageLoader;
import org.fbreader.utils.mylib.MyLibUtils;
import org.fbreader.utils.mylib.MyLibSort;
import org.fbreader.views.list.CustomList;
import org.fbreader.views.list.FancyCustomList;
import org.fbreader.localization.L10n;
import org.fbreader.models.BookInMyLibRMS;



public class MyLibRecentBooksViewList 
             extends FancyCustomList 
             implements View, CommandListener {
    
   
    private Image imageListIcon;
    public static final Command selectMyLibRecentBooksListCmd = 
            new Command("Select", Command.OK, 1);
    private Vector myLibRMSByBookLastOpenSortDataVector=null;
    private final String RECENT_BOOKS_ICON = 
             "/icons/book_placeholder_38x38.png";
    private ViewMaster viewMaster;
    private MyLibUtils myLibUtils;
    private static BookInMyLibRMS selectedBook;
    private MyLibSort myLibSort;
    boolean booksLastOpenFound;
    private final Alert alertRecentBooksNotFound = new Alert(L10n.getMessage("RECENT_BOOKS_NOT_FOUND_ALERT_TITLE"), 
            L10n.getMessage("RECENT_BOOKS_NOT_FOUND_ALERT_CONTENT"), null, null); 
    public MyLibRecentBooksViewList(MIDlet parent) {
       super(L10n.getMessage("MY_LIB_RECENT_BOOKS_VIEW_LIST_TITLE"));
       viewMaster = ViewMaster.getInstance();
       myLibSort = MyLibSort.getInstance();  
       myLibUtils = MyLibUtils.getInstance();
          
       this.setTheme(CustomList.createTheme(Display.getDisplay(parent)));

       try  {
              imageListIcon=ImageLoader.getInstance().loadImage(RECENT_BOOKS_ICON, null);
            } 
       catch(IOException e) {
       } 
        
        this.setFitPolicy(List.TEXT_WRAP_DEFAULT);
        
        this.addCommand(viewMaster.getHelpCmd());
        this.addCommand(viewMaster.getBackCmd());

        this.setSelectCommand(selectMyLibRecentBooksListCmd);
        
        this.setCommandListener(this);

    }

    
    /**
     * Activate MyLibRecentBooksViewList 
     */
    public void activate() {
        //Hide CategoryBar
        viewMaster.setCategoryBarVisible(false);
        
        //Sort MyLib RMS
        myLibSort.sortMyLibRMSByBookLastOpen();
        
        //Get sorted Vector
        myLibRMSByBookLastOpenSortDataVector=myLibSort.getMyLibRMSByBookLastOpenSortDataVector();        
        
        //We assume that no book could not be opened for reading
        booksLastOpenFound=false;
        
        //View MyLibRecentBooksList
        if (myLibRMSByBookLastOpenSortDataVector !=null) {
            for (int i = 0; i < myLibRMSByBookLastOpenSortDataVector.size(); i++) {
              BookInMyLibRMS currentBook = (BookInMyLibRMS) myLibRMSByBookLastOpenSortDataVector.elementAt(i);
              if (currentBook.getBookLastOpen()!=0) {
                booksLastOpenFound=true;  
                this.append(currentBook.getBookTitle().toLowerCase(),currentBook.getBookAuthor().toLowerCase(),"",imageListIcon,
                               FancyElement.IMPORTANCE_NONE);
                  
              }

            }
            if (!booksLastOpenFound){ 
                    showRecentBooksNotFoundAlert();
            }
        }
        else {
            showRecentBooksNotFoundAlert();
        }
   
    }
    

    /**
     * Empties the List items
     */
    public void deactivate() {
        //delete MyLibTagsList
        for (int i=this.size()-1; i >= 0; i--){
            this.delete(i);
        }
    }    
    
    
    public void commandAction(Command command, Displayable displayable) {
        
        if (displayable==alertRecentBooksNotFound && command == viewMaster.getDismissCmd()) {
            //show CategoryBar
            viewMaster.setCategoryBarVisible(true);
            viewMaster.backView();            
        }          
        
        if (command == viewMaster.getBackCmd()) {
            //show CategoryBar
            viewMaster.setCategoryBarVisible(true);
            viewMaster.backView();
        }
        else if (command == selectMyLibRecentBooksListCmd) {
            
            selectedBook = (BookInMyLibRMS) myLibRMSByBookLastOpenSortDataVector.elementAt(this.getSelectedIndex());  
            myLibUtils.setSelectedBookForRead(selectedBook);
            viewMaster.getMyLibBookInfoViewForm();
            viewMaster.openView(ViewMaster.VIEW_MY_LIB_BOOK_INFO_FORM);
        }
        else if (command == viewMaster.getHelpCmd()){
            viewMaster.getMyLibRecentBooksViewListHelpForm();
            viewMaster.openView(ViewMaster.VIEW_MY_LIB_RECENT_BOOKS_LIST_HELP_FORM);            
        }         
        
    }
    
    public void showRecentBooksNotFoundAlert() {
        alertRecentBooksNotFound.setTimeout(Alert.FOREVER); 
        alertRecentBooksNotFound.addCommand(viewMaster.getDismissCmd());
        alertRecentBooksNotFound.setCommandListener(this);         
        Main.getInstance().switchDisplayable(alertRecentBooksNotFound,this);
    }       
    
    
}
