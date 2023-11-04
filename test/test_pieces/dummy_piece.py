from chess import PieceColorEnum
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class DummyPiece(Piece):
    def __init__(self, color = PieceColorEnum.BLACK, board_size = {'height': 0, 'width': 0}, position = {'x': 0, 'y': 0}) -> None:
        self._color = color
    def _is_valid_move(self, x: int, y: int) -> bool:
        ...
    @property
    def color(self) -> PieceColorEnum:
        return self._color
    @property
    def board_size(self) -> BoardSize:
        ...
    @property
    def position(self) -> PiecePosition:
        ...
    @position.setter
    def position(self, value: PiecePosition) -> None:
        ...
    @property
    def movements(self) -> list[PiecePosition]:
        ...
    def can_move_to(self, x: int, y: int) -> None:
        ...
    def update_movements(self, board: list[list[Piece]]) -> None:
        ...
