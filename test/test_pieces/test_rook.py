from chess import PieceColorEnum
from chess.pieces.piece import Piece, PiecePosition
from chess.pieces.rook import Rook
from test.test_pieces.dummy_piece import DummyPiece
from test.unitTL import UnitTL

class TestRook:
    def test_update_movements(self):
        rook = Rook(PieceColorEnum.BLACK, {'height': 5, 'width': 5}, {'x': 2, 'y': 2})
        board: list[list[Piece | None]] = [[None, None, None, None, None],
                                           [None, None, None, None, None],
                                           [None, None, rook, None, None],
                                           [None, None, None, None, None],
                                           [None, None, None, None, None]]
        rook.update_movements(board)

        expected: list[PiecePosition] = [{'x': 2, 'y': 0},
                                         {'x': 2, 'y': 1},
                                         {'x': 3, 'y': 2},
                                         {'x': 4, 'y': 2},
                                         {'x': 2, 'y': 3},
                                         {'x': 2, 'y': 4},
                                         {'x': 0, 'y': 2},
                                         {'x': 1, 'y': 2}]
        given = rook.movements

        assert UnitTL.assert_list(given, expected)

    def test_update_movements_friend_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        rook = Rook(PieceColorEnum.BLACK, {'height': 3, 'width': 1}, {'x': 0, 'y': 1})
        board: list[list[Piece | None]] = [[dummy],
                                           [rook],
                                           [None]]
        rook.update_movements(board)

        expected: list[PiecePosition] = [{'x': 0, 'y': 2}]
        given = rook.movements

        assert UnitTL.assert_list(given, expected)

    def test_update_movements_enemy_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        rook = Rook(PieceColorEnum.WHITE, {'height': 1, 'width': 3}, {'x': 1, 'y': 0})
        board: list[list[Piece | None]] = [[None, rook, dummy]]
        rook.update_movements(board)

        expected: list[PiecePosition] = [{'x': 0, 'y': 0},
                                         {'x': 2, 'y': 0}]
        given = rook.movements

        assert UnitTL.assert_list(given, expected)

