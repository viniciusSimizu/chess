from abc import ABC, abstractmethod
from typing import TypedDict
from chess import PieceColorEnum
from chess.fen.fen import PieceMapping

from chess.pieces.piece import BoardSize

class GameConfig(TypedDict):
    fen: str
    turn: PieceColorEnum
    board_size: BoardSize
    piece_mapping: PieceMapping

class Game(ABC):
    @property
    @abstractmethod
    def description(self) -> str:
        pass

    @abstractmethod
    def get_config(self) -> GameConfig:
        pass

