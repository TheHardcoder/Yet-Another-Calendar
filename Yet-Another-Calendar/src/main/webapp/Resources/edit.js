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
		this[name] = wert;
		if (document.getElementById(name) != null) {
			document.getElementById(name).value = wert;
		}
	}
}

var datevalues = new Array("Year", "Month", "Day", "Hour", "Minute", "Second");
var dates = new Array("dtstamp", "created", "lastmod", "exdate");

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