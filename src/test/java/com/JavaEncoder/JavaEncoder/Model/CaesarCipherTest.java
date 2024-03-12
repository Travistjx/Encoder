package com.JavaEncoder.JavaEncoder.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTest {


    @Test
    void itShouldEncodePlainTextAndReturnEncodedText() {
        // Create new cipher object
        CaesarCipher cipher = new CaesarCipher(1);

        // Encode plaintext
        String plaintext = "HELLO";
        String encodedText = cipher.encode(plaintext);

        // Check if text is encoded correctly
        // BGDKKN is the correct result
        assertEquals("BGDKKN", encodedText);
    }

    @Test
    void itShouldDecodeEncodedTextAndReturnDecodedText() {
        // Create new cipher object
        CaesarCipher cipher = new CaesarCipher();

        // Decode encoded text
        String encodedText = "BGDKKN";
        String decodedText = cipher.decode(encodedText);

        // Check if text is decoded correctly
        // HELLO is the correct result
        assertEquals("HELLO", decodedText);
    }
}