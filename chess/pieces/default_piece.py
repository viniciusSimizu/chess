from chess import PieceColorEnum
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class DefaultPiece(Piece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        self.color = super().color
        self.board_size = super().board_size
        self.position = super().position
        self.movements: list[list[bool]] = super().movements

    def mount_movements(self) -> None:
        self.movements = []
        for _ in range(self.board_size['height']):
            row: list[bool] = []
            for x in range(self.board_size['width']):
                row.append(False)
            self.movements.append(row)


    def clean_movements(self) -> None:
        for y in range(len(self.movements)):
            for x in range(len(self.movements[y])):
                self.movements[y][x] = False

    def can_move_to(self, x: int, y: int) -> bool:
        return self.movements[y][x]

