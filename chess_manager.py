import utils.fen as FEN
from chess_board import ChessBoard
import pieces


class ChessManager:
    def __init__(self) -> None:
        pass

    def start(self) -> None:
        fen_syntax = 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w'
        (pieces_positions, first_play) = fen_syntax.split(' ')

        board_fen = FEN.create_board(pieces_positions)
        chess_board = ChessBoard(board_fen, first_play)
        
        chess_board.visualize_board()


if __name__ == '__main__':
    ChessManager().start()
