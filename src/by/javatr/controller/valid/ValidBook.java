package by.javatr.controller.valid;

import by.javatr.bean.Book;

public class ValidBook {
    private ValidBook(){}
    private static final ValidBook instance = new ValidBook();
    public static ValidBook getInstance(){
        return instance;
    }

    public boolean isValidBook(Book book) {
        if(book.getName()==null ||book.getName().equals(" ")|| book.getName().equals("")){
            return false;
        }
        if(book.getRating()<0 ||book.getRating()>10){
           return false;
        }
        return true;
    }
}
