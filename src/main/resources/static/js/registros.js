function cambiarFragment(url){
    fetch(url)
        .then(response => response.text())
        .then(data=> {
            document.getElementById("content").innerHTML=data;
        })
        .catch(error =>{
            document.getElementById("content").innerHTML='' +
                '<p>Error al cargar el contenido</p>'
        });
}

