from chess import PieceColorEnum
from chess.game_setup.game import GameConfig
from chess.game_setup.game_default import GameDefault
from chess.pieces.bishop import Bishop
from chess.pieces.king import King
from chess.pieces.knight import Knight
from chess.pieces.pawn import Pawn
from chess.pieces.queen import Queen
from chess.pieces.rook import Rook


class GameClassic(GameDefault):
    def __init__(self) -> None:
        super().__init__('The classic experience of Chess')

    def get_config(self) -> GameConfig:
        return {'fen': 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR',
                'turn': PieceColorEnum.WHITE,
                'board_size': {'width': 8, 'height': 8},
                'piece_mapping': {'p': Pawn,
                                  'r': Rook,
                                  'n': Knight,
                                  'b': Bishop,
                                  'q': Queen,
                                  'k': King}}

