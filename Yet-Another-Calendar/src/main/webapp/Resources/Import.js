
function showFileOpenDialog () {
	var elem = document.getElementById("fileopen");
	elem.setAttribute('style', 'opacity: 0;z-index: 10;');
	elem.innerHTML = '<form action="import" method="post" enctype="multipart/form-data" style="position: absolute; z-index: 10; left: 50%; margin-left: -250px; margin-top: 50px; width: 500px; height: 150px; padding: 10px; background-color: #A65E00;">' +
	'<div style="position: absolute; top: 5px; right: 5px; width: 15px; height; 15px; text-algin: center; font-weight: bold; cursor: pointer;" onclick="closeFileOpenDialog();">x</div>'+
	'<div style="margin: 10px; font-size: 20px;">Kalender-Datei hochladen</div>'+	
	'<input style="margin: 10px;" type="file" name="file" size="50" accept="text/calendar"></input>'+
		'<input style="margin: 10px;" type="submit" value="hochladen" onclick="closeFileOpenDialog();"></input>'+
		'</form>';
	var i = 0, max = 50;
	var processor = setInterval(function() {
		if ( i <= 50){
			document.getElementById("fileopen").setAttribute('style', 'opacity: ' + (i/50.0) + ';z-index: 10;');
		}
		else {
			clearInterval(processor);
		}
		i++;
	}, 10);
}

function closeFileOpenDialog () {
	var i = 0, max = 50;
	var processor = setInterval(function() {
		if ( i <= 50){
			document.getElementById("fileopen").setAttribute('style', 'opacity: ' + (1.0 - (i/50.0)) + ';z-index: 10;');
		}
		else {
			var elem = document.getElementById("fileopen");
			elem.innerHTML = "";
			clearInterval(processor);
		}
		i++;
	}, 10);
}