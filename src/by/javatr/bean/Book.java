package by.javatr.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book implements Serializable, Comparable<Book>, Cloneable{

    private  String name;
    private float rating;
    private boolean isBestSeller; //поставить final?
    private int idBook;

    public Book(String name, float rating, boolean isBestSeller) {
        this.name = name;
        this.rating = rating;
        this.isBestSeller = isBestSeller;
    }
    public int getIdBook() {
        return idBook;
    }
    public Book(){};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        if (!name.equals(book.getName())) return false;
        if (rating != book.getRating()) return false;
        if (isBestSeller != book.isBestSeller()) return false;
        if (idBook != book.getIdBook()) return false;
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }


    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Override
    public int hashCode() {
        return (int)(31* rating * idBook +((name==null)?0:name.hashCode()));
    }



    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public boolean isBestSeller() {
        return isBestSeller;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", isBestSeller=" + isBestSeller +
                ", id = "+ idBook+
                '}';
    }

    @Override
    public int compareTo(Book book) {
        int result = this.name.compareTo(book.getName());
        if(result==0){
            Float rating2 = (Float)this.rating;
            result=rating2.compareTo(book.getRating());
        }
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public static void main(String[] args) {
        Book book = new Book("впва", 10.5f, false);
        Book book1 = new Book("впва", 8.5f, false);
        Book book2 = new Book("впва", 2.5f, false);

        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(book1);
        books.add(book2);
        Collections.sort(books);
        System.out.println(books);
    }

}
