from chess import PieceColorEnum
from chess.pieces.pawn import Pawn
from chess.pieces.piece import Piece
from test.test_pieces.dummy_piece import DummyPiece

class TestPawn:
    dummy = DummyPiece()

    def test_update_movements_empyt_space_case_one(self):
        pawn = Pawn(PieceColorEnum.BLACK, {'height': 2, 'width': 1}, {'x': 0, 'y': 0})
        board: list[list[Piece|None]] = [[pawn],
                                         [None]]
        pawn.update_movements(board)

        expected: list[list[bool]] = [[False],
                                      [True]]
        given = pawn.movements

        assert given == expected

    def test_update_movements_empyt_space_case_two(self):
        pawn = Pawn(PieceColorEnum.WHITE, {'height': 2, 'width': 1}, {'x': 0, 'y': 1})
        board: list[list[Piece|None]] = [[None],
                                         [pawn]]
        pawn.update_movements(board)

        expected: list[list[bool]] = [[True],
                                      [False]]
        given = pawn.movements

        assert given == expected

    def test_update_movements_non_empty_space_case_one(self):
        pawn = Pawn(PieceColorEnum.BLACK, {'height': 2, 'width': 1}, {'x': 0, 'y': 0})
        board: list[list[Piece|None]] = [[pawn],
                                         [self.dummy]]
        pawn.update_movements(board)

        expected: list[list[bool]] = [[False],
                                      [False]]
        given = pawn.movements

        assert given == expected

    def test_update_movements_non_empty_space_case_two(self):
        pawn = Pawn(PieceColorEnum.WHITE, {'height': 2, 'width': 1}, {'x': 0, 'y': 1})
        board: list[list[Piece|None]] = [[self.dummy],
                                         [pawn]]
        pawn.update_movements(board)

        expected: list[list[bool]] = [[False],
                                      [False]]
        given = pawn.movements

        assert given == expected

    def test_update_movements_out_of_board_case_one(self):
        pawn = Pawn(PieceColorEnum.BLACK, {'height': 2, 'width': 1}, {'x': 0, 'y': 1})
        board: list[list[Piece|None]] = [[None],
                                         [pawn]]
        pawn.update_movements(board)

        expected: list[list[bool]] = [[False],
                                      [False]]
        given = pawn.movements

        assert given == expected

    def test_update_movements_out_of_board_case_two(self):
        pawn = Pawn(PieceColorEnum.WHITE, {'height': 2, 'width': 1}, {'x': 0, 'y': 0})
        board: list[list[Piece | None]] = [[pawn],
                                           [None]]
        pawn.update_movements(board)

        expected: list[list[bool]] = [[False],
                                      [False]]
        given = pawn.movements

        assert given == expected

