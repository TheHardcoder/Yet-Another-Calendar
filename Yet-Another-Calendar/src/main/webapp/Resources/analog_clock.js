window.onload = function() {
	 
    setInterval( function() {
    var seconds = new Date().getSeconds();
    var sdegree = seconds * 6;
    var srotate = "rotate(" + sdegree + "deg)";
    
    document.getElementById("cl_sec").setAttribute('style', "-moz-transform: " + srotate + "; -webkit-transform: " + srotate);
        
    }, 1000 );
    
    setInterval( function() {
    var hours = new Date().getHours();
    var mins = new Date().getMinutes();
    var hdegree = hours * 30 + (mins / 2);
    var hrotate = "rotate(" + hdegree + "deg)";
    
    document.getElementById("cl_hour").setAttribute('style', "-moz-transform: " + hrotate + "; -webkit-transform: " + hrotate);
        
    }, 1000 );

    setInterval( function() {
    var mins = new Date().getMinutes();
    var mdegree = mins * 6;
    var mrotate = "rotate(" + mdegree + "deg)";
    
    document.getElementById("cl_min").setAttribute('style', "-moz-transform: " + mrotate + "; -webkit-transform: " + mrotate);
        
    }, 1000 );

}