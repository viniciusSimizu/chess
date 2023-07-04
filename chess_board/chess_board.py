import pieces
from typing import Literal
import string


class ChessBoard:
    def __init__(self, chess_board: 'list[list[pieces.Piece | None]]', next_move: Literal['w', 'b']) -> None:
        self.chess_board = chess_board
        self.next_move = next_move

    def visualize_board(self) -> None:
        print('-' * len(self.chess_board) * 3)
        for i, line in enumerate(self.chess_board):
            print(i+1, end='')
            print('|', end='')
            for j, square in enumerate(line):
                if issubclass(type(square), pieces.Piece):
                    print(square.symbol_unicode, end='')
                else:
                    print(' ', end='')

                if j == len(line) - 1:
                    continue

                print(' |', end='')

            print(' |')

            if i == len(self.chess_board) - 1:
                continue

            print('  ', end='')
            print('-' * len(self.chess_board) * 3)

        print(' ', end='')
        print('-' * len(self.chess_board) * 3)
        print('  ', end='')
        print('  '.join(string.ascii_uppercase[:len(self.chess_board[0])]))
