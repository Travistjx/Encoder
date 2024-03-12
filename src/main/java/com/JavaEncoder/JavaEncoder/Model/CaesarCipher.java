package com.JavaEncoder.JavaEncoder.Model;


import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class CaesarCipher implements EncoderInterface{

    // Reference table to select keys
    private static final String REFERENCE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    // Fixed reference table size
    private static final int TABLE_SIZE = 44;

    // Offset / Key
    private int offset;

    public CaesarCipher (int offset) {
        // Ensure offset given is within boundaries
        if (offset < 0 || offset >= TABLE_SIZE) {
            throw new IllegalArgumentException("Invalid key");
        }
        this.offset = offset;
    }

    // Encode method
    @Override
    public String encode(String plainText) {
        StringBuilder encodedText = new StringBuilder();

        // Add first char to encoded text based on generated offset
        encodedText.append(REFERENCE_TABLE.charAt(offset));

        // Loop through to encode the plaintext
        for (char c : plainText.toCharArray()) {
            int index = 0;

            if (c >= 'A' && c <= 'Z'){
                index = c - 'A'; // Find index of char in REFERENCE_TABLE
            }
            else if (c >= '0' && c <= '9'){
                index = c - '0' + 26; // Find index of char in REFERENCE_TABLE
            }
            else if (c >= '(' && c <= '/'){
                index = c - '(' + 36; // Find index of char in REFERENCE_TABLE
            }
            else {
                // If not in reference table, just add to encodedText without change
                encodedText.append(c);
                continue;
            }

            // Encode the char using the key/offset
            char encryptedChar = REFERENCE_TABLE.charAt((index - offset + TABLE_SIZE) % TABLE_SIZE);

            // Add encrypted char to encoded text
            encodedText.append(encryptedChar);
        }

        return encodedText.toString();
    }

    // Decode method
    @Override
    public String decode(String encodedText) {
        StringBuilder decodedText = new StringBuilder();
        int offset = 0;

        // Find the offset
        // Check where the first char of encodedText lies within REFERENCE_TABLE
        for(char c : REFERENCE_TABLE.toCharArray()){
            if (c == encodedText.charAt(0)) break;
            offset++;
        }

        // Loop through to decode the encodedText
        for (int i = 1; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            int index = 0;

            if (c >= 'A' && c <= 'Z'){
                index = c - 'A'; // Find index of char in REFERENCE_TABLE
            }
            else if (c >= '0' && c <= '9'){
                index = c - '0' + 26; // Find index of char in REFERENCE_TABLE
            }
            else if (c >= '(' && c <= '/'){
                index = c - '(' + 36; // Find index of char in REFERENCE_TABLE
            }
            else {
                // If not in reference table, just add to decodedText without change
                decodedText.append(c);
                continue;
            }

            // Decode the char using the key/offset found from first char
            char decryptedChar = REFERENCE_TABLE.charAt((index + offset + TABLE_SIZE) % TABLE_SIZE);

            // Add decrypted char to decoded text
            decodedText.append(decryptedChar);
        }

        return decodedText.toString();
    }
}
