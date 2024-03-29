package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 900;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 280, 250, 340};
    public static int[] heroesDamage = {20, 25, 15, 20};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic","Medic"};                                //создал игрока "Медик"

    public static void main(String[] args) {
        System.out.println("Game started:");
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        healing();                           //после каждого раунда медик лечит игроков
        if (bossHealth > 0) {
            changeBossDefence();
            bossHits();
        }
        heroesHit();
        printStatistics();
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                } else {
                    Random r = new Random();
                    int coeff = r.nextInt(5) + 2; //2,3,4,5,6
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(heroesAttackType[i]
                            + " hits critically "
                            + heroesDamage[i] * coeff);
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("_______________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i]);
        }
        System.out.println("_______________");
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDied = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDied = false;
                break;
            }
        }

        if (allHeroesDied) {
            System.out.println("Boss won!!!");
        }

        return allHeroesDied;
    }

        public static void healing() {
            if (heroesHealth[3] > 0) {
                for (int i = 0; i < heroesHealth.length; i++) {
                    heroesHealth[i] = heroesHealth[i] + heroesDamage[3];
                }
            } else {
                heroesHealth[3] = 0;                                        // создал способность медику лечить игроков
            }
    }
}

