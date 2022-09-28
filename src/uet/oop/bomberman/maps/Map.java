package uet.oop.bomberman.maps;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public int col;
    public int row;
    private String level;
    private List<String> readFile;
    public String[][] mapCode;

    private void setReadFile(String fileName) {
        File f2 = new File(fileName);
        try {
            readFile = Files.readAllLines(f2.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    set and get map's code of level
     */
    public void setAndGetMapCode(String fileName) {
        this.setReadFile(fileName);
        String[] numbers_info = readFile.get(0).split(" ");
        row = Integer.parseInt(numbers_info[1]);
        col = Integer.parseInt(numbers_info[2]);
        mapCode = new String[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mapCode[i][j] = readFile.get(i + 1).charAt(j) + "";
            }
        }
    }
}
