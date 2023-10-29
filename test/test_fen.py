from typing import Any

from chess import PieceColorEnum
from chess.fen.fen import Fen, FenPiece, PieceMapping

class Piece(FenPiece):
    def __eq__(self, other: Any) -> bool:
        if isinstance(other, self.__class__) and self.color == other.color:
            return True
        return False


class Rook(Piece):
    pass


class Pawn(Piece):
    pass


class Bishop(Piece):
    pass


class Knight(Piece):
    pass


class King(Piece):
    pass


class Queen(Piece):
    pass


class TestFen:
    piece_mapping = PieceMapping(pawn=Pawn,
                                 bishop=Bishop,
                                 knight=Knight,
                                 rook=Rook,
                                 queen=Queen,
                                 king=King) 

    def test_build(self):
        fen_notation = 'rnbq/pppp/4/4/PPPP/KBNR'
        fen_object = Fen(self.piece_mapping)

        given_result = fen_object.build(fen_notation)
        expected_result: list[list[Piece | None]] = [
                [Rook(color=PieceColorEnum.BLACK), Knight(color=PieceColorEnum.BLACK), Bishop(color=PieceColorEnum.BLACK), Queen(color=PieceColorEnum.BLACK)],
                [Pawn(color=PieceColorEnum.BLACK), Pawn(color=PieceColorEnum.BLACK), Pawn(color=PieceColorEnum.BLACK), Pawn(color=PieceColorEnum.BLACK)],
                [None, None, None, None],
                [None, None, None, None],
                [Pawn(color=PieceColorEnum.WHITE), Pawn(color=PieceColorEnum.WHITE), Pawn(color=PieceColorEnum.WHITE), Pawn(color=PieceColorEnum.WHITE)],
                [King(color=PieceColorEnum.WHITE), Bishop(color=PieceColorEnum.WHITE), Knight(color=PieceColorEnum.WHITE), Rook(color=PieceColorEnum.WHITE)]
                ]

        assert given_result == expected_result

