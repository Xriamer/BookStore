package com.xriamer.store.vo;

import java.io.Serializable;

public class Shopcar implements Serializable {
    private Member member;
    private Books books;
    private Integer amount;

    public Shopcar(Member member, Books books, Integer amount) {
        this.member = member;
        this.books = books;
        this.amount = amount;
    }

    public Shopcar() {
    }

    @Override
    public String toString() {
        return "Shopcar{" +
                "member=" + member +
                ", books=" + books +
                ", amount=" + amount +
                '}';
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
