from typing import Literal
from enum import Enum

class ColorEnum(Enum):
    BLACK = 'b'
    WHITE = 'w'

class FenPiece:
    def __init__(self, color: ColorEnum) -> None:
        self.color = color


class PieceMapping:
    def __init__(self, pawn: type[FenPiece], bishop: type[FenPiece], knight: type[FenPiece], rook: type[FenPiece], queen: type[FenPiece], king: type[FenPiece]) -> None:
        self.pawn = pawn
        self.bishop = bishop
        self.knight = knight
        self.rook = rook
        self.queen = queen
        self.king = king

    def __getitem__(self, search: str):
        return self.__annotations__[search]

PieceKey = Literal['pawn', 'bishop', 'knight', 'rook', 'queen', 'king']

class Fen:
    piece_mapping: PieceMapping

    def __init__(self, piece_mapping: PieceMapping) -> None:
        self.piece_mapping = piece_mapping

    def build(self, fen: str) -> list[list[FenPiece | None]]:
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
            
            piece = self.piece_factory(char)
            row.append(piece)

        board.append(row)

        return board


    def piece_factory(self, char: str) -> FenPiece:
        pieceKey: PieceKey | None = None
        color = ColorEnum.WHITE if char.isupper() else ColorEnum.BLACK
        
        match char.upper():
            case 'P':
                pieceKey = 'pawn'
            case 'B':
                pieceKey = 'bishop'
            case 'N':
                pieceKey = 'knight'
            case 'R':
                pieceKey = 'rook'
            case 'Q':
                pieceKey = 'queen'
            case 'K':
                pieceKey = 'king'
            case _:
                raise ValueError('FEN Piece - invalid char', char)
        
        return getattr(self.piece_mapping, pieceKey)(color=color)                

