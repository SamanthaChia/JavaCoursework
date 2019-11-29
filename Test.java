public class Test {
    public static void main(String[] args) {
        String itemDescription = "testing this item with an a";
        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : itemDescription.toCharArray()) // Scans through user entry
        {
            if (Character.isSpaceChar(ch)) // Checks if there is a space
            {
                convertNext = true;
            } else if (convertNext) // Checks if the next char should be converted
            {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch); // Coverts all other char in the word to lower case
            }
            converted.append(ch);
        }
        String upperCaseDescription = converted.toString();
        String[] arrOfDescription = upperCaseDescription.split(" ");
        for (int i = 0; i < arrOfDescription.length; i++) {
            if(arrOfDescription[i].equals("A")){
            arrOfDescription[i] = arrOfDescription[i].toLowerCase();
            System.out.println("converted a !! "+ arrOfDescription[i]);
            }
            System.out.println("i value: " + i);
            System.out.println("array value : " + arrOfDescription[i]);

        }
        // String endDescriptionArrays = String.join(" ", arrOfDescription);
    }
}