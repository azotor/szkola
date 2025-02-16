class Slider {

    constructor() {
        this.element = document.getElementById( `slider` );
        this.images = this.element.dataset.photos.split( `;` );
        this.currentImageIndex = 0;

        this.setImage();

        if( this.images.length > 1 ) {

            this.prev = document.createElement( `div` );
            this.prev.classList.add( `slider-button`, `prev` );
            this.prev.title = `Poprawdni obraz`;
            this.prev.addEventListener( `click`, () => this.prevImage() );

            this.next = document.createElement( `div` );
            this.next.classList.add( `slider-button`, `next` );
            this.next.title = `Następny obraz`;
            this.next.addEventListener( `click`, () => this.nextImage() );

            this.element.appendChild( this.prev );
            this.element.appendChild( this.next );

            this.scroll = 0;
            this.scrollOffset = 0;
            this.lastTime = new Date().getTime();

            this.resetChangeTime();
            this.loop();
        }
    }

    loop() {
        let now = new Date().getTime();
        let dt = now - this.lastTime;
        this.lastTime = now;

        if( now >= this.changeTime ) this.nextImage();
        if( this.scroll == -1 ) this.scrollToPrev( dt );
        if( this.scroll == 1 ) this.scrollToNext( dt );
        requestAnimationFrame( () => this.loop() );
    };

    setImage() {
        this.element.style.backgroundImage = `url(./img/slider/${ this.images[ this.currentImageIndex ] })`;
    }

    resetChangeTime() {
        this.changeTime = new Date().getTime() + parseInt( this.element.dataset.timer );
    }

    nextImage() {
        if( this.scroll == 0 ) {
            this.currentImageIndex++;
            if( this.currentImageIndex >= this.images.length ) this.currentImageIndex = 0;
            this.toNext();
            this.resetChangeTime();
        }
    }

    prevImage() {
        if( this.scroll == 0 ) {
            this.currentImageIndex--;
            if( this.currentImageIndex < 0 ) this.currentImageIndex = this.images.length - 1;
            this.toPrev();
            this.resetChangeTime();
        }
    }

    toPrev() {
        let currentImage = this.images[ this.currentImageIndex ];
        let nextImageIndex = this.currentImageIndex + 1;
        if( nextImageIndex >= this.images.length ) nextImageIndex = 0;
        let nextImage = this.images[ nextImageIndex ];
        this.element.style.backgroundImage = `url(./img/slider/${ currentImage }), url(./img/slider/${ nextImage })`;
        this.element.style.backgroundPosition = `-800px, 0px`;
        this.scroll = -1;
        this.scrollOffset = -800;
    }

    scrollToPrev( dt ) {
        if( this.scrollOffset < 0 ) {
            this.scrollOffset += 800 * 3 * dt / 1000;
            this.element.style.backgroundPosition = `${ this.scrollOffset }px, ${ this.scrollOffset + 800 }px`;
        } else {
            this.scroll = 0;
            this.element.style.backgroundPosition = `0px, 800px`;
        }

    }

    toNext() {
        let currentImage = this.images[ this.currentImageIndex ];
        let prevImageIndex = this.currentImageIndex - 1;
        if( prevImageIndex < 0 ) prevImageIndex = this.images.length - 1;
        let prevImage = this.images[ prevImageIndex ];
        this.element.style.backgroundImage = `url(./img/slider/${ prevImage }), url(./img/slider/${ currentImage })`;
        this.element.style.backgroundPosition = `0px, 800px`;
        this.scroll = 1;
        this.scrollOffset = 800;
    }

    scrollToNext( dt ) {
        if( this.scrollOffset > 0 ) {
            this.scrollOffset -= 800 * 3 * dt / 1000;
            this.element.style.backgroundPosition = `${ this.scrollOffset - 800 }px, ${ this.scrollOffset }px`;
        } else {
            this.scroll = 0;
            this.element.style.backgroundPosition = `-800px, 0px`;
        }

    }

}

new Slider();