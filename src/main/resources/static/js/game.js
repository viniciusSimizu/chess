document.addEventListener("DOMContentLoaded", () => {
	const socket = new WebSocket("ws://localhost:8080");

	socket.onopen = (event) => {
		console.log("salve");
	};
})
