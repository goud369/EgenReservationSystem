/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    'use strict';

    angular
            .module('plunker')
            .service('cancelService', cancelService);

    cancelService.$inject = ['$q', '$http'];
    function cancelService($q, $http) {
          
        var cancelService = this;
        cancelService.cancelReservation = function (confirmationNumber) {
             var defer = $q.defer();
            $http.delete('http://localhost:8080/vs/reservations/'+confirmationNumber)
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