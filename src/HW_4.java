import java.util.Random;

public class HW_4 {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int medicHealth = 500;
    public static double healPower = 0;
    public static long[] heroesHealth = {280, 270, 250};
    public static int[] heroesDamage = {20, 15, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic"};
    public static int roundNumber;

    public static void main(String[] args) {
        // printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        printStatistics();
        bossAttack();
        heroesAttack();
        // printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesAttack() {
        if (medicHealth > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] < 100) { // Исцеляем ели здоровье мегьше 100
                    Random rnd = new Random();
                    healPower = rnd.nextDouble(2.0);
                    System.out.println("Исцелен " + heroesAttackType[i] + " на " + Math.round(heroesHealth[i] * healPower) + " единиц , коэфицент  " + healPower);
                    heroesHealth[i] = heroesHealth[i] + Math.round(heroesHealth[i] * healPower);

                }
            }
        }
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {

                int damage = heroesDamage[i];
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coef = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage *= coef;
                    System.out.println("Critical damage " + heroesAttackType[i] + " : " + damage);
                }
                bossHealth = bossHealth - damage;
                if (bossHealth < 0) {
                    bossHealth = 0;
                }
            }
        }
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;


                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            }
        }

        if (medicHealth > 0) {
            medicHealth = medicHealth - bossDamage;
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " -------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }
    }
}


