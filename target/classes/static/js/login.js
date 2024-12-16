inputs = document.querySelectorAll('input'); //Tomo todos los inputs del html  
btn = document.getElementById('botonLogin');
var user = null;

expresiones = { //objeto con mis expresiones regulares
	mail: /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/,
	contraseña: /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/,
};

const validar = (e) => { //arrow function para validar datos

	switch (e.target.id) {
		case "mail":
			expresiones.mail.test(e.target.value) ? claseT('mail') : claseF('mail');
			break;
		case "contraseña":
			expresiones.contraseña.test(e.target.value) ? claseT('contraseña') : claseF('contraseña');
			break;
	}
}


async function avanzar() {

	const response = fetch('/Admin', {
		method: 'POST',
		headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
	});
	/*
	let validaduyc = false;
	alert(response.json);
	console.log(response);
	alert(JSON.parse((await response.text()).toString()));
	const admins = (JSON.parse((await response.text()).toString()));
	alert(admins);
	console.log(admins);
	
	for (let admin of admins) {
		if (admin.user == document.getElementById("mail").value && admin.contraseña == document.getElementById("contraseña").value) {
			validaduyc = true;
		}


	}
	alert(validaduyc);
	validaduyc == true ? (location.href = "/Admin/init/cargarproductos"): mostrarPopup2();
*/
}



inputs.forEach((input) => {
	input.addEventListener('keyup', validar);
});
btn.addEventListener('click', avanzar)

const claseF = (a) => {
	document.getElementById(a).classList.add('form_error');
	document.getElementById(a).classList.remove('form-control');

}
const claseT = (a) => {
	document.getElementById(a).classList.add('form-control');
	document.getElementById(a).classList.remove('form_error');

}

function mostrarPopup2() {
	let pop = document.getElementById("popup2")
	pop.style.display = "block";
	pop.innerHTML = '<h6 class="m-0 font-weight-bold text-primary">EL USUARIO O LA CONTRASEÑA SON INCORRECTOS</h6>' +
		'<button id="boton-cerrar2" onclick="javascript: cerrarPopup2();">Cerrar</button>'
}

function cerrarPopup2() {
	document.getElementById("popup2").style.display = "none";
}
