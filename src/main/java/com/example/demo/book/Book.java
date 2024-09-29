package com.example.demo.book;

public class Book {
    private int id;
    private String name;
    private int pages;
    private int price;
    private String author;

    public Book(){
    }

    public Book(int id, String name, int pages, int price, String author){
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.author = author;
    }

    public Book(String name, int pages, int price, String author){
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }
}