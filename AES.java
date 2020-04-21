package sample;
import java.math.BigInteger;

public class AES {

    private final static int[] S_BOX = {
            0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
            0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
            0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
            0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
            0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
            0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
            0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
            0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
            0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
            0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
            0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
            0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
            0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
            0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
            0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
            0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
    };

    private final static int[] INV_S_BOX = {
            0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB,
            0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB,
            0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E,
            0x08, 0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25,
            0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92,
            0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84,
            0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06,
            0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B,
            0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73,
            0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E,
            0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B,
            0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4,
            0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F,
            0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF,
            0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61,
            0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D,
    };


    private final static int RC[] = {
            -1, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36
    };

    // one r-con is 4 bytes
    private static int R_CON[][] = new int[11][4];

    // 44 words, one word is 4 Bytes
    private static int WORD[][] = new int[44][4];

    private static final int MIX[][] = {
            {0x02, 0x03, 0x01, 0x01},
            {0x01, 0x02, 0x03, 0x01},
            {0x01, 0x01, 0x02, 0x03},
            {0x03, 0x01, 0x01, 0x02}
    };

    private static final int MIX_INV[][] = {
            {0x0e, 0x0b, 0x0d, 0x09},
            {0x09, 0x0e, 0x0b, 0x0d},
            {0x0d, 0x09, 0x0e, 0x0b},
            {0x0b, 0x0d, 0x09, 0x0e}
    };

    // plain 16 bytes
    private static int[] plain = new int[16];

    // 11 keys, each is 16 bytes
    private static int key[][] = new int[11][16];



    private static int[] copyOf(int array[]) {
        int output[] = new int[array.length];
        for(int i = 0; i < array.length; i++)
            output[i] = array[i];
        return output;
    };

    private static int[][] copyOf(int matrix[][]) {
        int output[][] = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            output[i] = copyOf(matrix[i]);
        return output;
    };

    private static int[] substitute(int array[]) {
        int output[] = copyOf(array);
        for(int i = 0; i < array.length; i++) {
            output[i] = S_BOX[output[i]];
        }
        return output;
    }


    private static int[][] substitute(int matrix[][]) {
        int output[][] = copyOf(matrix);
        for(int i = 0; i < matrix.length; i++) {
            output[i] = substitute(output[i]);
        }
        return output;
    }

    private static int[] substituteInv(int array[]) {
        int output[] = copyOf(array);
        for(int i = 0; i < array.length; i++) {
            output[i] = INV_S_BOX[output[i]];
        }
        return output;
    }

    private static int[][] substituteInv(int matrix[][]) {
        int output[][] = copyOf(matrix);
        for(int i = 0; i < matrix.length; i++) {
            output[i] = substituteInv(output[i]);
        }
        return output;
    }

    private static int multiplyByTwo(int a) {
        if(a < 128) return (a << 1) % 0x100;
        else  return ((a << 1) ^ 0x1b) % 0x100;
    }

    private static int multiply(int a, int b) {
        // 110101.b = 1.b + 100.b + 10000.b

        int sum = 0;
        int temp = b;

        for(int i = 0; i < 16; i++) {
            if(((a >> i) & 1) == 1) {
                sum ^= temp;
            }
            temp = multiplyByTwo(temp);
        }
        return sum;
    }

