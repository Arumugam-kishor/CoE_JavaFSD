package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task9 {
    
    Set<String> keywords;

    public Task9(Set<String> keywords) {
        this.keywords = keywords;
    }

    public void analyzeLogFile(String inputFile, String outputFile) {
        Map<String, Integer> keywordCounts = new HashMap<>();

        for (String keyword : keywords) {
            keywordCounts.put(keyword, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        keywordCounts.put(keyword, keywordCounts.get(keyword) + 1);
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            writer.write("Summary\n");
            for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + " occurrences");
                writer.newLine();
            }

            System.out.println("Log analysis complete. Results saved in " + outputFile);

        } catch (Exception e) {
            System.err.println("Exception Occured: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        String[] keywordArray = {"WARNING","ERROR","BUG"};
        Set<String> keywords = new HashSet<>(Arrays.asList(keywordArray));

        Task9 analyzer = new Task9(keywords);
        analyzer.analyzeLogFile(inputFile, outputFile);
    }
}
