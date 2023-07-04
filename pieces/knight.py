from .piece import Piece


class Knight(Piece):
    def __init__(self, color: str, symbol_unicode: str) -> None:
        super().__init__(color, symbol_unicode)
        