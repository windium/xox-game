import java.util.*;

public class Main {
    public static String createTable(ArrayList<String> moves) {
        String emptyTable = "   1   2   3 \n1  a | b | c\n  ———+———+———\n2  d | e | f\n  ———+———+———\n3  g | h | i\n";
        for (int i = 0; i < moves.size(); i++) {
            String[] posSplit = moves.get(i).split(" ");

            int posX = Integer.parseInt(posSplit[0]);
            int posY = Integer.parseInt(posSplit[1]);

            String[][] alphabet = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}};

            String index = alphabet[posX-1][posY-1];

            emptyTable = emptyTable.replace(index, posSplit[2]);

        }

        emptyTable = emptyTable.replaceAll("[abcdefghi]", " ");

        return emptyTable;
    }

    public static boolean checkTable(ArrayList<String> moves) {
        String emptyTable = "a b c d e f g h i";
        boolean condition = false;
        for (String move: moves) {
            String[] posSplit = move.split(" ");
            int posX = Integer.parseInt(posSplit[0]);
            int posY = Integer.parseInt(posSplit[1]);

            String[][] alphabet = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}};

            emptyTable = emptyTable.replace(alphabet[posX-1][posY-1], posSplit[2]);

            String[] splitted = emptyTable.split(" ");

            if(Objects.equals(splitted[0], splitted[1]) && Objects.equals(splitted[1], splitted[2])) {
                // 1st row
                condition = true;
            } else if(Objects.equals(splitted[3], splitted[4]) && Objects.equals(splitted[4], splitted[5])) {
                // 2nd row
                condition = true;
            } else if(Objects.equals(splitted[6], splitted[7]) && Objects.equals(splitted[7], splitted[8])) {
                // 3rd row
                condition = true;
            } else if(Objects.equals(splitted[0], splitted[3]) && Objects.equals(splitted[3], splitted[6])) {
                // 1st column
                condition = true;
            } else if(Objects.equals(splitted[1], splitted[4]) && Objects.equals(splitted[4], splitted[7])) {
                // 2nd column
                condition = true;
            } else if(Objects.equals(splitted[2], splitted[5]) && Objects.equals(splitted[5], splitted[8])) {
                // 3rd column
                condition = true;
            } else if(Objects.equals(splitted[0], splitted[4]) && Objects.equals(splitted[4], splitted[8])) {
                // Cross 1 to 9
                condition = true;
            } else if(Objects.equals(splitted[3], splitted[4]) && Objects.equals(splitted[4], splitted[6])) {
                // Cross 3 to 6
                condition = true;
            }
        }
        return condition;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<String> moves = new ArrayList<String>(9);

        Random random = new Random();

        System.out.println(createTable(moves));

        while(moves.size() < 9) {
            if(checkTable(moves)) {
                System.out.println(createTable(moves));
                System.out.println("Game Ends.");
                break;
            }
            String pos = input.nextLine();

            if (!moves.contains(pos + " O") && !moves.contains(pos + " X")) {
                moves.add(pos + " X");
                String randomPos;
                do {
                    randomPos = random.nextInt(1,4) + " " + random.nextInt(1,4);
                } while (moves.contains(randomPos+ " O") || moves.contains(randomPos+ " X"));
                moves.add(randomPos+ " O");
                String index = createTable(moves);
                //System.out.println(moves);
                System.out.println(index);
            } else {
                System.out.println("Try another position.");
            }
        }
    }
}