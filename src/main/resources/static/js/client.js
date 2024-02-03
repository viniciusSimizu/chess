document.addEventListener("DOMContentLoaded", () => {
  /* const host = window.location.hostname;
  const path = "connections";
  const socket = new WebSocket(`ws://${host}:3000/${path}`);

  const movement = {
    from: { x: null, y: null },
    to: { x: null, y: null },
  };

  socket.onopen = () => {
    console.log("connected");
    htmx.trigger("#mainContent", "connected");
  };

  socket.onmessage = (e) => {
    if (e.data === "RELOAD") {
      htmx.trigger("#board", "reload");
    }
  }; */
});

htmx.onLoad(() => {
  const pieces = document.querySelectorAll(".piece");
  let selectedPiece;
  let moves = [];

  function selectHandle(e) {
    console.log("selecionou");

    const alreadySelected = this.classList.contains("active");
    unselectPiece();

    if (alreadySelected) {
      return;
    }

    selectPiece(this);
    removeMoves();
    addMoves(this.querySelectorAll(".move"));
  }

  function moveHandle(e) {
    console.log("moveu");
  }

  function addMoves(moveElements) {
    moves = moveElements;
    moves.forEach((move) => move.addEventListener("click", moveHandle));
  }

  function removeMoves() {
    moves.forEach((move) => move.removeEventListener("click", moveHandle));
    moves = [];
  }

  function selectPiece(piece) {
    selectedPiece = piece;
    selectedPiece.classList.add("active");
  }

  function unselectPiece() {
    if (selectedPiece) {
      selectedPiece.classList.remove("active");
      selectedPiece = undefined;
    }
  }

  // =====================================================================

  pieces.forEach((piece) => {
    piece.addEventListener("click", selectHandle);
  });

  window.addEventListener("click", (e) => {
    const target = e.target;
    if (
      target.classList.contains("piece") ||
      target.classList.contains("move")
    ) {
      return;
    }
    unselectPiece();
    removeMoves();
  });
});
