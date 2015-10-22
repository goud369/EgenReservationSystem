(function () {
    'use strict';

    angular
            .module('plunker')
            .service('loginService', loginService);

    loginService.$inject = ['$q', '$http'];
    function loginService($q, $http) {
          
        var loginService = this;
        loginService.login = function (email, password) {
             var defer = $q.defer();
            $http.get('http://localhost:8080/vs/ownerscheck/'+email+'/'+password)
                    .then(successFn, errorFn);

            function successFn(response) {
                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;

        };
        
    }
})();