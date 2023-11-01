from chess import PieceColorEnum
from chess.pieces.bishop import Bishop
from chess.pieces.piece import Piece
from test.test_pieces.dummy_piece import DummyPiece

class TestBishop:
    def test_update_movements(self):
        bishop = Bishop(PieceColorEnum.BLACK, {'height': 5, 'width': 5}, {'x': 2, 'y': 2})
        board: list[list[Piece | None]] = [[None, None, None, None, None],
                                           [None, None, None, None, None],
                                           [None, None, bishop, None, None],
                                           [None, None, None, None, None],
                                           [None, None, None, None, None]]
        bishop.update_movements(board)

        expected: list[list[bool]] = [[True, False, False, False, True],
                                      [False, True, False, True, False],
                                      [False, False, False, False, False],
                                      [False, True, False, True, False],
                                      [True, False, False, False, True]]
        given = bishop.movements

        assert given == expected

    def test_update_movements_friend_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.BLACK)
        bishop = Bishop(PieceColorEnum.BLACK, {'height': 3, 'width': 3}, {'x': 0, 'y': 2})
        board: list[list[Piece | None]] = [[None, None, None],
                                           [None, dummy, None],
                                           [bishop, None, None]]

        bishop.update_movements(board)

        expected: list[list[bool]] = [[False, False, False],
                                      [False, False, False],
                                      [False, False, False]]
        given = bishop.movements

        assert given == expected

    def test_update_movements_enemy_collision(self):
        dummy = DummyPiece(color=PieceColorEnum.WHITE)
        bishop = Bishop(PieceColorEnum.BLACK, {'height': 3, 'width': 3}, {'x': 0, 'y': 2})
        board: list[list[Piece | None]] = [[None, None, None],
                                           [None, dummy, None],
                                           [bishop, None, None]]

        bishop.update_movements(board)

        expected: list[list[bool]] = [[False, False, False],
                                      [False, True, False],
                                      [False, False, False]]

        given = bishop.movements

        assert given == expected

