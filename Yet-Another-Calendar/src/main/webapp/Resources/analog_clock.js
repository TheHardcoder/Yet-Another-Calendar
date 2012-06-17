function zeichneUhr() {
	var objCanvas = document.getElementById('analog_clock');
	if (objCanvas != null){
		AnalogClock(objCanvas);
	}
}

window.onload = function() {
	zeichneUhr();
}

function AnalogClock(objCanvas) {

	var objDate = new Date();
	var intSek = objDate.getSeconds(); 
	var intMin = objDate.getMinutes(); 
	var intHours = objDate.getHours() % 12; 
	var objContext = objCanvas.getContext("2d");

	objContext.clearRect(0, 0, 150, 150); 

	objContext.save(); 
	objContext.translate(75, 75); 

	objContext.save();
	objContext.beginPath();
	objContext.arc(0, 0, 75, 0, 2 * Math.PI, false);
	objContext.fillStyle = "#000";
	objContext.fill();
	objContext.restore();

	objContext.save();
	objContext.beginPath();
	objContext.arc(0, 0, 65, 0, 2 * Math.PI, false);
	objContext.fillStyle = "#fff";
	objContext.fill();
	objContext.restore();

	// Minuten
	objContext.save();
	for ( var i = 0; i < 60; i++) {
		objContext.beginPath();
		if (i % 5 == 0) {
			objContext.moveTo(0, -45);
			objContext.lineTo(0, -60);
			objContext.lineWidth = 3;
		} else {
			objContext.moveTo(0, -53);
			objContext.lineTo(0, -60);
			objContext.lineWidth = 1;
		}
		objContext.strokeStyle = "#000";
		objContext.stroke();
		objContext.rotate(Math.PI / 30);
	}
	objContext.restore();

	// Stunden
	objContext.save();
	objContext.rotate(intHours * Math.PI / 6 + intMin * Math.PI / 360);
	objContext.beginPath(); 
	objContext.moveTo(0, 10);
	objContext.lineTo(0, -38); 
	objContext.lineWidth = 4;
	objContext.strokeStyle = "#666";
	objContext.stroke();
	objContext.restore();

	// Minuten
	objContext.save();
	objContext.rotate(intMin * Math.PI / 30);
	objContext.beginPath();
	objContext.moveTo(0, 10);
	objContext.lineTo(0, -50);
	objContext.lineWidth = 4;
	objContext.strokeStyle = "#666";
	objContext.stroke();
	objContext.restore();

	// Sekunden
	objContext.save();
	objContext.rotate(intSek * Math.PI / 30);
	objContext.beginPath();
	objContext.moveTo(0, 10);
	objContext.lineTo(0, -50);
	objContext.lineWidth = 2;
	objContext.strokeStyle = "#a00";
	objContext.stroke();
	objContext.restore();

	objContext.restore();

	hTimer = window.setTimeout(function() {
		AnalogClock(objCanvas);
	}, 1000);
}