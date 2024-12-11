$(document).ready(avanzar());

async function avanzar() {
	let resultado = document.querySelector("#producto");
	resultado.innerHTML += ` `;
	
	
	const response = await fetch('/listproductos', {
		method: 'GET',
		headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
	});
	
	const productos = (JSON.parse((await response.text()).toString()));

	let pasada = 0;
	let response2;
	for (let p of productos) {
		const uri = '/uploads/' + p.imagen;
		response2 = await fetch(uri, {
			method: 'GET',
		});
		resultado.innerHTML += ` 
								<div class="mainip" >
									<img  class="mainimagep" id="imagen${pasada}" alt="Imagen no disponible"/>
									<div class="textmp">
										<h4 class="T44p"> ${p.nombre}</h4>
										<p class="TP1p">$${p.precio}\u2003\u2003</p>
									</div>
								</div>
						       `;

		const img = document.getElementById('imagen' + pasada);
		img.src = response2.url;
		pasada = pasada + 1;
	}
}
