from typing import Any, cast

from fen.fen import ColorEnum, Fen, FenPiece, PieceMapping

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
                [Rook(color=ColorEnum.BLACK), Knight(color=ColorEnum.BLACK), Bishop(color=ColorEnum.BLACK), Queen(color=ColorEnum.BLACK)],
                [Pawn(color=ColorEnum.BLACK), Pawn(color=ColorEnum.BLACK), Pawn(color=ColorEnum.BLACK), Pawn(color=ColorEnum.BLACK)],
                [None, None, None, None],
                [None, None, None, None],
                [Pawn(color=ColorEnum.WHITE), Pawn(color=ColorEnum.WHITE), Pawn(color=ColorEnum.WHITE), Pawn(color=ColorEnum.WHITE)],
                [King(color=ColorEnum.WHITE), Bishop(color=ColorEnum.WHITE), Knight(color=ColorEnum.WHITE), Rook(color=ColorEnum.WHITE)]
                ]

        assert given_result == expected_result

