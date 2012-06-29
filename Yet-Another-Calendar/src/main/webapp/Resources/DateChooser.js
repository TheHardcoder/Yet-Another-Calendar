

function daysInMonth(iMonth, iYear)
{
return 32 - new Date(iYear, iMonth, 32).getDate();
}

backup01 = window.onload;

window.onload = function() {
	backup01();
	update();
	
}

function update() {
	writeSelectableDays(daysInMonth(document.getElementById("month").value - 1, document.getElementById("year").value));
	var date = new Date(document.getElementById("year").value, document.getElementById("month").value - 1, document.getElementById("day").value);
	var onejan = new Date(date.getFullYear(),0,1);
	var week = Math.ceil((((date - onejan) / 86400000) + onejan.getDay()+1)/7);
	//document.getElementById("week").value = week;
	document.getElementById("week").value = Math.ceil(date.getDate()/7);
}

function writeSelectableDays(max){
	var element = document.getElementById("day");
	if (element == null){
		return;
	}
	var select = 1;
	for (var i = 0; i < element.options.length; i++){
		if (element.options[i].selected == true) {
			select = i;
		}
	}
	if (select > max-1){
		select = max-1;
	}
	var s = "";
	for (var i = 1; i <= max; i++){
		s += "<option>" + i + "</option>";
	}
	element.innerHTML = s;
	element.options[select].selected = true;
}