from chess import PieceColorEnum
from chess.pieces.default_piece import DefaultPiece
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class Pawn(DefaultPiece):
    __vertical_direction_multiplier = 1

    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        if color == PieceColorEnum.WHITE:
            self.__vertical_direction_multiplier = -1

        super().__init__(color, board_size, position)

    def update_movements(self, board: list[list[Piece | None]]) -> None:
        self.movements.clear()

        to_y = self.position['y'] + 1 * self.__vertical_direction_multiplier

        if 0 <= to_y < self.board_size['height'] and board[to_y][self.position['x']] == None:
            self.movements.append({'x': self.position['x'],
                                   'y': to_y})
        
