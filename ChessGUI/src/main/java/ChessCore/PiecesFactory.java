/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;
import ChessCore.Player;
import Pieces.*;

/**
 *
 * @author HP
 */




public class PiecesFactory {
    
    private static String pawn = "Pawn";
    private static String knight = "Knight";
    private static String king = "King";
    private static String queen = "Queen";
    private static String bishop = "Bishop";
    private static String rook = "Rook";
    
    
    public Piece pieceFactory(String name, Player color)
    {
        switch(name)
        {
            case "Pawn":
                return new Pawn(color);
            case "Knight":
                return new Knight(color);
            case "Queen":
                return new Queen(color);
            case "Bishop":
                return new Bishop(color);
            case "King":
                return new King(color);
            case "Rook":
                return new Rook(color);
                
        }
       return null; 
    }
    
    
    
}
