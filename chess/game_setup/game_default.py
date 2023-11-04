from chess.game_setup.game import Game

class GameDefault(Game):
    def __init__(self, description) -> None:
        self.__description = description

    @property
    def description(self) -> str:
        return self.__description

