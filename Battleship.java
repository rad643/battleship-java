import java.util.Scanner;
public class Battleship{

    final static int limit=4;

    public static void main(String[] args){

        //declaration of variables and Scanner class
        System.out.println("Welcome to Battleship!");
        System.out.println("");
        Scanner input=new Scanner(System.in);
        int x,y,row,col,i;

        //player1 matrix initialization; location board
        char[][] player1= new char[5][5];
        for(row=0;row<player1.length;row++){
            for(col=0;col<player1[row].length;col++){
                player1[row][col]='-';
            }
        }

        //player2 matrix initialization; location board
        char[][] player2= new char[5][5];
        for(row=0;row<player2.length;row++){
            for(col=0;col<player2[row].length;col++){
                player2[row][col]='-';
            }
        }      
        
        //player1 enter x and y
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        for(i=1;i<=5;i++){
            System.out.println("Enter ship " + String.valueOf(i) + " location:");
            //this only works while x and y are between 0 and 4
            do {
                x=input.nextInt();
                y=input.nextInt();
                if( (!(0<=x && x<=limit)) || (!(0<=y && y<=limit)) ){
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    System.out.println("Enter ship " + String.valueOf(i) + " location:");
                    continue;
                }
                if (player1[x][y] == '@') {
                    System.out.println("You already have a ship there. Choose different coordinates.");
                    System.out.println("Enter ship " + String.valueOf(i) + " location:");
                    continue;
                }   
            }while ( (!(0<=x && x<=limit)) || (!(0<=y && y<=limit)) || player1[x][y]=='@' );
            player1[x][y]='@';
        }

        //call method to print the player1's location board
        printBattleShip(player1); 

        //100 empty lines 
        for(i=1;i<=100;i++){
            System.out.println("");
        }



        //player2 enter x and y
        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        for(i=1;i<=5;i++){
            System.out.println("Enter ship " + String.valueOf(i) + " location:");
            do {
                x=input.nextInt();
                y=input.nextInt();
                if( (!(0<=x && x<=limit)) || (!(0<=y && y<=limit)) ){
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    System.out.println("Enter ship " + String.valueOf(i) + " location:");
                    continue;
                }
                if (player2[x][y] == '@') {
                    System.out.println("You already have a ship there. Choose different coordinates.");
                    System.out.println("Enter ship " + String.valueOf(i) + " location:");
                    continue;
                }
            }while ( (!(0<=x && x<=limit)) || (!(0<=y && y<=limit)) || player2[x][y]=='@' );
            player2[x][y]='@';
        }


        //call method to print the player2's location board
        printBattleShip(player2);

        //100 empty lines 
        for(i=1;i<=100;i++){
            System.out.println("");
        }


        //player1 target history board
        char[][] player1target= new char[5][5];
        for(row=0;row<player1target.length;row++){
            for(col=0;col<player1target[row].length;col++){
                player1target[row][col]='-';
            }
        }


        //player2 target history board
        char[][] player2target= new char[5][5];
        for(row=0;row<player2target.length;row++){
            for(col=0;col<player2target[row].length;col++){
                player2target[row][col]='-';
            }
        }       

        


        //they both need to be hitting each other until either the array of player1 or the array of player2 has had all its '@' elements replaced with 'X'.
        boolean player1StillHasShips=false;
        boolean player2StillHasShips=false;


        while(true){

            

            boolean alreadyFired1=false;
            //validate bounds first  
            do{
                System.out.println("Player 1, enter hit row/column:");
                x=input.nextInt();
                y=input.nextInt();
                if( (!(0<=x && x<=limit)) || (!(0<=y && y<=limit)) ){
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    continue;
                }

                //check if player 1 has already fired at player 2
                if(player2target[x][y]=='O' || player2target[x][y]=='X'){
                    System.out.println("You already fired on this spot. Choose different coordinates.");
                    alreadyFired1=true;
                    continue;
                }else{
                    alreadyFired1=false;
                }
            }while ( (!(0<=x && x<=limit)) || (!(0<=y && y<=limit)) || alreadyFired1 );


            //player1 is hitting player2. THIS IS WHERE THE LOGIC BUG IS 
            if(player2[x][y]=='@'){
                System.out.println("PLAYER 1 HIT PLAYER 2's SHIP!");
                player2target[x][y]='X';
                player2[x][y]='X';
                printBattleShip(player2target);
                player2StillHasShips=false; //this means that player 2 has no ships 
                for (row = 0; row < player2.length; row++) {
                    for (col = 0; col < player2[row].length; col++) {
                        if (player2[row][col] == '@') {
                            player2StillHasShips=true; //this means that player 2 still has ships left
                        }
                    }
                }
                if (player2StillHasShips==false) { //if player 2 is out of ships he obviously lost 
                    System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                    System.out.println("");
                
                    System.out.println("Final boards:");
                    System.out.println("");
                    for (row = 0; row < 5; row++) {
                        for (col = 0; col < 5; col++) {
                            if (player1[row][col] == '-' && player1target[row][col] == 'O') {
                                player1[row][col] = 'O';
                            } 
                           if (player2[row][col] == '-' && player2target[row][col] == 'O') {
                            player2[row][col] = 'O';
                           }
                        }
                    }
                    printBattleShip(player1);
                    System.out.println("");
                    printBattleShip(player2);
                    break;
                }
            }else{
                System.out.println("PLAYER 1 MISSED!");
                player2target[x][y]='O';
                printBattleShip(player2target);
                
            }



            System.out.println("");
            
            int a;
            int b;
            boolean alreadyFired2=false;
            //validate bounds first 
            do{
                System.out.print("Player 2, enter hit row/column:");
                System.out.println("");
                a=input.nextInt();
                b=input.nextInt();
                if( (!(0<=a && a<=limit)) || (!(0<=b && b<=limit)) ){
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    continue;
                }

                //check if player 2 has already fired at player 1 
                if(player1target[a][b]=='O' || player1target[a][b]=='X'){
                    System.out.println("You already fired on this spot. Choose different coordinates.");
                    alreadyFired2=true;
                    continue;
                }else{
                    alreadyFired2=false;
                }
            }while ( (!(0<=a && a<=limit)) || (!(0<=b && b<=limit)) || alreadyFired2 );





            //player2 is hitting player1. THIS IS WHERE THE LOGIC BUG IS 
            if(player1[a][b]=='@'){
                System.out.println("PLAYER 2 HIT PLAYER 1's SHIP!");
                player1target[a][b]='X';
                player1[a][b]='X';
                printBattleShip(player1target);
                
                player1StillHasShips=false; //this means that player 1 lost so the game is over 
                for (row = 0; row < player1.length; row++) {
                    for (col = 0; col < player1[row].length; col++) {
                        if (player1[row][col] == '@') {
                            player1StillHasShips=true; //this means that player 1 hasn't lost yet 
                        }
                    }
                }
                if (player1StillHasShips==false) { //player 1 has run out of ships
                    System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!");
                    System.out.println("");
                
                    System.out.println("Final boards:");
                    System.out.println("");
                    for (row = 0; row < 5; row++) {
                        for (col = 0; col < 5; col++) {
                            if (player1[row][col] == '-' && player1target[row][col] == 'O') {
                                player1[row][col] = 'O';
                            } 
                           if (player2[row][col] == '-' && player2target[row][col] == 'O') {
                            player2[row][col] = 'O';
                           }
                        }
                    }
                    printBattleShip(player1);
                    System.out.println("");
                    printBattleShip(player2);
                    break;
                }
            }else{
                System.out.println("PLAYER 2 MISSED!");
                player1target[a][b]='O';
                printBattleShip(player1target);
                
            }

            
            System.out.println("");


        }
        

    }

    
    //method that prints the boards with the top and left headers 
    private static void printBattleShip(char[][] player) {
        System.out.print("  ");
        for (int row = -1; row < 5; row++) {
            if (row > -1) {
                System.out.print(row + " ");
            }
            for (int column = 0; column < 5; column++) {
                if (row == -1) {
                    System.out.print(column + " ");
                } else {
                    System.out.print(player[row][column] + " ");
                }
            }
            System.out.println("");
        }
    }


}