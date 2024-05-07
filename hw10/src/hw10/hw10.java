package hw10;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class hw10 {
    private static final String FILE_NAME = "scores.csv";

    public static void main(String[] args) {
        Map<String, Integer> scoresMap = readScoresFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("請選擇操作:");
            System.out.println("1. 新增學生成績");
            System.out.println("2. 查詢學生成績");
            System.out.println("3. 修改學生成績");
            System.out.println("4. 刪除學生成績");
            System.out.println("5. 列出所有學生成績");
            System.out.println("0. 退出程式");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 0:
                    saveScoresToFile(scoresMap);
                    System.out.println("程式退出。");
                    return;
                case 1:
                    addScore(scanner, scoresMap);
                    break;
                case 2:
                    queryScore(scanner, scoresMap);
                    break;
                case 3:
                    updateScore(scanner, scoresMap);
                    break;
                case 4:
                    deleteScore(scanner, scoresMap);
                    break;
                case 5:
                    listAllScores(scoresMap);
                    break;
                default:
                    System.out.println("無效的選擇。請重新輸入。");
            }
        }
    }

    private static void addScore(Scanner scanner, Map<String, Integer> scoresMap) {
        System.out.println("請輸入學生姓名:");
        String name = scanner.nextLine();
        System.out.println("請輸入學生成績:");
        int score = scanner.nextInt();
        scanner.nextLine(); // consume newline

        scoresMap.put(name, score);
        System.out.println("學生成績已添加。");
    }

    private static void queryScore(Scanner scanner, Map<String, Integer> scoresMap) {
        System.out.println("請輸入要查詢的學生姓名:");
        String name = scanner.nextLine();
        Integer score = scoresMap.get(name);
        if (score != null) {
            System.out.println(name + " 的成績為: " + score);
        } else {
            System.out.println("找不到學生 " + name);
        }
    }

    private static void updateScore(Scanner scanner, Map<String, Integer> scoresMap) {
        System.out.println("請輸入要修改成績的學生姓名:");
        String name = scanner.nextLine();
        if (scoresMap.containsKey(name)) {
            System.out.println("請輸入新的成績:");
            int newScore = scanner.nextInt();
            scanner.nextLine(); // consume newline
            scoresMap.put(name, newScore);
            System.out.println("學生成績已更新。");
        } else {
            System.out.println("找不到學生 " + name);
        }
    }

    private static void deleteScore(Scanner scanner, Map<String, Integer> scoresMap) {
        System.out.println("請輸入要刪除成績的學生姓名:");
        String name = scanner.nextLine();
        Integer removedScore = scoresMap.remove(name);
        if (removedScore != null) {
            System.out.println(name + " 的成績已刪除。");
        } else {
            System.out.println("找不到學生 " + name);
        }
    }

    private static void listAllScores(Map<String, Integer> scoresMap) {
        System.out.println("所有學生成績:");
        for (Map.Entry<String, Integer> entry : scoresMap.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }

    private static Map<String, Integer> readScoresFromFile() {
        Map<String, Integer> scoresMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                scoresMap.put(name, score);
            }
        } catch (IOException | NumberFormatException e) {
            // If the file doesn't exist or cannot be read, just return an empty map
            System.out.println("無法讀取檔案，創建一個新的空的成績列表。");
        }
        return scoresMap;
    }

    private static void saveScoresToFile(Map<String, Integer> scoresMap) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Integer> entry : scoresMap.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("無法保存成績至檔案。");
        }
    }
}
