from abc import ABC, abstractmethod
from typing import TypeVar, TypedDict

from chess import PieceColorEnum

class BoardSize(TypedDict):
    height: int
    width: int

class PiecePosition(TypedDict):
    x: int
    y: int

T = TypeVar('T', bound='Piece')

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
    def movements(self) -> list[list[bool]]:
        pass

    @abstractmethod
    def can_move_to(self, x: int, y: int) -> bool:
        pass

    @abstractmethod
    def update_movements(self: T, board: list[list[T|None]]) -> None:
        pass

