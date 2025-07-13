/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChessGUI;

import ChessCore.ChessBoard;
import Pieces.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ChessCore.ClassicChessGame;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import ChessCore.Move;
import ChessCore.Square;
import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.GameStatus;
import ChessCore.PawnPromotion;
import ChessCore.Player;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Board extends JFrame {

    private static final ClassicChessGame game = new ClassicChessGame();
    private static int clickCounter = 0;
    private static Square s1 = null, s2 = null;
    private static boolean WhitekingInCheck = false, BlackKingInCheck = false;
    private static boolean isMouseClickedOnce = false;
    private static boolean flag = false;
    private static ChessBoard chessBoard = game.getBoard();
    private static Board b = new Board();
    private static JFrame frame = new JFrame();
    private static PawnPromotion promotion = PawnPromotion.None;
    private static Move move = null;
    

    

    public Image[] returnImage() throws IOException {

        Image[] im = new Image[12];

        im[0] = ImageIO.read(new File("BlackRook.png"));
        im[1] = ImageIO.read(new File("BlackBishop.png"));
        im[2] = ImageIO.read(new File("BlackKnight.png"));
        im[3] = ImageIO.read(new File("BlackKing.png"));
        im[4] = ImageIO.read(new File("BlackQueen.png"));
        im[5] = ImageIO.read(new File("BlackPawn.png"));
        im[6] = ImageIO.read(new File("WhiteRook.png"));
        im[7] = ImageIO.read(new File("WhiteBishop.png"));
        im[8] = ImageIO.read(new File("WhiteKnight.png"));
        im[9] = ImageIO.read(new File("WhiteKing.png"));
        im[10] = ImageIO.read(new File("WhiteQueen.png"));
        im[11] = ImageIO.read(new File("WhitePawn.png"));

        return im;
    }

    public static BoardRank returnRank(int x) {
        switch (x) {
            case 0:
                return BoardRank.EIGHTH;
            case 1:
                return BoardRank.SEVENTH;
            case 2:
                return BoardRank.SIXTH;
            case 3:
                return BoardRank.FIFTH;
            case 4:
                return BoardRank.FORTH;
            case 5:
                return BoardRank.THIRD;
            case 6:
                return BoardRank.SECOND;
            case 7:
                return BoardRank.FIRST;
        }

        return null;
    }

    public static BoardFile returnFile(int y) {
        switch (y) {
            case 0:
                return BoardFile.A;
            case 1:
                return BoardFile.B;
            case 2:
                return BoardFile.C;
            case 3:
                return BoardFile.D;
            case 4:
                return BoardFile.E;
            case 5:
                return BoardFile.F;
            case 6:
                return BoardFile.G;
            case 7:
                return BoardFile.H;

        }
        return null;
    }

    public JPanel returnPanel(JFrame frame) throws IOException {
        Image[] img = b.returnImage();
        JPanel pn = new JPanel() {

            @Override
            public void paint(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;

                boolean white = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (white) {
                            g2d.setColor(Color.pink);
                        } else {
                            g2d.setColor(Color.white);
                        }
                        g2d.fillRect(i * 64, j * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }

//                System.out.println("turn" + game.getWhoseTurn());
                for (int i = 0; i < 8; i++) {
                    int x = 0;
                    for (int j = 0; j < 8; j++) {
                        Square s = new Square(returnFile(i), returnRank(j));
                        if (game.getPieceAtSquare(s) != null) {
                            if (game.getPieceAtSquare(s) instanceof Rook) {
                                x = 0;
                            }
                            if (game.getPieceAtSquare(s) instanceof Bishop) {
                                x = 1;
                            }
                            if (game.getPieceAtSquare(s) instanceof Knight) {
                                x = 2;
                            }
                            if (game.getPieceAtSquare(s) instanceof King) {
                                x = 3;
                            }
                            if (game.getPieceAtSquare(s) instanceof Queen) {
                                x = 4;
                            }
                            if (game.getPieceAtSquare(s) instanceof Pawn) {
                                x = 5;
                            }
                            if (game.getPieceAtSquare(s).getOwner() == Player.WHITE) {
                                x += 6;
                            }

                            Image im = img[x];
                            im = im.getScaledInstance(64, 64, Image.SCALE_SMOOTH);

                            if (game.getWhoseTurn() == Player.WHITE) {
                                g2d.drawImage(im, i * 64, j * 64, this);
                            } else {
                                g2d.drawImage(im, i * 64, (7 - j) * 64, this);
                            }
                        }
                    }
                }
                //highlight
                if (isMouseClickedOnce) {
//                    System.out.println("inside highlight");
//                    System.out.println(s1.getFile() + "" + s1.getRank());
//                    System.out.println(game.getPieceAtSquare(s1));
                    if (game.getWhoseTurn() == Player.BLACK) {
                        s1 = new Square(s1.getFile(), BoardRank.values()[7 - s1.getRank().getValue()]);
                    }
                    List<Square> validMoves = game.getAllValidMovesFromSquare(s1);
                    for (int i = 0; i < validMoves.size(); i++) {

                        int col = validMoves.get(i).getFile().getValue();
                        int row = validMoves.get(i).getRank().getValue();
                        g2d.setColor(Color.magenta);
                        g2d.setStroke(new BasicStroke(4));
                        if (game.getWhoseTurn() == Player.WHITE) {

                            g2d.drawRect(col * 64, (7 - row) * 64, 64, 64);
                        } else {
                            g2d.drawRect(col * 64, (row) * 64, 64, 64);
                        }
                    }
                }
                if (game.getGameStatus() == GameStatus.BLACK_WON) {
                    JOptionPane.showMessageDialog(frame, "Black WON");
                    frame.setVisible(false);

                }
                if (game.getGameStatus() == GameStatus.WHITE_WON) {
                    JOptionPane.showMessageDialog(frame, "White WON");
                    frame.setVisible(false);

                }
                if (game.getGameStatus() == GameStatus.STALEMATE) {
                    JOptionPane.showMessageDialog(frame, "Game is a Draw");
                    frame.setVisible(false);

                }
                if (game.getGameStatus() == GameStatus.INSUFFICIENT_MATERIAL) {
                    JOptionPane.showMessageDialog(frame, "game is draw due to insufficient material");
                    frame.setVisible(false);

                }

                if (game.getGameStatus() == GameStatus.WHITE_UNDER_CHECK) {
                    Square kingSquare = game.getKingSquare(Player.WHITE);
                    int col = kingSquare.getFile().getValue();
                    int row = kingSquare.getRank().getValue();
                    g2d.setColor(Color.red);
                    g2d.setStroke(new BasicStroke(4));
                    if (game.getWhoseTurn() == Player.WHITE) {

                        g2d.drawRect(col * 64, (7 - row) * 64, 64, 64);
                    } else {
                        g2d.drawRect(col * 64, (row) * 64, 64, 64);

                    }
                }
                if (game.getGameStatus() == GameStatus.BLACK_UNDER_CHECK) {
                    Square kingSquare = game.getKingSquare(Player.BLACK);
                    int col = kingSquare.getFile().getValue();
                    int row = kingSquare.getRank().getValue();
                    g2d.setColor(Color.red);
                    g2d.setStroke(new BasicStroke(4));
                    if (game.getWhoseTurn() == Player.WHITE) {

                        g2d.drawRect(col * 64, (7 - row) * 64, 64, 64);
                    } else {
                        g2d.drawRect(col * 64, (row) * 64, 64, 64);

                    }

                }
            }
        };

        return pn;

    }

    public static void main(String[] args) throws IOException {

        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);

        frame.add(b.returnPanel(frame));

        frame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e
            ) {

                if (clickCounter == 0) {
                    isMouseClickedOnce = true;
                    int x1 = (int) Math.floor(e.getX() / 64);
                    int y1 = (int) Math.floor(e.getY() / 64);
                    System.out.println(returnFile(x1) + " " + returnRank(y1));
                    if (game.getWhoseTurn() == Player.WHITE) {
                        s1 = new Square(returnFile(x1), returnRank(y1));
                    } else {
                        s1 = new Square(returnFile(x1), returnRank(7 - y1));
                    }
                    s1 = new Square(returnFile(x1), returnRank(y1));
                    clickCounter++;
                    flag = false;
                } else if (clickCounter == 1) {
                    isMouseClickedOnce = false;
                    int x2 = (int) Math.floor(e.getX() / 64);
                    int y2 = (int) Math.floor(e.getY() / 64);
                    System.out.println(returnFile(x2) + " " + returnRank(y2));
                    if (game.getWhoseTurn() == Player.WHITE) {
                        s2 = new Square(returnFile(x2), returnRank(y2));
                    } else {
                        s2 = new Square(returnFile(x2), returnRank(7 - y2));
                    }

                    flag = true;
                    clickCounter = 0;
                }
                if (flag) {

//                    System.out.println(s1.getFile() + " " + s1.getRank() + ";" + s2.getFile() + " " + s2.getRank());
                    if (game.getPieceAtSquare(s1) instanceof Pawn && (s2.getRank() == BoardRank.EIGHTH &&game.getWhoseTurn()==Player.WHITE|| s2.getRank() == BoardRank.FIRST&&game.getWhoseTurn()==Player.BLACK)) {

                        String[] options = {"Queen", "Rook", "Knight", "Bishop"};
                        int selection = JOptionPane.showOptionDialog(frame, "choose promotion", "you get a pawn promotion!!", 0, 3, null, options, options[0]);             
                        
                        if (selection == 0) {
                            promotion = PawnPromotion.Queen;
                        } else if (selection == 1) {
                            promotion = PawnPromotion.Rook;

                        } else if (selection == 2) {
                            promotion = PawnPromotion.Knight;

                        } else if (selection == 3) {
                            promotion = PawnPromotion.Bishop;

                        }

                    }
                    if (promotion == PawnPromotion.None ) {
                        move = new Move(s1, s2);
                    } else {
                        move = new Move(s1, s2, promotion);
                    }

                    if (game.makeMove(move)) {
//                        System.out.println("true");
                        WhitekingInCheck = game.getGameStatus() == GameStatus.WHITE_UNDER_CHECK;
                        BlackKingInCheck = game.getGameStatus() == GameStatus.BLACK_UNDER_CHECK;

                        frame.repaint();

                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid move");
//                        System.out.println("false");
                    }
                } else {
//                    m = new Memento(b);
//                    c.push(m);
                    frame.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e
            ) {
            }

            @Override
            public void mouseReleased(MouseEvent e
            ) {
            }

            @Override
            public void mouseEntered(MouseEvent e
            ) {
            }

            @Override
            public void mouseExited(MouseEvent e
            ) {
            }

        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
                    frame.dispose();
                }
                if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
                    game.undo();
                    frame.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        }); 
        frame.setFocusable(true);
        frame.setVisible(true);
    }
}
