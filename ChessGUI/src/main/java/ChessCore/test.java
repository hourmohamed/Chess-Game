/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author HP
 */
public class test {

    public String readFromFile() {
        File file = new File("ChessGame.txt");
        String var = "";
        try {
            FileReader f = new FileReader(file);
            BufferedReader read = new BufferedReader(f);

            String line;
            
            while ((line = read.readLine()) != null) {
                var += line ;
            }
        } catch (Exception e) {
            System.out.println("file not found");
        }
        
        return var;
    }

    public static void main(String[] args) {
        ClassicChessGame game = new ClassicChessGame();

        BoardFile file1 = BoardFile.F;
        BoardRank rank1 = BoardRank.SECOND;

        BoardFile file2 = BoardFile.F;
        BoardRank rank2 = BoardRank.FORTH;

        System.out.println(file1.getValue());
        Square s1 = new Square(file1, rank1);
        Square s2 = new Square(file2, rank2);

        Move move = new Move(s1, s2);
        System.out.println(game.makeMove(move));
        System.out.println(game.getGameStatus());
    }
}
