document.addEventListener("DOMContentLoaded", () => {
  const socket = new WebSocket("ws://127.0.0.1:3000");

  socket.onopen = (e) => {
    console.log("connected");
  };

  socket.onmessage = (e) => {
    console.log(e.data);
  };
});
