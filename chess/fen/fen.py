from abc import ABC
from enum import Enum
from typing import Literal, TypedDict, cast, get_args

from chess import PieceColorEnum

class PieceEnum(Enum):
    PAWN = 'p'
    ROOK = 'r'
    KNIGHT = 'n'
    BISHOP = 'b'
    QUEEN = 'q'
    KING = 'k'


class FenPiece(ABC):
    def __init__(self) -> None:
        self.color: PieceColorEnum | None = None
        self.fen: str | None = None


class PieceMapping(TypedDict):
    p: type[FenPiece]
    r: type[FenPiece]
    n: type[FenPiece]
    b: type[FenPiece]
    q: type[FenPiece]
    k: type[FenPiece]


PieceKeys = Literal['p', 'r', 'n', 'b', 'q', 'k']


class Fen:
    @staticmethod
    def build(fen: str, piece_mapping: PieceMapping) -> list[list[FenPiece | None]]:
        board: list[list[FenPiece | None]] = []
        row: list[FenPiece | None] = []

        for char in fen:
            if char.isdecimal():
                for _ in range(int(char)):
                    row.append(None)
                continue

            if char == '/':
                board.append(row)
                row = []
                continue
            
            piece = Fen.piece_factory(char, piece_mapping)
            row.append(piece)

        board.append(row)
        return board

    @staticmethod
    def which_color(char: str) -> PieceColorEnum:
        color = PieceColorEnum.WHITE if char.isupper() else PieceColorEnum.BLACK
        return color


    @staticmethod
    def piece_factory(char: str, piece_mapping: PieceMapping) -> FenPiece:
        piece_key = char.lower()

        if piece_key not in get_args(PieceKeys):
            raise ValueError('FEN Piece - invalid char', char)
        piece_key = cast(PieceKeys, piece_key)
        
        piece = piece_mapping[piece_key]()
        piece.color = Fen.which_color(char)
        piece.fen = char

        return piece

