from abc import ABC, abstractmethod
from typing import TypedDict

from chess import PieceColorEnum

class BoardSize(TypedDict):
    height: int
    width: int

class PiecePosition(TypedDict):
    x: int
    y: int

class Piece(ABC):
    @property
    @abstractmethod
    def color(self) -> PieceColorEnum:
        pass

    @property
    @abstractmethod
    def board_size(self) -> BoardSize:
        pass

    @property
    @abstractmethod
    def position(self) -> PiecePosition:
        pass

    @position.setter
    @abstractmethod
    def position(self, value: PiecePosition) -> None:
        pass

    @property
    @abstractmethod
    def movements(self) -> list[PiecePosition]:
        pass

    @abstractmethod
    def can_move_to(self, x: int, y: int) -> bool:
        pass

    @abstractmethod
    def update_movements(self, board: list[list['Piece']]) -> None:
        pass

