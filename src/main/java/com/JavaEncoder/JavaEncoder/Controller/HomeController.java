package com.JavaEncoder.JavaEncoder.Controller;

import com.JavaEncoder.JavaEncoder.Model.CaesarCipher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class HomeController {

    // Display Home page
    @GetMapping("/javaencoder")
    public String displayHomePage () {
        return "home";
    }

    // Receive form information and encode/decode received plain text/encoded text
    @PostMapping("/javaencoder")
    public String processForm (@RequestParam(value = "operation") String operation,
                                @RequestParam(value = "inputTextEncoder", required = false) String textToEncode,
                               @RequestParam(value = "inputTextDecoder", required = false) String textToDecode,
                               @RequestParam(value = "encodedResult", required = false) String encodedResult,
                               @RequestParam(value = "decodedResult", required = false) String decodedResult,
                               Model model) {

        // If operation == 'encode'
        if (operation.equals("encode")) {

            // Create random object
            Random random = new Random();

            try {
                // Generate a random offset/key
                int offset = random.nextInt(44);
                // Create new CaesarCipher object
                CaesarCipher cipher = new CaesarCipher(offset);
                // Encode the plaintext
                String encodedText = cipher.encode(textToEncode);

                // Add attributes to model object
                // If decode information exists from previous form submission,
                // add in as well to retain information
                model.addAttribute("textToEncode", textToEncode);
                model.addAttribute("encodedText", encodedText);
                model.addAttribute("textToDecode", textToDecode);
                model.addAttribute("decodedText", decodedResult);
            }
            catch (IllegalArgumentException e) {
                String errorMessage = "An error occurred when generating the key.";
                model.addAttribute("errorMessage", errorMessage);
            }
        }
        // If operation == 'decode'
        else if (operation.equals("decode")) {

            CaesarCipher cipher = new CaesarCipher();

            // Decode encoded text
            String decodedText = cipher.decode(textToDecode);

            // Add attributes to model object
            // If encode information exists from previous form submission,
            // add in as well to retain information
            model.addAttribute("textToDecode", textToDecode);
            model.addAttribute("decodedText", decodedText);
            model.addAttribute("textToEncode", textToEncode);
            model.addAttribute("encodedText", encodedResult);
        }

        return "home";
    }
}
