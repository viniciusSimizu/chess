from chess.board import Board
from chess.game_setup.game import GameConfig
from chess.game_setup.game_classic import GameClassic


class Start:
    board = Board()
    setups = [GameClassic()]

    def run(self) -> None:
        config = self.choose_setup()
        
        self.board.prepare(config)
        self.board.play()

    def choose_setup(self) -> GameConfig:
        for idx, setup in enumerate(self.setups):
            print(f'{idx+1} {setup.description}')

        while True:
            idx = input(f'Which experience would you like?: ')

            if idx.isdecimal():
                idx = int(idx)-1

                if 0 <= idx < len(self.setups):
                    return self.setups[idx].get_config()

Start().run()

