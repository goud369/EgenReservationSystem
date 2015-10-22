/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';

    angular
            .module('plunker')
            .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', '$rootScope'];
    function LoginController($location, AuthenticationService, $rootScope) {
        var loginvm = this;

        loginvm.login = login;

       

        function login() {
            AuthenticationService.Login(loginvm.username, loginvm.password)
                    .then(function (data) {
                        if (data.length === 0) {
                            loginvm.error = "Please Enter valid crdentials";
                        }
                        else {
                            $rootScope.id = data.id;
                            $rootScope.OwLoggedIn = true;
                          //  AuthenticationService.SetCredentials(loginvm.username, loginvm.password);
                            $location.path('/admin');
                        }
                    }, function (error) {
                        console.log(error);

                    });

        }
        ;
    }

})();

