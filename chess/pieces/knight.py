
from chess import PieceColorEnum
from chess.pieces.default_piece import DefaultPiece
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class Knight(DefaultPiece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        super().__init__(color, board_size, position)

    def update_movements(self, board: list[list[Piece|None]]) -> None:
        self.clean_movements()

        FORWARD_DISTANCE = 2
        SIDE_STEP_DISTANCE = 1

        directions: list[PiecePosition] = [{'x': 0, 'y': -1},
                                           {'x': 1, 'y': 0},
                                           {'x': 0, 'y': 1},
                                           {'x': -1, 'y': 0}]

        for direction in directions:
            for sidestep_weight in [1, -1]:
                x = self.position['x'] + direction['x'] * FORWARD_DISTANCE + direction['y'] * SIDE_STEP_DISTANCE * sidestep_weight
                y = self.position['y'] + direction['y'] * FORWARD_DISTANCE + direction['x'] * SIDE_STEP_DISTANCE * sidestep_weight

                if not 0 <= x < self.board_size['width'] or not 0 <= y < self.board_size['height']:
                    continue

                target = board[y][x]

                if target == None:
                    self.movements[y][x] = True
                    continue

                if target.color != self.color:
                    self.movements[y][x] = True

