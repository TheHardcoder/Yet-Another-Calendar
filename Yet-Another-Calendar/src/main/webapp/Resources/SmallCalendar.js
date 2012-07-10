var output = "";
var startdate = new Date();
var curdate = new Date();
var monthnames = new Array("Januar","Februar","März","April","Mai","Juni","Juli","August","September","Oktober","November","Dezember");

Date.prototype.getWeek = function() {
	Datum = this; // Anm. 1
	DoDat=donnerstag(Datum);
	kwjahr=DoDat.getFullYear();
	DoKW1=donnerstag(new Date(kwjahr,0,4)); // Anm. 2
	kw=Math.floor(1.5+(DoDat.getTime()-DoKW1.getTime())/86400000/7);
	return kw;
}

function donnerstag(datum) { // Anm. 5
	  var Do=new Date();
	  Do.setTime(datum.getTime() + (3-((datum.getDay()+6) % 7)) * 86400000); // Anm. 3
	  return Do;
}

function create() {
	startdate = new Date();
	curdate = new Date();
	startdate.setDate(1);
	while (startdate.getDay() != 1){
		startdate.setDate(startdate.getDate()-1);
	}
	var max = daysInMonth(curdate.getMonth(), curdate.getFullYear());
	var i = 0;
	output = "<tr><th colspan='7'>" + curdate.getDate() + ". " + monthnames[curdate.getMonth()] + " " + curdate.getFullYear() + "</th></tr><tr><td>Mo</td><td class='even'>Di</td><td>Mi</td><td class='even'>Do</td><td>Fr</td><td class='even'>Sa</td><td>So</td></tr>";
	output += "<tr>";
	while(i < curdate.getDate() || startdate.getDate() < daysInMonth(curdate.getMonth(), curdate.getFullYear())) {
		writeDay(startdate.getDate());
		if (startdate.getDay() == 0){
			output += "</tr><tr>";
		}
		i++;
		startdate.setDate(startdate.getDate()+1);
	}
	while(startdate.getDay() != 0) {
		writeDay(startdate.getDate());
		i++;
		startdate.setDate(startdate.getDate()+1);
	}
	writeDay(startdate.getDate());
	output += "</tr>";
	var table = document.getElementById("smallcalendar");
	table.innerHTML = output;
}
backup02 = window.onload;

window.onload = function() {
	backup02();
	create();
	var elems = getElementsByClass("entry");
	for (var i = 0; i < elems.length; i++){
		var s = elems[i].innerHTML;
		elems[i].innerHTML = unescape(elems[i].innerHTML);
		elems[i].setAttribute("title",unescape(elems[i].getAttribute("title")))
	}
}

function getElementsByClass( searchClass, domNode, tagName) { 
	if (domNode == null) domNode = document;
	if (tagName == null) tagName = '*';
	var el = new Array();
	var tags = domNode.getElementsByTagName(tagName);
	var tcl = " "+searchClass+" ";
	for(i=0,j=0; i<tags.length; i++) { 
		var test = " " + tags[i].className + " ";
		if (test.indexOf(tcl) != -1) 
			el[j++] = tags[i];
	} 
	return el;
}

function writeDay(day){
	var dayclass = "";
	if (startdate.getMonth() != curdate.getMonth())
		dayclass = "gray";
	if (startdate.getDate() == curdate.getDate() && startdate.getMonth() == curdate.getMonth() && startdate.getFullYear() == curdate.getFullYear()){
		dayclass += " selected";
	}
	if (startdate.getDay() % 2 == 0 && startdate.getDay() > 0)
		dayclass += " even";
	output += "<td class='" + dayclass + "'>" + day + "</td>";
}

function daysInMonth(iMonth, iYear)
{
return 32 - new Date(iYear, iMonth, 32).getDate();
}

function goToToday(servlet){
	var date = new Date();
	window.location = servlet + "&selectedyear=" + date.getFullYear() + "&selectedmonth=" + (date.getMonth() + 1) + "&selectedweek=" + date.getWeek() + "&selectedday=" + date.getDate();
}

function goTo(servlet, year, month, day){
	var date = new Date(year,month-1,day);
	window.location = servlet + "&selectedyear=" + date.getFullYear() + "&selectedmonth=" + (date.getMonth() + 1) + "&selectedweek=" + date.getWeek() + "&selectedday=" + date.getDate();
}

function changeWeek(servlet, next){
	var date = new Date(document.getElementById("selectedyear").innerHTML, document.getElementById("selectedmonth").innerHTML - 1, document.getElementById("selectedday").innerHTML);
	date = new Date(date.getTime() + (next ? 7: -7) * 24 * 60 * 60 * 1000);
	window.location = servlet + "&selectedyear=" + date.getFullYear() + "&selectedmonth=" + (date.getMonth() + 1) + "&selectedweek=" + date.getWeek() + "&selectedday=" + date.getDate();
}

function changeMonth(servlet, next){
	var date = new Date(document.getElementById("selectedyear").innerHTML, document.getElementById("selectedmonth").innerHTML - 1, document.getElementById("selectedday").innerHTML);
	date.setMonth(date.getMonth() + (next ? +1 : -1));
	window.location = servlet + "&selectedyear=" + date.getFullYear() + "&selectedmonth=" + (date.getMonth() + 1) + "&selectedweek=" + date.getWeek() + "&selectedday=" + date.getDate();
}