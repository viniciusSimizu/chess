from chess import PieceColorEnum
from chess.pieces.king import King
from chess.pieces.piece import Piece, PiecePosition
from test.test_pieces.dummy_piece import DummyPiece
from test.unitTL import UnitTL

class TestKing:
    def test_update_movements(self):
        king = King(PieceColorEnum.BLACK, {'height': 3, 'width': 3}, {'x': 1, 'y': 1})
        board: list[list[Piece|None]] = [[None, None, None],
                                         [None, king, None],
                                         [None, None, None]]
        king.update_movements(board)

        given = king.movements
        expected: list[list[bool]] = [[True, True, True],
                                      [True, False, True],
                                      [True, True, True]]

        assert given == expected

    def test_update_movements_friend_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        king = King(PieceColorEnum.BLACK, {'height': 2, 'width': 2}, {'x': 1, 'y': 1})
        board: list[list[Piece|None]] = [[dummy, None],
                                         [None, king]]

        king.update_movements(board)

        given = king.movements
        expected: list[list[bool]] = [[False, True],
                                      [True, False]]

        assert given == expected

    def test_update_movements_enemy_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.WHITE)
        king = King(PieceColorEnum.BLACK, {'height': 2, 'width': 2}, {'x': 1, 'y': 1})
        board: list[list[Piece|None]] = [[dummy, None],
                                         [None, king]]

        king.update_movements(board)

        given = king.movements
        expected: list[list[bool]] = [[True, True],
                                      [True, False]]

        assert given == expected

