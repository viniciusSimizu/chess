from chess import PieceColorEnum
from chess.pieces.piece import Piece
from chess.pieces.rook import Rook
from test.test_pieces.dummy_piece import DummyPiece

class TestRook:
    def test_update_movements(self):
        rook = Rook(PieceColorEnum.BLACK, {'height': 5, 'width': 5}, {'x': 2, 'y': 2})
        board: list[list[Piece | None]] = [[None, None, None, None, None],
                                           [None, None, None, None, None],
                                           [None, None, rook, None, None],
                                           [None, None, None, None, None],
                                           [None, None, None, None, None]]
        rook.update_movements(board)

        expected: list[list[bool]] = [[False, False, True, False, False],
                                      [False, False, True, False, False],
                                      [True, True, False, True, True],
                                      [False, False, True, False, False],
                                      [False, False, True, False, False]]
        given = rook.movements

        assert given == expected

    def test_update_movements_friend_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        rook = Rook(PieceColorEnum.BLACK, {'height': 4, 'width': 1}, {'x': 0, 'y': 2})
        board: list[list[Piece | None]] = [[None],
                                           [dummy],
                                           [rook],
                                           [None]]
        rook.update_movements(board)

        expected: list[list[bool]] = [[False],
                                      [False],
                                      [False],
                                      [True]]
        given = rook.movements

        assert given == expected

    def test_update_movements_enemy_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.WHITE)
        rook = Rook(PieceColorEnum.BLACK, {'height': 1, 'width': 4}, {'x': 1, 'y': 0})
        board: list[list[Piece | None]] = [[None, rook, dummy, None]]
        rook.update_movements(board)

        expected: list[list[bool]] = [[True, False, True, False]]
        given = rook.movements

        assert given == expected

