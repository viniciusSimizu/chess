from chess import PieceColorEnum
from chess.fen.fen import Fen


class ChessBoard:
    def __init__(self, fen: str, turn: PieceColorEnum) -> None:
        self.fen = fen
        self.board = Fen()

