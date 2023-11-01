
from chess import PieceColorEnum
from chess.pieces.default_piece import DefaultPiece
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class Knight(DefaultPiece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        super().__init__(color, board_size, position)

    def update_movements(self, board: list[list[Piece | None]]) -> None:
        self.movements.clear()

        FORWARD_DISTANCE = 2
        SIDE_STEP_DISTANCE = 1

        directions = [{'horizontal': 1, 'vertical': 0},
                      {'horizontal': 0, 'vertical': 1}]

        for direction in directions:
            for forward_weight in [1, -1]:
                for sidestep_weight in [1, -1]:
                    horizontal = direction['horizontal'] * forward_weight * FORWARD_DISTANCE
                    vertical = direction['vertical'] * forward_weight * FORWARD_DISTANCE

                    horizontal_step = direction['vertical'] * sidestep_weight * SIDE_STEP_DISTANCE
                    vertical_step = direction['horizontal'] * sidestep_weight * SIDE_STEP_DISTANCE

                    position: PiecePosition = {'x': self.position['x'] + horizontal + horizontal_step,
                                               'y':self.position['y'] + vertical + vertical_step}

                    if not 0 <= position['x'] < self.board_size['width'] or not 0 <= position['y'] < self.board_size['height']:
                        continue

                    target = board[position['y']][position['x']]

                    if not target:
                        self.movements.append(position)
                        continue

                    is_friend = self.color == target.color
                    if not is_friend:
                        self.movements.append(position)

