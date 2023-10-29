from chess import PieceColorEnum
from chess.pieces.knight import Knight
from chess.pieces.piece import Piece, PiecePosition
from test.test_pieces.dummy_piece import DummyPiece
from test.unitTL import UnitTL

class TestKnight:
    def test_update_movements(self):
        knight = Knight(PieceColorEnum.BLACK, {'height': 5, 'width': 5}, {'x': 2, 'y': 2})
        board: list[list[Piece | None]] = [[None, None, None, None, None],
                                           [None, None, None, None, None],
                                           [None, None, knight, None, None],
                                           [None, None, None, None, None],
                                           [None, None, None, None, None]]
        knight.update_movements(board)

        expected: list[PiecePosition] = [{'x': 1, 'y': 0},
                                         {'x': 3, 'y': 0},
                                         {'x': 4, 'y': 1},
                                         {'x': 4, 'y': 3},
                                         {'x': 3, 'y': 4},
                                         {'x': 1, 'y': 4},
                                         {'x': 0, 'y': 1},
                                         {'x': 0, 'y': 3}]
        given = knight.movements

        if len(expected) != len(given):
            assert False

        for movement in given:
            found = False
            for expect_movement in expected:
                if movement == expect_movement:
                    found = True
                    break

            if not found:
                assert False

        assert True

    def test_update_movements_enemy_space(self):
        knight = Knight(PieceColorEnum.BLACK, {'height': 3, 'width': 2}, {'x': 0, 'y': 0})
        dummy = DummyPiece(color=PieceColorEnum.WHITE)
        board: list[list[Piece | None]] = [[knight, None],
                                           [None, None],
                                           [None, dummy]]
        knight.update_movements(board)

        expected: list[PiecePosition] = [{'x': 1, 'y': 2}]
        given = knight.movements

        assert UnitTL.assert_list(given, expected)

    def test_update_movements_friend_space(self):
        knight = Knight(PieceColorEnum.BLACK, {'height': 3, 'width': 2}, {'x': 0, 'y': 0})
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        board: list[list[Piece | None]] = [[knight, None],
                                           [None, None],
                                           [None, dummy]]
        knight.update_movements(board)

        expected: list[PiecePosition] = []
        given = knight.movements

        assert UnitTL.assert_list(given, expected)

    def test_update_movements_out_of_board(self):
        knight = Knight(PieceColorEnum.BLACK, {'height': 1, 'width': 1}, {'x': 0, 'y': 0})
        board: list[list[Piece | None]] = [[knight]]
        knight.update_movements(board)

        expected: list[PiecePosition] = []
        given = knight.movements

        assert given == expected

