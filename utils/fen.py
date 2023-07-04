import pieces


def create_board(fen_term: str) -> 'list[list[pieces.Piece | None]]':
    board = []

    line = []
    for character in fen_term:
        if character.isdigit():
            for _ in range(int(character)):
                line.append(None)

        if character.isalpha():
            piece = piece_factory(character)
            line.append(piece)

        if character == '/':
            board.append(line)
            line = []

    board.append(line)
    return board


def piece_color(character: str) -> str:
    if character.isupper():
        return 'w'
    elif character.islower():
        return 'b'
    else:
        raise ValueError()


def piece_factory(character: str) -> pieces.Piece:
    color = piece_color(character)
    piece = None

    piece_character = character.upper()
    match piece_character:
        case 'B':
            symbols = {'b': '\u2657', 'w': '\u265D'}
            piece = pieces.Bishop(color, symbols[color])
        case 'K':
            symbols = {'b': '\u2654', 'w': '\u265A'}
            piece = pieces.King(color, symbols[color])
        case 'N':
            symbols = {'b': '\u2658', 'w': '\u265E'}
            piece = pieces.Knight(color, symbols[color])
        case 'P':
            symbols = {'b': '\u2659', 'w': '\u265F'}
            piece = pieces.Pawn(color, symbols[color])
        case 'Q':
            symbols = {'b': '\u2655', 'w': '\u265B'}
            piece = pieces.Queen(color, symbols[color])
        case 'R':
            symbols = {'b': '\u2656', 'w': '\u265C'}
            piece = pieces.Rook(color, symbols[color])

    return piece
