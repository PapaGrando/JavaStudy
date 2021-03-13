import java.util.Random;
import java.util.Scanner;

class Scratch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("type field size");
        int size = sc.nextInt();

        Game game = new Game(size, sc);
        game.processGame();

        sc.close();
    }
}

class Game {

    private static final char DOT_EMPTY = '*';
    private static final char DOT_PLAYER = 'X';
    private static final char DOT_ENEMY = 'O';
    private static final byte MAP_EMPTY = 0;
    private static final byte MAP_PLAYER = 1;
    private static final byte MAP_ENEMY = -1;

    private int size = 3;
    private Scanner sc;

    public Game(int size, Scanner sc) {
        this.size = size;
        this.sc = sc;
    }

    public Game() {
        this.size = 3; //default
    }

    Random rand = new Random();

    public void processGame() {
        //init
        Map map = new Map(size);

        showFieldArray(map);

        System.out.println("Player is " + DOT_PLAYER);

        boolean gameIsActive = true;
        int turnsLeft = size * size;

        while (gameIsActive) {

            if (turnsLeft <= 0) {
                gameIsActive = false;
                break;
            }

            playerTurn(map);
            turnsLeft--;

            if (turnsLeft <= 0) {
                gameIsActive = false;
                break;
            }
            enemyTurn(map);
            showFieldArray(map);

            if (checkWinner(map))
                gameIsActive = false;

        }
    }

    private void playerTurn(Map map) {
        int column, row;

        System.out.println("Type Row num");
        column = sc.nextInt();
        System.out.println("Type Column num");
        row = sc.nextInt();

        if (column > size || column < 0 || row > size || row < 0) {
            System.out.println("invalid cell");
            this.playerTurn(map);
        }

        if (map.field[column - 1][row - 1] != 0) {
            System.out.println("Choose free cell");
            this.playerTurn(map);
        }
        map.field[column - 1][row - 1] = MAP_PLAYER;
    }

    private void enemyTurn(Map map) {
        int enemyColumn = 0, enemyRow = 0;
        int target = rand.nextInt(map.field.length);

        enemyColumn = rand.nextInt(size);
        enemyRow = rand.nextInt(size);

        boolean enemyCellBusy = false;

        int i = 0;
        while (map.field[enemyColumn][enemyRow] == MAP_ENEMY ||
                map.field[enemyColumn][enemyRow] == MAP_PLAYER) {

            if ((size * size) > i) {
                enemyRow = enemyRow + 1;

                if (enemyRow >= size) {
                    enemyColumn = enemyColumn + 1;
                    enemyRow = 0;

                    if (enemyColumn >= size) {
                        enemyColumn = 0;
                    }
                }
                i++;
                continue;
            }
            enemyCellBusy = true;
            break;
        }
        if (enemyCellBusy) {
            System.out.println("Enemy cant turn - end cells");
        } else {
            map.field[enemyColumn][enemyRow] = MAP_ENEMY;
        }
    }

    //если игра закончилась (ничья, выйгрыш) - возвращает true
    private boolean checkWinner(Map map) {

        int vertCheck = 0, horisCheck = 0, diag1Check = 0, diag2Check = 0,
                freeCellsCheker = 1; // если это значение не равно 0 в конце, то игра закончится в ничью

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                vertCheck += map.field[i][j];
                horisCheck += map.field[j][i];
                freeCellsCheker *= map.field[i][j];
            }

            diag1Check += map.field[i][i];
            diag2Check += map.field[(size - 1) - i][i];

            if (horisCheck == size * MAP_ENEMY || vertCheck == size * MAP_ENEMY) {
                System.out.println("Winner is ENEMY");
                return true;
            } else if (horisCheck == size * MAP_PLAYER || vertCheck == size * MAP_PLAYER) {
                System.out.println("Winner is PLAYER");
                return true;
            }
            vertCheck = 0;
            horisCheck = 0;
        }

        if (diag1Check == size * MAP_ENEMY || diag2Check == size * MAP_ENEMY) {
            System.out.println("Winner is ENEMY");
            return true;
        } else if (diag1Check == size * MAP_PLAYER || diag2Check == size * MAP_PLAYER) {
            System.out.println("Winner is PLAYER");
            return true;
        }

        if (freeCellsCheker != 0) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }

    private void showFieldArray(Map map) {

        String str = "";
        StringBuffer sb = new StringBuffer(str);
        for (int i = 0; size > i; i++) {
            for (int j = 0; size > j; j++) {
                if (map.field[i][j] == MAP_ENEMY) sb.append(DOT_ENEMY);
                else if (map.field[i][j] == MAP_PLAYER) sb.append(DOT_PLAYER);
                else sb.append(DOT_EMPTY);

                if ((size - 1) > j) sb.append("  |  ");
            }
            sb.append('\n');
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    class Map {

        int[][] field;

        public Map(int size) {

            this.field = new int[size][size];
            for (int i = 0; size > i; i++) {
                for (int j = 0; size > j; j++) {
                    this.field[i][j] = MAP_EMPTY;
                }
            }

        }
    }
}