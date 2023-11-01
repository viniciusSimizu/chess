from chess import PieceColorEnum
from chess.pieces.default_piece import DefaultPiece
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class King(DefaultPiece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        super().__init__(color, board_size, position)

    def update_movements(self, board: list[list[Piece | None]]) -> None:
        self.movements.clear()

        relative_moves = [-1, 0, 1]

        for relative_y in relative_moves:
            for relative_x in relative_moves:
                if relative_y == 0 and relative_x == 0:
                    continue

                position: PiecePosition = {'x': self.position['x'] + relative_x,                                           'y':self.position['y'] + relative_y}
                if not 0 <= position['x'] < self.board_size['width'] or not 0 <= position['y'] < self.board_size['height']:
                    continue

                target = board[position['y']][position['x']]

                if target == None or self.color != target.color:
                    self.movements.append(position)

