public class Player {

    private String name;
    private boolean eliminated = false;
    private int survivalCount = 0;
    private int totalShots = 0;
    private int spinsRemaining = 3;

    public Player(String name) {
        this.name = name;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public String getName() {
        return name;
    }

    public int getSurvivalCount() {
        return survivalCount;
    }

    public int getTotalShots() {
        return totalShots;
    }

    public int getSpinsRemaining() {
        return spinsRemaining;
    }

    public void takeShot(boolean hit) {
        totalShots++;
        if(hit) {
            eliminated = true;
        }
        else {
            survivalCount++;
        }
    }
public boolean useSpin() {
        if(spinsRemaining > 0) {
            spinsRemaining--;
            return true;
        }
        return false;
    }
    public void reset() {
        this.eliminated = false;
        this.survivalCount = 0;
        this.totalShots = 0;
        this.spinsRemaining = 3;
    }
    public double getSurvivalRate() {
        if (totalShots == 0) {
            return 0.0;
        }
        return (double) survivalCount / totalShots * 100;
    }
    @Override
    public String toString() {
        if (totalShots == 0) {
            return String.format("%s - Status: %s | Spins:  %d",
                    name,
                    eliminated ? "ELIMINATED" : "ALIVE",
                    spinsRemaining);
        }
        return String.format("%s - Status: %-10s | Shots: %d | Survived: %d (%.1f%%) | Spins: %d",
                name,
                eliminated ?  "ELIMINATED" : "ALIVE",
                totalShots,
                survivalCount,
                getSurvivalRate(),
                spinsRemaining);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return name != null ?  name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
