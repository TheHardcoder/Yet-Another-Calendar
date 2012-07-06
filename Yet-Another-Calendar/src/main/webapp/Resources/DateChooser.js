

function daysInMonth(iMonth, iYear)
{
return 32 - new Date(iYear, iMonth, 32).getDate();
}

backup01 = window.onload;

window.onload = function() {
	backup01();
	update();
	var syear = document.getElementById("selectedyear");
	var smonth = document.getElementById("selectedmonth");
	var sweek = document.getElementById("selectedweek");
	var sday = document.getElementById("selectedday");
	if (syear != null){
		var children = document.getElementById("year").childNodes;
		for (i = 0; i < children.length; i++){
			if (children[i].innerHTML == syear.innerHTML){
				children[i].selected = true;
			}
		}
	}
	if (smonth != null){
		var children = document.getElementById("month").childNodes;
		for (i = 0; i < children.length; i++){
			if (children[i].innerHTML == smonth.innerHTML){
				children[i].selected = true;
			}
		}
	}
	if (sweek != null){
		document.getElementById("week").value = sweek.innerHTML;
	}
	if (sday != null){
		var children = document.getElementById("day").childNodes;
		for (i = 0; i < children.length; i++){
			if (children[i].innerHTML == sday.innerHTML){
				children[i].selected = true;
			}
		}
	}
}

function update() {
	writeSelectableDays(daysInMonth(document.getElementById("month").value - 1, document.getElementById("year").value));
	var date = new Date(document.getElementById("year").value, document.getElementById("month").value - 1, document.getElementById("day").value);
	var onejan = new Date(date.getFullYear(),0,1);
	var week = Math.ceil((((date - onejan) / 86400000) + onejan.getDay() + 1)/7);
	if (week > 52)
		week = 1;
	document.getElementById("week").value = week;
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