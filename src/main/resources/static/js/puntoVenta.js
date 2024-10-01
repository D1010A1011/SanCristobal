let carrito = [];

// Función para agregar producto al carrito
function agregarAlCarrito(codigo,nombre,precio,stock) {
    const productoEnCarrito = carrito.find(p => p.codigo === codigo);

    if (productoEnCarrito) {
        if (productoEnCarrito.cantidad<stock){
            productoEnCarrito.cantidad+=1;
        }
    } else {
        carrito.push({
                codigo: codigo,
                nombre: nombre,
                precio: precio,
                stock:stock,
                cantidad: 1
            });

    }
    renderCarrito();
}

function toggleCar() {
    const menu = document.getElementById("carrito");
    menu.classList.toggle("active");
}

function aumentarCantidad(codigo){
    const productoEnCarrito=carrito.find(p=>p.codigo===codigo);
    if (productoEnCarrito&&productoEnCarrito.cantidad<productoEnCarrito.stock){
        productoEnCarrito.cantidad +=1;
    }
    renderCarrito();
}
function disminuirCantidad(codigo){
    const productoEnCarrito=carrito.find(p=>p.codigo===codigo);
    if (productoEnCarrito){
        productoEnCarrito.cantidad -=1;
        if (productoEnCarrito.cantidad<=0){
            carrito=carrito.filter(p=>p.codigo !== codigo);
        }
    }
    renderCarrito();
}

// Función para renderizar el carrito
function renderCarrito() {
    const carritoTableBody = document.getElementById("carritoBody");
    carritoTableBody.innerHTML = '';
    let total = 0;

    carrito.forEach((item, index) => {
        let precioCantidad=(item.precio * item.cantidad).toFixed(2)
        const row = document.createElement('tr');
        row.innerHTML = `
      <td>${item.codigo}</td>
      <td>${item.nombre}</td>
      <td><button  onclick="disminuirCantidad('${item.codigo}')">-</button>
        ${item.cantidad}
        <button onclick="aumentarCantidad('${item.codigo}')">+</button></td>
      <td>${item.precio}</td>
      <td>${precioCantidad}</td>
    `;
        total += item.precio * item.cantidad;
        carritoTableBody.appendChild(row);
    });
    document.getElementById("valor-total").innerText="Valor total: COP: $"+total.toFixed(2);
    document.getElementById('total').value=total.toFixed(2);
    document.getElementById("carrito-input").value=JSON.stringify(carrito)
}


/**Funcion para agregar EventListener a todos los botones de Productos*/

document.addEventListener("DOMContentLoaded",function (){
    const botones=document.querySelectorAll('.agregar-carrito-btn');

    botones.forEach(boton=>{
        boton.addEventListener('click',function (){
            const codigo=this.getAttribute('data-codigo');
            const nombre=this.getAttribute('data-nombre');
            const precio=this.getAttribute('data-precio');
            const stock=this.getAttribute('data-stock')
            agregarAlCarrito(codigo,nombre,precio,stock);
        });
    });
});

/**Alerta de carga de página de ventas*/
document.addEventListener("DOMContentLoaded",function (){
    obtenerCliente();
});
function obtenerCliente(){
    Swal.fire({
        title: 'Ingresa el documento o nit del cliente',
        input: 'text',
        inputPlaceholder: 'Documento',
        showCancelButton: false,
        confirmButtonText: 'Aceptar',
        preConfirm: (inputValue) => {
            // Validar si el input está vacío
            if (!inputValue) {
                // Si está vacío, rechaza la promesa con un mensaje de error
                Swal.showValidationMessage('El documento es requerido');
                return false;
            }
            return inputValue; // Devolver el valor si es válido
        }
    }).then((result) => {
        if (result.isConfirmed) {
            const inputValue = result.value;
            document.getElementById('client').value = inputValue;
        }
    });
}

function toogleTableBusqueda(){
    const menu = document.getElementById("busqueda-table");
    menu.classList.toggle("active");
}

function buscarProducto(){
    const codigo=document.getElementById("codigo-input").value;

    const busquedaTableBody=document.getElementById("busquedaBody");
    busquedaTableBody.innerHTML='';

    const row = document.createElement('tr');
    fetch('/buscar/'+codigo)
        .then(response => {
            if (!response.ok) {
                throw new Error('Producto no encontrado');
            }
            return response.json();
        })
        .then(producto => {
            row.innerHTML = `
                        <td> ${producto.codigo}</td>
                        <td> ${producto.nombre}</td>
                        <td> ${producto.categoria}</td>
                        <td> ${producto.precio}</td>
                        <td> ${producto.stock}</td>
                        <td> <button onclick="agregarAlCarrito('${producto.codigo}','${producto.nombre}','${producto.precio}','${producto.stock}')">Agregar</button></td>
                    `;
            busquedaTableBody.appendChild(row);
            toogleTableBusqueda();
        })
        .catch(error=>{
            console.error('Error: ',error)
        });
}

