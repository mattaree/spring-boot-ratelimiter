package com.atm.feign.listener;

import com.atm.feign.listener.models.BooksAuthor;

/**
 * To pass this test following is observed
 *  - Both objects when compare using "equals" method show result to "true"
 *  - Integer value returned by hashcode() for both objects, i.e. "ba1" and "ba2" must be same.
 */
public class EqualsAndHashCodeTest {
    public static void main(String[] args) {
        BooksAuthor ba1 = new BooksAuthor();
        ba1.setId(1);
        ba1.setBirthYear(1933);
        ba1.setDeathYear(2020);
        ba1.setName("Abraham Lincoln");

        BooksAuthor ba2 = new BooksAuthor();
        ba2.setId(1);
        ba2.setBirthYear(1933);
        ba2.setDeathYear(2020);
        ba2.setName("Abraham Lincoln");

        // should print "true"
        System.out.println("Equals Method : " + ba1.equals(ba2));

        // Integer value returned by hashcode() for both objects, i.e. "ba1" and "ba2" must be same.
        System.out.println("Hashcode number ba1 : " + ba1.hashCode());
        System.out.println("Hashcode number ba2 : " + ba2.hashCode());
    }
}
