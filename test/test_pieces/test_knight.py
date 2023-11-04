from chess import PieceColorEnum
from chess.pieces.knight import Knight
from chess.pieces.piece import Piece, PiecePosition
from test.test_pieces.dummy_piece import DummyPiece
from test.unitTL import UnitTL

class TestKnight:
    def test_update_movements(self):
        knight = Knight(PieceColorEnum.BLACK, {'height': 5, 'width': 5}, {'x': 2, 'y': 2})
        board: list[list[Piece|None]] = [[None, None, None, None, None],
                                           [None, None, None, None, None],
                                           [None, None, knight, None, None],
                                           [None, None, None, None, None],
                                           [None, None, None, None, None]]
        knight.update_movements(board)

        expected: list[list[bool]] = [[False, True, False, True, False],
                                      [True, False, False, False, True],
                                      [False, False, False, False, False],
                                      [True, False, False, False, True],
                                      [False, True, False, True, False]]
        given = knight.movements

        assert given == expected

    def test_update_movements_enemy_space(self):
        knight = Knight(PieceColorEnum.BLACK, {'height': 3, 'width': 2}, {'x': 0, 'y': 0})
        dummy = DummyPiece(color=PieceColorEnum.WHITE)
        board: list[list[Piece|None]] = [[knight, None],
                                           [None, None],
                                           [None, dummy]]
        knight.update_movements(board)

        expected: list[list[bool]] = [[False, False],
                                      [False, False],
                                      [False, True]]
        given = knight.movements

        assert given == expected

    def test_update_movements_friend_space(self):
        knight = Knight(PieceColorEnum.BLACK, {'height': 3, 'width': 2}, {'x': 0, 'y': 0})
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        board: list[list[Piece | None]] = [[knight, None],
                                           [None, None],
                                           [None, dummy]]
        knight.update_movements(board)

        expected: list[list[bool]] = [[False, False],
                                      [False, False],
                                      [False, False]]
        given = knight.movements

        assert given == expected

