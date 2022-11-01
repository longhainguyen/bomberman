package uet.oop.bomberman.menu;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuStage {
    public static String highScore = "";

    public static void setHighScore() {
        List<Integer> listScore = new ArrayList<>();
        StringBuilder score = new StringBuilder();
        File f = new File("res/highScore/highScore.txt");
        try {
            BufferedReader br = Files.newBufferedReader(f.toPath(), StandardCharsets.UTF_8);
            String line = null;
            while(true) {
                line = br.readLine();
                if(line==null) {
                    break;
                }else {
                    listScore.add(Integer.valueOf(line));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(listScore);
        int index = 0;
        for(int i = listScore.size() - 1; i >= 0; i--) {
            if(index > 4) {
                break;
            }
            index++;
            score.append("No.").append(index).append(" : ").append(listScore.get(i)).append("\n");
        }
        highScore = score.toString();
    }
}
