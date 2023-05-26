function hit() {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", hitEndpointUrl, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            location.reload();
        }
    };
    xhr.send();
}