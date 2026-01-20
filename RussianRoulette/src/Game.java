import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Player> players = new ArrayList<>();
    private Gun gun = new Gun(6);
    private Scanner scanner = new Scanner(System.in);
    private int turnNumber = 0;

    public void startGame() {
        System.out.println("====RUSSIAN ROULETTE GAME====");
        createPlayers();
        gun.loadGun();
        playGame();
    }

    private void createPlayers() {
        System.out.println("Enter number of players: ");
        int amountPlayers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= amountPlayers; i++) {
            System.out.print("Enter player name: " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
        System.out.println("\n" + amountPlayers + "players are in the room.\n");
    }

    private void playGame() {
        int currentPlayerIndex = 0;

        while (getActivePlayers().size() > 1) {
            List<Player> activePlayers = getActivePlayers();
            Player currentPlayer = activePlayers.get(currentPlayerIndex % activePlayers.size());

            turnNumber++;
            System.out.println("\n--- Turn " + turnNumber + " ---");
            playerTurn(currentPlayer);

            if (currentPlayer.isEliminated()) {
                // Player eliminated, reload gun for next player
                if (getActivePlayers().size() > 1) {
                    System.out.println("\nReloading gun for next player...\n");
                    gun.loadGun();
                }
            }
            currentPlayerIndex++;
        }
        endGame();
    }

    private void playerTurn(Player player) {
        System.out.println("\n" + player.getName() + "'s turn");
        System.out.println("Spins remaining: " + player.getSpinsRemaining());

        // Ask if player wants to spin
        if (player.getSpinsRemaining() > 0) {
            System.out. print("Do you want to spin the chamber? (yes/no): ");
            String spinChoice = scanner.nextLine().trim().toLowerCase();

            if (spinChoice.equals("yes") || spinChoice.equals("y")) {
                if (player.useSpin()) {
                    gun.spinChamber();
                    System.out.println("🔄 Chamber spun!  Spins remaining: " + player.getSpinsRemaining());
                }
            }
        } else {
            System.out. println("⚠️  No spins remaining!");
        }

        // Shoot
        System.out.print("Press ENTER to shoot.. .");
        scanner.nextLine();

        boolean hit = gun.shoot();
        player.takeShot(hit);

        if (hit) {
            System.out.println("💥 BANG! " + player. getName() + " has been eliminated!");
        } else {
            System.out.println("*click* " + player.getName() + " survived!");
        }
        playersStatus();
    }

    private void playersStatus() {
        System.out.println("\n=== Current Status ===");
        for (Player player : players) {
            System.out.println(player);
        }
    }

    private List<Player> getActivePlayers() {
        List<Player> active = new ArrayList<>();
        for (Player player : players) {
            if (!player.isEliminated()) {
                active.add(player);
            }
        }
        return active;
    }

    private void endGame() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("GAME OVER!");
        System.out.println("=".repeat(40));

        Player winner = getActivePlayers().get(0);
        System.out.println("\n🏆 WINNER: " + winner.getName() + " 🏆\n");

        System.out.println("=== Final Statistics ===");
        for (Player player : players) {
            System.out.println(player);
        }
    }
    public void playAgain() {
        System.out.print("\nPlay again? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes") || response.equals("y")) {
            resetGame();
            startGame();
        } else {
            System.out.println("Thanks for playing!");
            scanner.close();
        }
    }
    private void resetGame() {
        for(Player player : players) {
            player.reset();
        }
        turnNumber = 0;
        gun.loadGun();
    }

}