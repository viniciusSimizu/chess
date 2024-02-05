const host = window.location.hostname;
const resourcePath = "connections";

const messageType = {
  CONNECTION: "CONNECTION",
  RELOAD: "RELOAD",
};

const movement = {
  from: { x: null, y: null },
  to: { x: null, y: null },
};
let selectedPiece;
let pieces = [];
let moves = [];

document.addEventListener("DOMContentLoaded", () => {
  const socket = new WebSocket(`ws://${host}:3000/${resourcePath}`);
  socket.onopen = () => {
    console.log("connected");
  };

  socket.onmessage = (e) => {
    const { type, body } = JSON.parse(e.data);

    if (type === messageType.CONNECTION) {
      document.querySelector("#mainContent").outerHTML = body;
      load();
    }

    if (type === messageType.RELOAD) {
      document.querySelector("#board").outerHTML = body;
      removeMoves();
      load();
    }
  };

  window.addEventListener("click", (e) => {
    if (
      e.target.classList.contains("piece") ||
      e.target.classList.contains("move")
    ) {
      return;
    }
    removeMoves();
    unselectPiece();
  });

  function load() {
    removeMoves();
    unselectPiece();

    pieces = document.querySelectorAll(".piece");
    pieces.forEach((piece) => {
      piece.addEventListener("click", selectHandle);
    });
  }

  // =====================================================================

  function selectHandle(e) {
    if (!e.target.classList.contains("piece")) {
      return;
    }

    const alreadySelected = e.target.classList.contains("active");
    unselectPiece();

    if (alreadySelected) {
      return;
    }

    selectPiece(e.target);
    removeMoves();
    addMoves(e.target.querySelectorAll(".move"));
  }

  function moveHandle(e) {
    const computedStyle = getComputedStyle(e.target);
    const offsetX = parseInt(computedStyle.getPropertyValue("--offsetX"));
    const offsetY = parseInt(computedStyle.getPropertyValue("--offsetY"));
    movement.to.x = movement.from.x + offsetX;
    movement.to.y = movement.from.y + offsetY;

    removeMoves();
    unselectPiece();

    requestMove();
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
    movement.from.x = piece.cellIndex;
    movement.from.y = piece.parentElement.sectionRowIndex;
  }

  function unselectPiece() {
    if (selectedPiece) {
      selectedPiece.classList.remove("active");
      selectedPiece = undefined;
    }
  }

  function requestMove() {
    if (!socket.OPEN) {
      return;
    }

    socket.send(JSON.stringify(movement));
  }

  function resetMovement() {
    movement.from.x = null;
    movement.from.y = null;
    movement.to.x = null;
    movement.to.y = null;
  }
});
