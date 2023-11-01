from chess import PieceColorEnum
from chess.pieces.piece import Piece
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
        expected: list[list[bool]] = [[True, False, True, False, True],
                                      [False, True, True, True, False],
                                      [True, True, False, True, True],
                                      [False, True, True, True, False],
                                      [True, False, True, False, True]]

        assert given == expected

    def test_update_movements_friend_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        queen = Queen(PieceColorEnum.BLACK, {'height': 3, 'width': 3}, {'x': 2, 'y': 2})
        board: list[list[Piece|None]] = [[None, None, None],
                                         [None, dummy, None],
                                         [None, dummy, queen]]

        queen.update_movements(board)

        given = queen.movements
        expected: list[list[bool]] = [[False, False, True],
                                      [False, False, True],
                                      [False, False, False]]

        assert given == expected

    def test_update_movements_enemy_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.WHITE)
        queen = Queen(PieceColorEnum.BLACK, {'height': 3, 'width': 3}, {'x': 2, 'y': 2})
        board: list[list[Piece|None]] = [[None, None, None],
                                         [None, dummy, None],
                                         [None, dummy, queen]]

        queen.update_movements(board)

        given = queen.movements
        expected: list[list[bool]] = [[False, False, True],
                                      [False, True, True],
                                      [False, True, False]]

        assert given == expected

