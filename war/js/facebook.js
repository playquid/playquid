(function($) {

    App.fbAsyncInitCallback = function() {
        FB.Event.subscribe('auth.login', function() {
            if (App['onFBLogin']) {
                location.href = App.onFBLogin;
            }
        });
    }

}(jQuery));
