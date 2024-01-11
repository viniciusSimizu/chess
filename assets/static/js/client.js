document.addEventListener("load", () => {
  const socket = new WebSocket("ws://localhost:8080");

	socket.onopen((e) => {
		console.log('connected');
	});
})
