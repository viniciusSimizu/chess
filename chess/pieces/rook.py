from chess import PieceColorEnum
from chess.pieces.default_piece import DefaultPiece
from chess.pieces.piece import BoardSize, Piece, PiecePosition
from chess.utils.math import Math


class Rook(DefaultPiece):
    def __init__(self, color: PieceColorEnum, board_size: BoardSize, position: PiecePosition) -> None:
        super().__init__(color, board_size, position)

    def update_movements(self, board: list[list[Piece | None]]) -> None:
        self.movements.clear()

        directions = [{'horizontal': 1, 'vertical': 0},
                      {'horizontal': -1, 'vertical': 0},
                      {'horizontal': 0, 'vertical': 1},
                      {'horizontal': 0, 'vertical': -1}]

        for direction in directions:
            horizontal_squares = direction['horizontal'] * (self.board_size['width'] - 1) 
            vertical_squares = direction['vertical'] * (self.board_size['height'] - 1)
            squares = Math.max(horizontal_squares, vertical_squares)

            square_direction = 0
            if direction['horizontal'] == -1 or direction['vertical'] == -1:
                square_direction = 1

            moving_squares = squares - (squares * square_direction + self.position['x'] * direction['horizontal'])

            for distance in range(moving_squares):
                x = self.position['x'] + (distance+1) * direction['horizontal']
                y = self.position['y'] + (distance+1) * direction['vertical']

                if not 0 <= x < self.board_size['width'] or not 0 <= y < self.board_size['height']:
                    break

                target_position: PiecePosition = {'x': x,
                                                  'y': y}
                target = board[y][x]

                if target is None:
                    self.movements.append(target_position)
                    continue

                if self.color != target.color:
                    self.movements.append(target_position)
                break
                

