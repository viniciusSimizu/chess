from chess import PieceColorEnum
from chess.pieces.piece import Piece, PiecePosition
from chess.pieces.queen import Queen
from test.test_pieces.dummy_piece import DummyPiece
from test.unitTL import UnitTL

class TestQueen:
    def test_update_movements(self):
        queen = Queen(PieceColorEnum.BLACK, {'height': 5, 'width': 5}, {'x': 2, 'y': 2})
        board: list[list[Piece|None]] = [[None, None, None, None, None],
                                         [None, None, None, None, None],
                                         [None, None, queen, None, None],
                                         [None, None, None, None, None],
                                         [None, None, None, None, None]]
        queen.update_movements(board)

        given = queen.movements
        expected: list[PiecePosition] = [{'x': 2, 'y': 0},
                                         {'x': 2, 'y': 1},
                                         {'x': 4, 'y': 0},
                                         {'x': 3, 'y': 1},
                                         {'x': 4, 'y': 2},
                                         {'x': 3, 'y': 2},
                                         {'x': 4, 'y': 4},
                                         {'x': 3, 'y': 3},
                                         {'x': 2, 'y': 3},
                                         {'x': 2, 'y': 4},
                                         {'x': 1, 'y': 3},
                                         {'x': 0, 'y': 4},
                                         {'x': 1, 'y': 2},
                                         {'x': 0, 'y': 2},
                                         {'x': 0, 'y': 0},
                                         {'x': 1, 'y': 1}]

        assert UnitTL.assert_list(given, expected)

    def test_update_movements_friend_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        queen = Queen(PieceColorEnum.BLACK, {'height': 2, 'width': 2}, {'x': 1, 'y': 1})
        board: list[list[Piece|None]] = [[dummy, None],
                                         [None, queen]]

        queen.update_movements(board)

        given = queen.movements
        expected: list[PiecePosition] = [{'x': 1, 'y': 0},
                                         {'x': 0, 'y': 1}]

        assert UnitTL.assert_list(given, expected)

    def test_update_movements_enemy_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.WHITE)
        queen = Queen(PieceColorEnum.BLACK, {'height': 2, 'width': 2}, {'x': 1, 'y': 1})
        board: list[list[Piece|None]] = [[dummy, None],
                                         [None, queen]]

        queen.update_movements(board)

        given = queen.movements
        expected: list[PiecePosition] = [{'x': 0, 'y': 0},
                                         {'x': 1, 'y': 0},
                                         {'x': 0, 'y': 1}]

        assert UnitTL.assert_list(given, expected)

