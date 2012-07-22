backup = window.onload;

window.onload = function() {
	backup();
	for (j = 0; j < dates.length; j++) {
		insertFields(dates[j]);
	}
	var querystring = location.search;
	if (querystring == '')
		return;
	var wertestring = querystring.slice(1);
	var paare = wertestring.split("&");
	var paar, name, wert;

	for ( var i = 0; i < paare.length; i++) {
		paar = paare[i].split("=");
		name = paar[0];
		wert = paar[1];
		name = unescape(name).replace("+", " ");
		wert = unescape(wert).replace("+", " ");
		if ((name.search(/year/) >= 0 || name.search(/month/) >= 0 || name.search(/week/) >= 0 || name.search(/day/) >= 0) && wert.length <= 1){
			wert = "0" + wert;
		}
		this[name] = wert;
		if (document.getElementById(name) != null) {
			if(name == "rrule") {
				wert = wert.replace(/g/g, "=");
			}
			document.getElementById(name).value = wert;
		}
		if (name == 'year'){
			document.getElementById("endyear").value=wert;
		}
		if (name == 'month'){
			document.getElementById("endmonth").value=wert;
		}
		if (name == 'day'){
			document.getElementById("endday").value=wert;
		}
		if (name == 'week'){
			document.getElementById("endweek").value=wert;
		}
		else {
			document.getElementById("Edittitle").innerHTML="Termin bearbeiten";
		}
	}
	if (document.getElementById("id").value == 0){
		document.getElementById("Edittitle").innerHTML="Termin erstellen";
	}
	document.forms["editform"].elements["summary"].focus();
}

var datevalues = new Array("Year", "Month", "Day", "Hour", "Minute", "Second");
var dates = new Array("dtstamp", "created", "lastmod", "exdate");

function updateEvent(){
	setFormData();
	document.getElementById("editaction").value="save";
	var elem = document.forms["editform"].elements;
	for (var i = 0; i < elem.length; i++){
		elem[i].value = escape(elem[i].value);
	}
	document.getElementById("editform").submit();
}

function deleteEvent(){
	document.getElementById("editaction").value="delete";
	document.getElementById("editform").submit();
}

function setFormData() {
	for (i = 0; i < dates.length; i++) {
		setDateFor(dates[i]);
	}

	// for (i = 0; i < form.elements.length; ++i)
	// if (form.elements[i].value == "") {
	// alert("Es wurden nicht alle Felder ausgefuellt!");
	// form.elements[i].focus();
	// return false;
	// }
	return true;
}

function setDateFor(name) {
	var date = new Date();
	var form = document.getElementById("editform");
	form.elements[name + "Year"].value = date.getFullYear();
	form.elements[name + "Month"].value = date.getMonth();
	form.elements[name + "Day"].value = date.getDate();
	form.elements[name + "Hour"].value = date.getHours();
	form.elements[name + "Minute"].value = date.getMinutes();
	form.elements[name + "Second"].value = date.getSeconds();
}

function insertFields(datename) {
	var father = document.getElementById("hiddenfields");
	for (i = 0; i < datevalues.length; i++) {
		father.appendChild(getFieldElement(datename + datevalues[i]));
	}
}

function getFieldElement(name) {
	var ele = document.createElement("input");
	ele.setAttribute("type", "hidden");
	ele.setAttribute("name", name);
	ele.setAttribute("value", "0");
	return ele;
}