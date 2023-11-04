from abc import ABC
from typing import TypeVar, TypedDict

from chess import PieceColorEnum
from chess.board import Board

class BoardSize(TypedDict):
    height: int
    width: int

class PiecePosition(TypedDict):
    x: int
    y: int

T = TypeVar('T', bound='Piece')

class Piece(ABC):
    def __init__(self, board: Board, color: PieceColorEnum, position: PiecePosition) -> None:
        self.board = board
        self.color = color
        self.position = position

        movements: list[list[bool]] = []
        for i in range(len(board.board)):
            row: list[bool] = []
            for _ in range(len(board.board[i])):
                row.append(False)
            movements.append(row)

        self.movements = movements

    def can_move_to(self, x: int, y: int) -> bool:
        ...

    def update_movements(self: T, board: list[list[T | None]]) -> None:
        pass

