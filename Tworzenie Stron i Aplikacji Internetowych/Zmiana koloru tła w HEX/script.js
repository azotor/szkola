var color = '';
var body = document.body;
var char = document.querySelectorAll( '.char' );
var alphabet = '0123456789AaBbCcDdEeFf';

window.onkeydown = function( e ) {

    if( color.length && e.key == 'Backspace' ) {
        color = color.slice( 0, -1 );
    }

    if( color.length < 6 && alphabet.indexOf( e.key ) != -1 ) {
        color += e.key.toUpperCase();
    }

    for( var i = 0; i < char.length; i++ ) {
        char[ i ].innerText = '';
    }

    if( color.length ) {
        for( var i = 0; i < color.length; i++ ) {
            char[ i ].innerText = color.charAt( i );
        }
    }

    if( color.length == 6 ) body.style.backgroundColor = '#' + color;

};