function validarPasswords(){
	
	var claseCss = 'form-control is-invalid';
	
	var pass1 = document.getElementById('pass1').value;
	var pass2 = document.getElementById('pass2').value;
	
	if(pass1 == pass2) claseCss = 'form-control';
	
	document.getElementById('pass2').className = claseCss;
}


function validarDimension(){
	
	console.log("entra")
	
	var claseCss = 'form-control is-invalid';
	
	var pass = document.getElementById('pass1').value;
	
	if(pass.length >= 8) claseCss = 'form-control';
	
	document.getElementById('pass1').className = claseCss;
}
