import re
from chess import PieceColorEnum
from chess.fen.fen import Fen
from chess.game_setup.game import GameConfig
from chess.pieces.piece import BoardSize, Piece


class Board:
    board: list[list[Piece | None]]
    board_size = BoardSize
    turn: PieceColorEnum

    def prepare(self, config: GameConfig) -> None:
        self.board = Fen.build(config['fen'], config['piece_mapping'])

        self.board_size = config['board_size']
        self.turn = config['turn']

    def play(self) -> None:
        while True:
            move = input('Move (x,y;x,y): ')
            
            positions = re.findall(r'\d+', move)

            from_x = int(positions[0])
            from_y = int(positions[1])
            to_x = int(positions[2])
            to_y = int(positions[3])

            from_target = self.board[from_y][from_x]

            if from_target == None:
                continue

            if not from_target.can_move_to(to_x, to_y):
                continue

            self.board[to_y][to_x] = self.board[from_y][from_x]
            self.board[from_y][from_x] = None

