package com.stl.skipthelibrary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookDescriptionTest {
    private BookDescription bookDescription;

    @Before
    public void setup(){
        bookDescription = new BookDescription("test title", "test synopsis", "test author", null);
    }

    @Test
    public void testConstructor() {
        assertEquals("test title", bookDescription.getTitle());
        assertEquals("test synopsis", bookDescription.getSynopsis());
        assertEquals("test author", bookDescription.getAuthor());
        assertEquals(null, bookDescription.getRating());
    }

    @Test
    public void testSetters() {
        bookDescription.setTitle("test set title");
        assertEquals("test set title", bookDescription.getTitle());
        bookDescription.setAuthor("test set author");
        assertEquals("test set author", bookDescription.getAuthor());
        bookDescription.setSynopsis("test set synopsis");
        assertEquals("test set synopsis", bookDescription.getSynopsis());
        bookDescription.setRating(new Rating());
        assertEquals(new Rating(), bookDescription.getRating());
    }
}