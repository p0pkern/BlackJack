function hit() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", hitEndpointUrl, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200){
            location.reload();
        }
    };
    xhr.send();
}

function stand() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", standEndpointUrl, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            location.reload();
        }
    };
    xhr.send();
}

function newHand() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", newHandEndpointUrl, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            location.reload();
        }
    };
    xhr.send();
}

