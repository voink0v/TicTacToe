public class TicTacToe {


    private String[][] field;
    private String[] diagonal1;
    private String[] diagonal2;

    private boolean isFinished;
    private boolean switcher ;


    public TicTacToe() {
        newGame();
    }


    public void newGame() {
        switcher = true;
        isFinished = false;
        field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int g = 0; g < 3; g++) {
                field[i][g] = "-";
            }
        }
        initDiagonals();
    }

    public void initDiagonals () {
        diagonal1 = new String[]{field[0][0], field[1][1], field[2][2]};
        diagonal2 = new String[]{field[0][2], field[1][1], field[2][0]};
    }


    public String [][]  getField() {
        return field;
    }

    public String checkGame() {
        for (int i = 0; i < 3; i++) {
            String rowResult = checkRow(i);
            if (rowResult != null && !rowResult.equals("d")) {
                isFinished=true;
                return "{Player " + rowResult + " won}";
            }

            String columnResult = checkColumn(i);

            if (columnResult != null && !columnResult.equals("d")) {
                isFinished=true;
                return "{Player " + columnResult + " won}";

            }
        }
        String dioganalResult = checkDiagonal(diagonal1);
        if (dioganalResult != null && !dioganalResult.equals("d")) {
            isFinished=true;

            return "{Player " + dioganalResult + " won}";
        }
        dioganalResult = checkDiagonal(diagonal2);
        if (dioganalResult != null && !dioganalResult.equals("d")) {
            isFinished=true;
            return "{Player " + dioganalResult + " won}";
        }

        if (hasEmptyCell()) {
            return "Move completed";
        } else {
            return "D";
        }
    }

    private String checkColumn(int x) {
        boolean flag = true;
        String tmp = field[0][x];

        for (int i = 0; i < 3; i++) {
            if (field[i][x].equals("-")) {
                return null;
            }
            flag = flag && (tmp.equals(field[i][x]));
        }
        return flag ? tmp : "d";
    }

    private String checkRow(int x) {
        boolean flag = true;
        String tmp = field[x][0];

        for (int i = 0; i < 3; i++) {
            if (field[x][i].equals("-")) {
                return null;
            }
            flag = flag && (tmp.equals(field[x][i]));
        }
        return flag ? tmp : "d";
    }

    public String makeMove(int x, int y) {
        if (!hasEmptyCell() || isFinished) {
            return "Game was ended";
        }
        if (isEmpty(x-1, y-1)) {
            return doMove(x-1, y-1);
        } else {
            return String.format("{Cell %s, %s is already occupied}", x, y);
        }
    }

    public boolean isEmpty(int x, int y) {
        return field[x][y].equals("-");
    }

    public boolean hasEmptyCell() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j].equals("-")) {
                    return true;
                }
            }
        }
        return false;
    }


    private String doMove(int x, int y) {
        if (switcher) {
            field[x][y] = "X";
        } else {
            field[x][y] = "0";
        }
        initDiagonals();

        this.switcher = !switcher ;

        return checkGame();
    }

    private String checkDiagonal(String[] diagonal) {
        String tmp = diagonal[0];
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            if (diagonal[i].equals("-")) {
                return null;
            }
            flag = diagonal[i].equals(tmp) && flag;
        }
        return flag ? tmp : "d";
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println(game.makeMove(1, 2));
        System.out.println(game.getField());

        System.out.println(game.makeMove(1, 1));
        System.out.println(game.getField());

        System.out.println(game.makeMove(1, 3));
        System.out.println(game.getField());

        System.out.println(game.makeMove(1, 2));
        System.out.println(game.getField());

        System.out.println(game.makeMove(2, 2));
        System.out.println(game.getField());

        System.out.println(game.makeMove(2, 3));
        System.out.println(game.getField());

        System.out.println(game.makeMove(2, 1));
        System.out.println(game.getField());

        System.out.println(game.makeMove(3, 3));
        System.out.println(game.getField());

        System.out.println(game.makeMove(3, 2));
        System.out.println(game.getField());

        System.out.println(game.makeMove(3, 1));
        System.out.println(game.getField());
    }
}
