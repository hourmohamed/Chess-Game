package ChessCore;

import Pieces.Queen;
import Pieces.Rook;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Knight;
import Pieces.Bishop;
import Pieces.Piece;

public final class ClassicBoardInitializer implements BoardInitializer {
    private static final BoardInitializer instance = new ClassicBoardInitializer();
    
    PiecesFactory p = new PiecesFactory();

    private ClassicBoardInitializer() {
    }

    public static BoardInitializer getInstance() {
        return instance;
    }

    @Override
    public Piece[][] initialize() {
        Piece[][] initialState = {
            {p.pieceFactory("Rook", Player.WHITE), p.pieceFactory("Knight", Player.WHITE) , p.pieceFactory("Bishop", Player.WHITE), p.pieceFactory("Queen", Player.WHITE), p.pieceFactory("King", Player.WHITE), p.pieceFactory("Bishop", Player.WHITE), p.pieceFactory("Knight", Player.WHITE), p.pieceFactory("Rook", Player.WHITE)},
            {p.pieceFactory("Pawn", Player.WHITE), p.pieceFactory("Pawn", Player.WHITE), p.pieceFactory("Pawn", Player.WHITE), p.pieceFactory("Pawn", Player.WHITE), p.pieceFactory("Pawn", Player.WHITE), p.pieceFactory("Pawn", Player.WHITE), p.pieceFactory("Pawn", Player.WHITE), p.pieceFactory("Pawn", Player.WHITE)},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {p.pieceFactory("Pawn", Player.BLACK), p.pieceFactory("Pawn", Player.BLACK), p.pieceFactory("Pawn", Player.BLACK), p.pieceFactory("Pawn", Player.BLACK), p.pieceFactory("Pawn", Player.BLACK), p.pieceFactory("Pawn", Player.BLACK), p.pieceFactory("Pawn", Player.BLACK), p.pieceFactory("Pawn", Player.BLACK)},
            {p.pieceFactory("Rook", Player.BLACK), p.pieceFactory("Knight", Player.BLACK),p.pieceFactory("Bishop", Player.BLACK), p.pieceFactory("Queen", Player.BLACK), p.pieceFactory("King", Player.BLACK),p.pieceFactory("Bishop", Player.BLACK),p.pieceFactory("Knight", Player.BLACK),p.pieceFactory("Rook", Player.BLACK)}
        };
        return initialState;
    }
}
