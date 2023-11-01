
from chess import PieceColorEnum
from chess.pieces.default_piece import DefaultPiece
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class Queen(DefaultPiece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        super().__init__(color, board_size, position)

    def update_movements(self, board: list[list[Piece | None]]) -> None:
        self.movements.clear()

        directions: list[PiecePosition] = [{'x': 0, 'y': -1},
                                           {'x': 1, 'y': -1},
                                           {'x': 1, 'y': 0},
                                           {'x': 1, 'y': 1},
                                           {'x': 0, 'y': 1},
                                           {'x': -1, 'y': 1},
                                           {'x': -1, 'y': 0},
                                           {'x': -1, 'y': -1}]

        for direction in directions:
            position: PiecePosition = {'x': self.position['x'],
                                       'y': self.position['y']}

            while True:
                position = {'x': position['x'] + direction['x'],
                            'y': position['y'] + direction['y']}

                if not 0 <= position['x'] < self.board_size['width'] or not 0 <= position['y'] < self.board_size['height']:
                    break

                target = board[position['y']][position['x']]

                if target == None:
                    self.movements.append(position)
                    continue

                if target.color != self.color:
                    self.movements.append(position)

                break

