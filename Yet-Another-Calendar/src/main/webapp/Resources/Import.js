
function showFileOpenDialog (view, year, month, week, day) {
	var elem = document.getElementById("fileopen");
	elem.setAttribute('style', 'opacity: 0;z-index: 10;');
	var link = "import?action=import&amp;view=" + view + "&amp;selectedyear=" + year + "&amp;selectedmonth=" + month + "&amp;selectedweek=" + week + "&amp;selectedday=" + day;
	elem.innerHTML = '<form name="importdialog" action="' + link +'" method="post" enctype="multipart/form-data" id="importdialog">' +
	'<div style="position: absolute; top: 10px; right: 10px; width: 15px; height; 15px; text-algin: center; font-weight: bold; cursor: pointer;" onclick="closeFileOpenDialog();">x</div>'+
	'<input type="hidden" name="view" value="' + view + '"></input>' +
	'<input type="hidden" name="selectedyear" value="' + year + '"></input>' +
	'<input type="hidden" name="selectedmonth" value="' + month + '"></input>' +
	'<input type="hidden" name="selectedday" value="' + day + '"></input>' +
	'<div style="margin: 10px; font-size: 20px;">Kalender-Datei hochladen</div>'+	
	'<input style="margin: 10px;" type="file" name="file" size="50" accept="text/calendar"></input>'+
		'<input style="margin: 10px;" type="submit" value="hochladen" onclick="closeFileOpenDialog(); return true;"></input>'+
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