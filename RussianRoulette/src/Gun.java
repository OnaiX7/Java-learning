import java.util.Random;

public class Gun {

    private int numberOfChambers;
    private boolean[] chambers;
    private int currentChamber = 0;
    private Random random;

    public Gun(int numberOfChambers) {
        this.numberOfChambers = numberOfChambers;
        this.chambers = new boolean[numberOfChambers];
        this.random = new Random();
    }

    public void emptyGun() {
        for(int i = 0; i < chambers.length; i++) {
            chambers[i] = false;
        }
        currentChamber = 0;
    }

    public void loadGun() {
        emptyGun();
        int randomChamber = random.nextInt(numberOfChambers);
        chambers[randomChamber] = true;
        currentChamber = 0;
    }

    public boolean shoot() {
        boolean isFired = chambers[currentChamber];
        chambers[currentChamber] = false;
        currentChamber = (currentChamber + 1) % numberOfChambers;
        return isFired;
    }
    public void spinChamber() {
        currentChamber = random.nextInt(numberOfChambers);
    }

    public int getCurrentChamber() {
        return currentChamber;
    }

    public int getNumberOfChambers() {
        return numberOfChambers;
    }
}
