import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TTT {
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
							   {'-', '+', '-', '+', '-'}, 
							   {' ', '|', ' ', '|', ' '}, 
							   {'-', '+', '-', '+', '-'}, 
							   {' ', '|', ' ', '|', ' '}};
		
		printGameBoard(gameBoard);
		
		while(true) {
			
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter your number (1-9): ");
			int playerPos = scan.nextInt();
			
			while(playerPos < 1 || playerPos > 9) {
				System.out.println("Invalid Input. Try Again");
				playerPos = scan.nextInt();
			}
			
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Position taken!");
				System.out.print("Enter your number (1-9): ");
				playerPos = scan.nextInt();
			}
			
			placePiece(gameBoard, playerPos, "player");
			String result = checkWinner();
			if(result.length() > 0) {
				printGameBoard(gameBoard);
				System.out.println(result);
				String decision = scan.next();
				while (!decision.equals("Y") && !decision.equals("N")) {
					System.out.println("Invalid Input. Try Again");
					decision = scan.next();
				}
				if (decision.equals("Y")) {
					main(args);
				} else if (decision.equals("N")) {
					break;
				}
			}
			
			
			
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			
			placePiece(gameBoard, cpuPos, "cpu");
			
			printGameBoard(gameBoard);
			
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				String decision = scan.next();
				while (!decision.equals("Y") && !decision.equals("N")) {
					System.out.println("Invalid Input. Try Again");
					decision = scan.next();
				}
				if (decision.equals("Y")) {
					main(args);
				} else if (decision.equals("N")) {
					break;
				}
			}
		}
	}
	
	public static void printGameBoard (char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		
		char symbol = 'X';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")) {
			symbol = '0';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List slash1 = Arrays.asList(1, 5, 9);
		List slash2 = Arrays.asList(7, 5, 3);
		
		List<List> winningConditions = new ArrayList<List>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(botRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(slash2);
		winningConditions.add(slash1);
		
		for(List l : winningConditions) {
			if(playerPositions.containsAll(l)) {
				playerPositions.clear();
				cpuPositions.clear();
				return "You won!\nPlay Again? (Y/N): ";
			} else if(cpuPositions.containsAll(l)) {
				playerPositions.clear();
				cpuPositions.clear();
				return "You lose.\nPlay Again? (Y/N): ";
			} else if(playerPositions.size() + cpuPositions.size() == 9) {
				playerPositions.clear();
				cpuPositions.clear();
				return "Tie.\nPlay Again? (Y/N): ";
			}
		}
		return "";
	}

}