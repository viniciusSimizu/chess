package com.vini.app.setups;

import java.util.HashMap;

import com.vini.app.fen.FenPieceEnum;
import com.vini.app.pieces.Bishop;
import com.vini.app.pieces.IPiece;
import com.vini.app.pieces.King;
import com.vini.app.pieces.Knight;
import com.vini.app.pieces.Pawn;
import com.vini.app.pieces.Queen;
import com.vini.app.pieces.Rook;
import com.vini.app.types.ColorEnum;

public class SetupClassic extends Setup {
	public SetupClassic() {
		super("The classic experience of chess");
	};

	@Override
	public SetupData config() {
		return new SetupData()
			.setFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
			.setTurn(ColorEnum.WHITE)
			.setPieceMap(new HashMap<String, Class<? extends IPiece>>() {{
				put(FenPieceEnum.PAWN.toString(), Pawn.class);
				put(FenPieceEnum.ROOK.toString(), Rook.class);
				put(FenPieceEnum.KNIGHT.toString(), Knight.class);
				put(FenPieceEnum.BISHOP.toString(), Bishop.class);
				put(FenPieceEnum.QUEEN.toString(), Queen.class);
				put(FenPieceEnum.KING.toString(), King.class);
			}});
	}
}
