from chess import PieceColorEnum
from chess.pieces.default_piece import DefaultPiece
from chess.pieces.piece import BoardSize, Piece, PiecePosition


class Rook(DefaultPiece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        super().__init__(color, board_size, position)

        self.directions: list[PiecePosition] = [{'x': 0, 'y': -1},
                                                {'x': 1, 'y': 0},
                                                {'x': 0, 'y': 1},
                                                {'x': -1, 'y': 0}]

    def update_movements(self, board: list[list[Piece|None]]) -> None:
        self.clean_movements()

        for direction in self.directions:
            x = self.position['x'] 
            y = self.position['y']

            while True:
                x = x + direction['x']
                y = y + direction['y']

                if not 0 <= x < self.board_size['width'] or not 0 <= y < self.board_size['height']:
                    break

                target = board[y][x]

                if target == None:
                    self.movements[y][x] = True
                    continue

                if target.color != self.color:
                    self.movements[y][x] = True

                break

