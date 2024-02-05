var first = '';
var char = '';
var second = '';
var result = '';
var input = document.querySelectorAll( '.input' );
var alphabet = '1234567890';
var chars = '+-*/';
var down = false;

window.onkeydown = function( e ) {

    if( !input[ 0 ].classList.contains( 'check' ) ) {

        if( first.length && e.key == 'Backspace' ) {
            first = first.slice( 0, -1 );
        }

        if( first.length < 8 ) {
            
            if( alphabet.indexOf( e.key ) != -1 ) {
                first += e.key;
            }

            if( e.key == '.' || e.key == ',' ) {
                if( first.indexOf( e.key ) == -1 ) {
                    first += '.';
                }
            }

        }

        if( first.length && chars.indexOf( e.key ) != -1 ) {
            char = e.key;
            input[ 0 ].classList.add( 'check' );
            input[ 0 ].classList.remove( 'focus' );
            input[ 1 ].classList.add( 'check' );
            input[ 2 ].classList.add( 'focus' );
            down = true;
        }

        if( first.length == 0 && e.key == '-' ) {
            first = '-';
        }

    }

    if( !down && input[ 0 ].classList.contains( 'check' ) && !input[ 2 ].classList.contains( 'check' ) ) {

        if( second.length && e.key == 'Backspace' ) {
            second = second.slice( 0, -1 );
        }

        if( second.length == 0 && e.key == '-' ) {
            second = '-';
        }

        if( second.length < 8 ) {
            
            if( alphabet.indexOf( e.key ) != -1 ) {
                second += e.key;
            }

            if( e.key == '.' || e.key == ',' ) {
                if( second.indexOf( e.key ) == -1 ) {
                    second += '.';
                }
            }

        }

        if( second.length && e.key == '=' ) {
            input[ 2 ].classList.add( 'check' );
            input[ 2 ].classList.remove( 'focus' );
            input[ 3 ].classList.add( 'check' );
            input[ 4 ].classList.add( 'check' );
            switch( char ) {
                case '+' :
                    result = parseFloat( first ) + parseFloat( second );
                    break;
                case '-' :
                    result = parseFloat( first ) - parseFloat( second );
                    break;
                case '*' :
                    result = parseFloat( first ) * parseFloat( second );
                    break;
                case '/' :
                    result = parseFloat( first ) / parseFloat( second );
                    break;
            }
        }

    }

    input[ 0 ].innerText = first;
    input[ 1 ].innerText = char;
    input[ 2 ].innerText = second;
    input[ 3 ].innerText = input[ 3 ].classList.contains( 'check' ) ? '=' : '';
    input[ 4 ].innerText = result;

};

window.onkeyup = function( e ) { down = false; };