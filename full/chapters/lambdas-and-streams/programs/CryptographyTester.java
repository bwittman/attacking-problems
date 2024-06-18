public class CryptographyTester {
    public static void main(String[] args) {
        String plaintext = "NOW IS THE WINTER OF OUR DISCONTENT";
        CaesarCipher caesarCipher = new CaesarCipher(3);
        VigenereCipher vigenereCipher = new VigenereCipher("SHAKESPEARE");
        AffineCipher affineCipher = new AffineCipher(5, 7);
        
        String cipherText = process(plaintext, caesarCipher::encrypt);
        System.out.println("Ciphertext: " + cipherText);
        System.out.println("Plaintext:  " + process(cipherText, caesarCipher::decrypt));
        System.out.println();

        cipherText = process(plaintext, affineCipher::encrypt);
        System.out.println("Ciphertext: " + cipherText);
        System.out.println("Plaintext:  " + process(cipherText, affineCipher::decrypt));
        System.out.println();

        cipherText = process(plaintext, vigenereCipher::encrypt);
        System.out.println("Ciphertext: " + cipherText);
        System.out.println("Plaintext:  " + process(cipherText, vigenereCipher::decrypt));
        System.out.println();
    }

    public static String process(String input, Processable processable) {
        String result = "";
        for (int i = 0; i < input.length(); ++i) {
            char letter = input.charAt(i);
            if (letter >= 'A' && letter <= 'Z')
                result += processable.process(letter, i);
            else
                result += ' ';
        }

        return result;
    }   
}
