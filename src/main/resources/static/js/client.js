document.addEventListener("DOMContentLoaded", () => {
  const host = window.location.hostname;
  const path = "connections";
  const socket = new WebSocket(`ws://${host}:3000/${path}`);

  socket.onopen = (e) => {
    console.log("connected");
  };

  socket.onmessage = (e) => {
    console.log(e.data);
  };
});
