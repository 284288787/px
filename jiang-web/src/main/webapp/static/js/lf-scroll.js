(function(document, w) {
    var LfScroll = {
        init: function() {
            this.int = null
            this.scrollEl = document.getElementById('gd')
            this.runing = false
            this._initEvent()
        },
        _initEvent: function() {
            var _this = this;

            if(this.int) { //如果已经存在防止重复，先取消
                clearInterval(this.int)
                this.int = null;
                this.scrollEl.removeEventListener('webkitTransitionEnd', this);
            }

            this.runing = false;
            this.scrollEl.addEventListener('webkitTransitionEnd', this);
            _this.int = setInterval(_this._scroll.bind(_this), 1500);
        },
        handleEvent: function(e) {
            switch(e.type) {
                case 'webkitTransitionEnd':
                    this._scrollEnd(e);
                    break;
            }
        },
        _scroll: function() {
            if(this.runing) {
                return;
            }
            this.runing = true;
            var scrollEl = this.scrollEl;
            var firts = scrollEl.querySelector('li');
            var y = 0 - firts.offsetHeight;
            var lastEl = firts.cloneNode(true);

            scrollEl.appendChild(lastEl);
            scrollEl.style.webkitTransition = 'all 0.4s linear';
            scrollEl.style.webkitTransform = 'translate3d(0px, ' + y + 'px, 0px)';
        },
        _scrollEnd: function(e) {
            var scrollEl = this.scrollEl;
            var firts = scrollEl.querySelector('li');
            scrollEl.removeChild(firts);
            scrollEl.style.webkitTransition = 'all 0s linear';
            scrollEl.style.webkitTransform = 'translate3d(0px, 0px, 0px)';
            this.runing = false;
        }
    };
    w.LfScroll = LfScroll
})(document, window)