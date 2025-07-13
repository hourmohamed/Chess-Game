/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessCore;

/**
 *
 * @author HP
 */
public class Memento {

    private ChessBoard b;
    private Move lastmove;
    private boolean canWhiteCastleKingSide;
    private boolean canWhiteCastleQueenSide;
    private boolean canBlackCastleKingSide;
    private boolean canBlackCastleQueenSide;
    private GameStatus gameStatus ;
    private Player whoseTurn ;

    public ChessBoard getB() {
        return b;
    }

    public Move getLastmove() {
        return lastmove;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWhoseTurn() {
        return whoseTurn;
    }
    

    public boolean isCanWhiteCastleKingSide() {
        return canWhiteCastleKingSide;
    }

    public boolean isCanWhiteCastleQueenSide() {
        return canWhiteCastleQueenSide;
    }

    public boolean isCanBlackCastleKingSide() {
        return canBlackCastleKingSide;
    }

    public boolean isCanBlackCastleQueenSide() {
        return canBlackCastleQueenSide;
    }

    public Memento(ChessBoard b, Move lastmove, boolean canWhiteCastleKingSide, boolean canWhiteCastleQueenSide, boolean canBlackCastleKingSide, boolean canBlackCastleQueenSide, GameStatus gameStatus, Player whoseTurn) {
        this.b = b;
        this.lastmove = lastmove;
        this.canWhiteCastleKingSide = canWhiteCastleKingSide;
        this.canWhiteCastleQueenSide = canWhiteCastleQueenSide;
        this.canBlackCastleKingSide = canBlackCastleKingSide;
        this.canBlackCastleQueenSide = canBlackCastleQueenSide;
        this.gameStatus = gameStatus;
        this.whoseTurn = whoseTurn;
    }



    private ChessBoard returnSavedBoard() {
        return b;
    }

}
