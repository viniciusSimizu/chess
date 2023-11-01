from chess import PieceColorEnum
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class DefaultPiece(Piece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        self._color = color
        self.__board_size = board_size
        self._position = position
        self._movements = []
        for _ in range(board_size['height']):
            row = []
            for _ in range(board_size['width']):
                row.append(False)
            self._movements.append(row)

    def clean_movements(self) -> None:
        for y in range(len(self.movements)):
            for x in range(len(self.movements[y])):
                self.movements[y][x] = False

    def can_move_to(self, x: int, y: int) -> bool:
        return self.movements[y][x]

    @property
    def color(self) -> PieceColorEnum:
        return self._color

    @property
    def board_size(self) -> BoardSize:
        return self.__board_size

    @property
    def position(self) -> PiecePosition:
        return self._position

    @position.setter
    def position(self, value: PiecePosition) -> None:
        if self.__can_move:
            self._position = value
            self.__can_move = False

    @property
    def movements(self) -> list[list[bool]]:
        return self._movements

