document.addEventListener("DOMContentLoaded", () => {
  const host = window.location.hostname;
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
  };
});
