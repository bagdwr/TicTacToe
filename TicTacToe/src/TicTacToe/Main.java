package TicTacToe;


import java.util.*;

public class Main {
    public static ArrayList<Integer> PlayersMove = new ArrayList<>();
    public static ArrayList<Integer> CompMove = new ArrayList<>();
    public static ArrayList<List>WinningConditions=new ArrayList<List>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        char[][] example = {{'1', '|', '2', '|', '3'},
                {'-', '+', '-', '+', '-'},
                {'4', '|', '5', '|', '6'},
                {'-', '+', '-', '+', '-'},
                {'7', '|', '8', '|', '9'}
        };
        int PlayerPos,CompPos;
        printBoard(example);
        System.out.println();
        printBoard(gameBoard);
        while (true)
        {
            ///For human
            System.out.println("Input position between 1-9");
            PlayerPos = scan.nextInt();
            while(PlayersMove.contains(PlayerPos) || CompMove.contains(PlayerPos))
            {
                System.out.println("This position has been taken, enter a correct position");
                PlayerPos=scan.nextInt();
            }
            MarkBoard(gameBoard,PlayerPos,"human");
            String result = checkWinner();
            if(result.length()>0) {
                System.out.println(result);
                break;
            }
            ///For computer
            Random random=new Random();
            CompPos=random.nextInt(9)+1;
            while(PlayersMove.contains(CompPos)|| CompMove.contains(CompPos))
            {
                CompPos=random.nextInt(9)+1;
            }
            MarkBoard(gameBoard,CompPos,"computer");
            result = checkWinner();
            printBoard(gameBoard);
            if(result.length()>0) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printBoard(char[][] gameBoard)
    {
        for(int i=0; i<gameBoard.length; i++)
        {
            for(int j=0; j<gameBoard[i].length; j++)
            {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }

    public static void MarkBoard(char[][] gameBoard,int position, String player)
    {
        char symbol;
        if(player=="human")
        {
            symbol='X';
            PlayersMove.add(position);
        }
        else
        {
            symbol='O';
            CompMove.add(position);
        }
        switch (position)
        {
            case 1:
                gameBoard[0][0]=symbol;
                break;
            case 2:
                gameBoard[0][2]=symbol;
                break;
            case 3:
                gameBoard[0][4]=symbol;
                break;
            case 4:
                gameBoard[2][0]=symbol;
                break;
            case 5:
                gameBoard[2][2]=symbol;
                break;
            case 6:
                gameBoard[2][4]=symbol;
                break;
            case 7:
                gameBoard[4][0]=symbol;
                break;
            case 8:
                gameBoard[4][2]=symbol;
                break;
            case 9:
                gameBoard[4][4]=symbol;
                break;
            default:
                System.out.println("You chose the wrong position");
                System.exit(0);
        }
    }

    public static String checkWinner()
    {
        List<Integer>TopRow= Arrays.asList(1,2,3);
        List<Integer> MiddleRow= Arrays.asList(4,5,6);
        List<Integer>BottomRow=Arrays.asList(7,8,9);
        List<Integer>LeftInter=Arrays.asList(1,5,9);
        List<Integer>RightInter=Arrays.asList(3,5,7);
        List<Integer>LeftColumn=Arrays.asList(1,4,7);
        List<Integer>MiddleColumn=Arrays.asList(2,5,8);
        List<Integer>RightColumn=Arrays.asList(3,6,9);

        WinningConditions.add(TopRow);
        WinningConditions.add(MiddleRow);
        WinningConditions.add(BottomRow);
        WinningConditions.add(LeftInter);
        WinningConditions.add(RightInter);
        WinningConditions.add(LeftColumn);
        WinningConditions.add(MiddleColumn);
        WinningConditions.add(RightColumn);
        for(List L:WinningConditions)
        {
            if(PlayersMove.containsAll(L))
            {
                return "Congratulations! You won the game!!!";
            }else
            if(CompMove.containsAll(L))
                {
                    return "Computer won the game!";
                }
            else
            if(PlayersMove.size()+CompMove.size()==9 &&!PlayersMove.containsAll(L) && !CompMove.containsAll(L)) {
                return "Draw";
            }
        }
        return "";
    }
}
