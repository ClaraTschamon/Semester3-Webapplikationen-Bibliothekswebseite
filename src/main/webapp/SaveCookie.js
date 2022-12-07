//Clara Tschamon
window.addEventListener('load', function() {
        //1 tage = 86400 Sekunden
        var expires = (new Date(Date.now() + 86400 * 5000)).toUTCString(); //5 Tage
        let path = window.location.pathname;
        document.cookie = `lastVisited=` + path +`;path=/BibliothekWeb; expires=`+expires;

})





