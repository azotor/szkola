var code = '';
var correct_code = '2468';
var alphabet = '1234567890';
var correct = false;
var number = document.querySelectorAll( '.number' );

window.onkeydown = function( e ) {

    if( correct ) return;
    
    if( e.key == 'Backspace' ) {

        if( code.length > 0 ) {

            code = code.slice( 0, -1 );

        }

    }

    if( alphabet.indexOf( e.key ) >= 0 ) {
        
        if( code.length < 4 ) {

            code += e.key;

        }

    }

    for( var i = 0; i < number.length; i++ ) {

        number[ i ].innerText = "";

    }

    if( code.length ) {

        for( var i = 0; i < code.length; i++ ) {

            number[ i ].innerText = code.charAt( i );

        }

    }

    if( code.length == 4 ) {

        if( code === correct_code ) {
            document.body.classList.add( 'correct' );
            correct = true;
        } else {
            document.body.classList.add( 'wrong' );
        }

    }

    if( code.length < 4 && document.body.classList.contains( 'wrong' ) ) {
        document.body.classList.remove( 'wrong' );
    }

}