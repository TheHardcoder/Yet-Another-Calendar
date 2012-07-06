

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
	writeSelectableDays(daysInMonth(document.getElementById("month").value - 1, document.getElementById("year").value), document.getElementById("day"));
	var date = new Date(document.getElementById("year").value, document.getElementById("month").value - 1, document.getElementById("day").value);
	Datum = date; // Anm. 1
	DoDat=donnerstag(Datum);
	kwjahr=DoDat.getFullYear();
	DoKW1=donnerstag(new Date(kwjahr,0,4)); // Anm. 2
	kw=Math.floor(1.5+(DoDat.getTime()-DoKW1.getTime())/86400000/7);
	document.getElementById("week").value = kw;
	if (document.getElementById("endday") != null){
		writeSelectableDays(daysInMonth(document.getElementById("endmonth").value - 1, document.getElementById("endyear").value), document.getElementById("endday"));
		var date = new Date(document.getElementById("endyear").value, document.getElementById("endmonth").value - 1, document.getElementById("endday").value);
		Datum = date; // Anm. 1
		DoDat=donnerstag(Datum);
		kwjahr=DoDat.getFullYear();
		DoKW1=donnerstag(new Date(kwjahr,0,4)); // Anm. 2
		kw=Math.floor(1.5+(DoDat.getTime()-DoKW1.getTime())/86400000/7);
		document.getElementById("endweek").value = kw;
	}
}

function donnerstag(datum) { // Anm. 5
	  var Do=new Date();
	  Do.setTime(datum.getTime() + (3-((datum.getDay()+6) % 7)) * 86400000); // Anm. 3
	  return Do;
}

function writeSelectableDays(max, element){
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