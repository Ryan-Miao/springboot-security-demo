package com.test.springsecuritydemo.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CustomizedPasswordEncoderTest {

    @Test
    public void encode() {
        CustomizedPasswordEncoder encoder = new CustomizedPasswordEncoder();
        String encoded = encoder.encode("admin");
        assertEquals("25f214e1c1b400421a8ada8a213c9fcb", encoded);
        assertEquals(32, encoded.length());
        String test = encoder.encode("test");
        assertEquals("76bd7b432856c9ea6ce558cba9d955ff", test);
        assertEquals(32, test.length());
    }
}