    private static int[][] mix(int matrix[][]) {
        int output[][] = new int[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    output[i][j] ^= multiply(MIX[i][k], matrix[k][j]);
                    output[i][j] %= 256;
                }
            }
        }
        return output;
    }

    private static int[][] mixInv(int matrix[][]) {
        int output[][] = new int[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    output[i][j] ^= multiply(MIX_INV[i][k], matrix[k][j]);
                    output[i][j] %= 256;
                }
            }
        }
        return output;
    }

    private static void generateR_CON() {
        for(int i = 1; i <= 10; i++) {
            R_CON[i] = new int[] {RC[i], 0, 0, 0};
        }
    }

    private static String intToHEXString(int n) {
        if(n > 16) return new BigInteger(String.valueOf(n)).toString(16);
        return "0" + new BigInteger(String.valueOf(n)).toString(16);
    }

    private static String arrayToHEXString(int[] array) {
        String output = "";
        for(int i = 0; i < array.length; i++) {
            output += intToHEXString(array[i]) + " ";
        }
        return output;
    }

    private static String matrixToHEXString(int matrix[][]) {
        return (
                arrayToHEXString(matrix[0]) + "\n"
                + arrayToHEXString(matrix[1]) + "\n"
                + arrayToHEXString(matrix[2]) + "\n"
                + arrayToHEXString(matrix[3])
        );
    }

    private static int[] rotWord(int word[]) {
        return new int[] {word[1], word[2], word[3], word[0]};
    }

    private static int[] rotWord(int word[], int times) {
        if(times == 0) return copyOf(word);
        if(times > 0) return rotWord(rotWord(word), times-1);
        return new int[] {-1, -1, -1, -1}; // times in negative, invalid
    }

    private static int[][] shiftRow(int matrix[][]) {
        int output[][] = new int[4][4];
        for(int i = 0; i < 4; i++) {
            output[i] = rotWord(matrix[i], i);
        }
        return output;
    }

    // 0 1 2 3 -rs-> 3 0 1 2
    // -ls-> 1 2 3 0 --> 2 3 0 1 --> 3 0 1 2

    private static int[][] shiftRowInv(int matrix[][]) {
        int output[][] = new int[4][4];
        for(int i = 0; i < 4; i++) {
            output[i] = rotWord(matrix[i], 4-i);
        }
        return output;
    }

    private static int[] xor(int a[], int b[]) {
        int output[] = new int[4];
        for(int i = 0; i < 4; i++) {
            output[i] = a[i] ^ b[i];
        }
        return output;
    }

    private static int[][] xor(int a[][], int b[][]) {
        int output[][] = new int[4][4];
        for(int i = 0; i < 4; i++) {
            output[i] = xor(a[i] , b[i]);
        }
        return output;
    }

    private static void fillHEXIntoArray(String hex, int array[]) {
        for(int i = 0; i < 16; i++) {
            array[i] = new BigInteger(hex.substring(2*i, 2*i+2), 16).intValue();
        }
    }

    private static void KeyExpansion () {
        generateR_CON();
        int temp[] = new int[4];
        for (int i = 0; i < 4; i++) {
            WORD[i] = new int[]{key[0][4*i], key[0][4*i + 1], key[0][4*i + 2], key[0][4*i + 3]};
        }

        for (int i = 4; i < 44; i++) {
            temp = copyOf(WORD[i - 1]);
            if (i % 4 == 0) {
                temp = xor(substitute(rotWord(temp)) , R_CON[i / 4]);
            }
            WORD[i] = xor(WORD[i-4] , temp);
        }

        for(int i = 1; i <= 10; i++) {
            key[i] = new int[] {
                    WORD[4*i][0], WORD[4*i][1], WORD[4*i][2], WORD[4*i][3],
                    WORD[4*i + 1][0], WORD[4*i + 1][1], WORD[4*i + 1][2], WORD[4*i + 1][3],
                    WORD[4*i + 2][0], WORD[4*i + 2][1], WORD[4*i + 2][2], WORD[4*i + 2][3],
                    WORD[4*i + 3][0], WORD[4*i + 3][1], WORD[4*i + 3][2], WORD[4*i + 3][3]
            };
        }
    }



    private static ResultWithMSG validate(String inputString, String keyString) {
        inputString = inputString.replaceAll(" ", "");
        keyString = keyString.replaceAll(" ", "");

        if(keyString.length() < 32) {
            return new ResultWithMSG("Key is less than 16 bytes", "error", "");
        }


        for(int i = 0; i < inputString.length(); i++) {
            if(
                    (inputString.charAt(i) < '0' || inputString.charAt(i) > '9') &&
                    (inputString.charAt(i) < 'a' || inputString.charAt(i) > 'f') &&
                    (inputString.charAt(i) < 'A' || inputString.charAt(i) > 'F')
            ) {
                return new ResultWithMSG("Foreign symbol in text", "error", "");
            }
        }


        for(int i = 0; i < keyString.length(); i++) {
            if(
                    (keyString.charAt(i) < '0' || keyString.charAt(i) > '9') &&
                    (keyString.charAt(i) < 'a' || keyString.charAt(i) > 'f') &&
                    (keyString.charAt(i) < 'A' || keyString.charAt(i) > 'F')
            ) {
                return new ResultWithMSG("Foreign key in text", "error", "");
            }
        }

        if(inputString.length() % 32 != 0) {
            return new ResultWithMSG("Text will be right padded with zeros.", "warning", "");
        }

        if(keyString.length() > 32) {
            return new ResultWithMSG("Long key, the first 16 bytes only will be used.", "warning", "");
        }

        return new ResultWithMSG("", "all izz well :)", "");
    }

    private static void preprocess(String inputString, String keyString) {

        inputString = inputString.replaceAll(" ", "");
        keyString = keyString.replaceAll(" ", "");

        if(keyString.length() > 32) {
            keyString = keyString.substring(0, 32);
        }

        if(inputString.length() > 32) {
            inputString = inputString.substring(0, 32);
        }

        while(inputString.length() % 32 != 0) {
            inputString += '0';
            System.out.println(inputString);
        }

        fillHEXIntoArray(inputString, plain);
        fillHEXIntoArray(keyString, key[0]);

    }

    // fill array of length 16 (16 bytes) into 4x4 matrix
    private static int[][] fillArrayIntoMatrix(int array[]) {
        int matrix[][] = new int[4][4];
        int idx = 0;
        for(int col = 0; col < 4; col++) {
            for(int row = 0; row < 4; row++) {
                matrix[row][col] = array[idx++];
            }
        }
        return matrix;
    }

    public static String getInfoString() {
        String output = "";
        output += "plain text: " + arrayToHEXString(plain) + "\n";
        output += "after S_BOX: " + arrayToHEXString(substitute(plain)) + "\n";
        output += "after INV_S_BOX: " + arrayToHEXString(substituteInv(substitute(plain))) + "\n";
        output += "-------------------------------------" + "\n";

        for(int i = 0; i <= 10; i++) {
            output += ("Key " + i + ": \t" + arrayToHEXString(key[i]) + "\n");
        }
        return output;
    }

    public static ResultWithMSG transform(String transformation, String inputString, String keyString) {
        String output = "";
        ResultWithMSG validateResult = validate(inputString, keyString);
        if(validateResult.messageType == "error") return validateResult;
        System.out.println((validateResult.messageType == "error") + " " + validateResult.messageType);
        preprocess(inputString, keyString);
        KeyExpansion();

        if(transformation == "encryption") {
            int curKey[][] = fillArrayIntoMatrix(key[0]);
            int state[][] = xor(curKey, fillArrayIntoMatrix(plain));
            System.out.println(matrixToHEXString(state));
            for(int round = 1; round <= 9; round++) {
                curKey = fillArrayIntoMatrix(key[round]);
                state = xor(mix(shiftRow(substitute(state))), curKey);
                System.out.println("AES output after Round " + round + ":");
                System.out.println(matrixToHEXString(state));
                System.out.println("-----------------------");
            }
            curKey = fillArrayIntoMatrix(key[10]);
            state = xor(shiftRow(substitute(state)), curKey);
            System.out.println("AES output after Round " + 10 + ":");
            System.out.println(matrixToHEXString(state));

            for(int col = 0; col < 4; col++) {
                for(int row = 0; row < 4; row++) {
                    output += intToHEXString(state[row][col]) + " ";
                }
            }
        }

        if(transformation == "decryption") {
            int curKey[][] = fillArrayIntoMatrix(key[10]);
            int state[][] = xor(curKey, fillArrayIntoMatrix(plain));
            System.out.println(matrixToHEXString(state));
            for(int round = 9; round >= 1; round--) {
                curKey = fillArrayIntoMatrix(key[round]);
                state = mixInv(xor(substituteInv(shiftRowInv(state)), curKey));
                System.out.println("AES output after Round " + round + ":");
                System.out.println(matrixToHEXString(state));
                System.out.println("-----------------------");
            }
            curKey = fillArrayIntoMatrix(key[0]);
            state = xor(substituteInv(shiftRowInv(state)), curKey);
            System.out.println("AES output after Round " + 0 + ":");
            System.out.println(matrixToHEXString(state));

            for(int col = 0; col < 4; col++) {
                for(int row = 0; row < 4; row++) {
                    output += intToHEXString(state[row][col]) + " ";
                }
            }
        }
        return new ResultWithMSG(validateResult.message, validateResult.messageType, output);
    }
}
