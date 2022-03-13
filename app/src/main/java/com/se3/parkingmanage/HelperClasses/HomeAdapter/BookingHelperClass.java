package com.se3.parkingmanage.HelperClasses.HomeAdapter;

public class BookingHelperClass {
    String price;
    String bookTitle;
    String startDate;
    String stopDate;
    String Duedate;

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public BookingHelperClass(String price, String bookTitle, String startDate, String duedate) {
        this.price = price;
        this.bookTitle = bookTitle;
        this.startDate = startDate;
        Duedate = duedate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuedate() {
        return Duedate;
    }

    public void setDuedate(String duedate) {
        Duedate = duedate;
    }
}
